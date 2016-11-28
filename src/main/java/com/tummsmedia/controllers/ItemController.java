package com.tummsmedia.controllers;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.PendingResult;
import com.google.maps.model.GeocodingResult;
import com.tummsmedia.entities.*;
import com.tummsmedia.services.ItemRepo;
import com.tummsmedia.services.MessageRepo;
import com.tummsmedia.services.TransactionRepo;
import com.tummsmedia.services.UserRepo;
import com.tummsmedia.utilities.PasswordStorage;
import jodd.json.JsonParser;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.SQLException;
import java.util.*;

import static org.aspectj.bridge.MessageUtil.fail;

/**
 * Created by john.tumminelli on 11/16/16.
 */
@RestController
public class ItemController {

    public static HashMap<String, String> emailDataMap = new HashMap<>();

    @Autowired
    ItemRepo items;

    @Autowired
    UserRepo users;

    @Autowired
    TransactionRepo transactions;

    @Autowired
    MessageRepo messages;



    Server h2;

    @PostConstruct
    public void init() throws SQLException, com.tummsmedia.utilities.PasswordStorage.CannotPerformOperationException, IOException {
        h2 = Server.createWebServer().start();

        if (items.count() == 0) {

            loadItemData();
        }
    }
    @PreDestroy
    public void destroy() {
        h2.stop();
    }

    public void loadItemData() throws IOException, PasswordStorage.CannotPerformOperationException {
        String hashedPassword = PasswordStorage.createHash("testuser123");
        UserDetail ud = new UserDetail("test","user","17A Princess Street", "", "Charleston", "SC", 29401);
        User user = new User("testuser@gmail.com", hashedPassword, ud);
        users.save(user);
        File f = new File("ptp_item_data_json");
        FileReader fr = new FileReader(f);
        int fileSize = (int) f.length();     //cast to int
        char[] contents = new char[fileSize];
        fr.read(contents, 0, fileSize);
        JsonParser parser = new JsonParser();
        ItemWrapper itemWrapper = parser.parse(contents, ItemWrapper.class);
        items.save(itemWrapper.items);
    }
    public static void getGeolocateMapDetail(String address, Item selectedItem) throws Exception {
        final List<GeocodingResult[]> resps = new ArrayList<GeocodingResult[]>();
        final String MAPS_API_KEY = "AIzaSyCfVwsmmrQ1ptS9ohzm779XRS8RgaiSTtg";

        PendingResult.Callback<GeocodingResult[]> callback =
                new PendingResult.Callback<GeocodingResult[]>() {
                    @Override
                    public void onResult(GeocodingResult[] result) {
                        resps.add(result);
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        fail("Got error when expected success.");
                    }
                };
        GeoApiContext context = new GeoApiContext().setApiKey(MAPS_API_KEY);
        GeocodingResult[] results = GeocodingApi.newRequest(context).address(address).await();
        Double latitude = results[0].geometry.location.lat;
        Double longitude = results[0].geometry.location.lng;
        HashMap<String, Double> coordsMap = new HashMap<>();
        coordsMap.put("latitude", latitude);
        coordsMap.put("longitude", longitude);
        selectedItem.setLatLng(coordsMap);
//        String gmapUrlByLatLng = String.format("https://www.google.com/maps/@%s,%s,16z", latitude, longitude);
    }


    @RequestMapping(path = "/all-items", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Item>> getItems(HttpSession session) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        return new ResponseEntity<Iterable<Item>>(items.findAll(), HttpStatus.OK);

    }
    @RequestMapping(path = "/random-items", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<Item>> getRandomItems(HttpSession session) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        ArrayList<Item> randomItemsList = new ArrayList<Item>();
        int max = (int) items.count();
        int min = 1;
        int previousRandom = 0;
        int randomItemId;
        Random rand = new Random();
        for (int i = 1 ; i < 13; i++){
            randomItemId = rand.nextInt((max - min) + 1) + min;
            if (randomItemId != previousRandom){
                Item randomItem = items.findFirstByItemId(randomItemId);
                randomItemsList.add(randomItem);
                previousRandom = randomItemId;
            }
            else {
                i = i - 1;
                break;
            }
        }
        return new ResponseEntity<ArrayList<Item>>(randomItemsList, HttpStatus.OK);
    }

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Item>> showItemByCategory(@RequestParam("category")String category, HttpSession session){
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        Item.Category cat = Enum.valueOf(Item.Category.class, category);
        return new ResponseEntity<Iterable<Item>>(items.findAllByCategory(cat), HttpStatus.OK);
    }


    @RequestMapping(value = "/get-item", method = RequestMethod.GET)
    public ResponseEntity<Item> getSingleItem(String gmapUrlByLatLng, @RequestParam("itemId")int itemId, HttpSession session) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        Item selectedItem = items.findFirstByItemId(itemId);

        String street = selectedItem.getUser().getUserDetail().getStreet();
        String city = selectedItem.getUser().getUserDetail().getState();
        String state = selectedItem.getUser().getUserDetail().getState();
        String zip = Integer.toString(selectedItem.getUser().getUserDetail().getZipcode());
        String address = String.format("%s %s, %s %s", street, city, state, zip);
        getGeolocateMapDetail(address, selectedItem);

        return new ResponseEntity<Item>(selectedItem, HttpStatus.OK);
    }

    @RequestMapping(value = "/rent-item", method = RequestMethod.POST)
    public ResponseEntity<Object> rentItem(@RequestParam("itemId")int itemId,  HttpSession session) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            String unauthorized = "User must be logged in to rent an item";
            return new ResponseEntity<Object>(unauthorized, HttpStatus.FORBIDDEN);
        }
        Item itemBorrowed = items.findFirstByItemId(itemId);
        String ownerUsername = items.findFirstByItemId(itemId).getUser().getUsername();
        Transaction transaction = new Transaction(user.getId(), items.findFirstByItemId(itemId).getUser().getId(), itemBorrowed);
        transactions.save(transaction);
        MessageController.sendOwnerMessage(itemBorrowed, transaction, messages, users);
        return new ResponseEntity<Object>(transaction, HttpStatus.OK);
    }

    // This is a basic JSON add item route with no photo upload. Use http://localhost:8080/add-item/upload for
    // for full featured add item route with photo upload capability
    @RequestMapping(path = "/add-item", method = RequestMethod.POST)
    public ResponseEntity<Object> addNewItem(@RequestBody Item item, HttpSession session
    ) throws Exception {

        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        HashSet<Image> tempSet = new HashSet<>();
        Image defImage = new Image("default_no_image.jpg");
        tempSet.add(defImage);
        item.setImages(tempSet);
        items.save(item);
        return new ResponseEntity<Object>(item, HttpStatus.OK);
    }
    @RequestMapping(value = "/upload-photo/{itemId}", method = RequestMethod.POST)
    public ResponseEntity<Object> uploadPhoto(@PathVariable("itemId")int itemId, HttpSession session, @RequestBody String imageData) throws IOException {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        Item item = items.findFirstByItemId(itemId);
        if (user == null) {
            String noValidLogin = "User not logged in";
            return new ResponseEntity<Object>(noValidLogin, HttpStatus.FORBIDDEN);
        }
        System.out.println(imageData);
        String [] dataArray = imageData.split(",");
        int last = dataArray.length -1;
        imageData = dataArray[last];
        File uploadDir = new File("public/images");
        File imageFile = File.createTempFile(Integer.toString(item.getItemId()) + "_", ".jpg", uploadDir);
        BASE64Decoder decoder = new BASE64Decoder();
        InputStream input = new ByteArrayInputStream(imageData.getBytes());
        byte[] decodedBytes = decoder.decodeBuffer(input);
        try (FileOutputStream stream = new FileOutputStream(imageFile)) {
            stream.write(decodedBytes);
        }
        item.setImages(null);
        Image workingImage = new Image();
        workingImage.setImageFileName(imageFile.getName());

        HashSet<Image> images = new HashSet<>();
        images.add(workingImage);
        item.setImages(images);
        items.save(item);
        return new ResponseEntity<Object>(item, HttpStatus.OK);
    }

    @RequestMapping(path = "/delete-item", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteItem(@RequestParam ("itemId")int itemId, HttpSession session){
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        items.delete(itemId);
        String deleteResponse = String.format("Item with itemID of: %s has been deleted from the database.", itemId);
        return new ResponseEntity<Object>(deleteResponse, HttpStatus.OK);
    }
}
