package com.tummsmedia.controllers;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.PendingResult;
import com.google.maps.model.GeocodingResult;
import com.tummsmedia.entities.*;
import com.tummsmedia.services.ItemRepo;
import com.tummsmedia.services.TransactionRepo;
import com.tummsmedia.services.UserRepo;
import com.tummsmedia.utilities.PasswordStorage;
import jodd.json.JsonParser;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
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

    @Autowired
    ItemRepo items;

    @Autowired
    UserRepo users;

    @Autowired
    TransactionRepo transactions;


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
        String gmapUrlByLatLng = String.format("https://www.google.com/maps/@%s,%s,16z", latitude, longitude);
        selectedItem.setMapUrl(gmapUrlByLatLng);
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

//    @RequestMapping(value = "/items", method = RequestMethod.GET)
//    public ResponseEntity<Iterable<Item>> showItemByCategory(@RequestParam("category")String category, HttpSession session){
//        String username = (String) session.getAttribute("username");
//        User user = users.findFirstByUsername(username);
//        Item.Category cat = Enum.valueOf(Item.Category.class, category);
//        return new ResponseEntity<Iterable<Item>>(items.findAllByCategory(cat), HttpStatus.OK);
//    }
//    @RequestMapping(path = "/add-item", method = RequestMethod.POST)
//    public ResponseEntity<Object> addNewItem(@RequestBody Item item, HttpSession session, MultipartFile file) throws Exception {
//        String username = (String) session.getAttribute("username");
//        User user = users.findFirstByUsername(username);
//        if (user == null) {
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }
//
//
//
//
//
//
//        HashSet<Image> itemImageSet = new HashSet<>();
//        File dir = new File("public/files");
//        dir.mkdirs();
//        File f = File.createTempFile(Integer.toString(items.item.getItemId()), file.getOriginalFilename(), dir);
//        String mimeType = new MimetypesFileTypeMap().getContentType(f);
//        FileOutputStream fos = new FileOutputStream(f);
//        fos.write(file.getBytes());
//        if(!mimeType.startsWith("image/")) {
//            return new ResponseEntity<Object>("File is not a compatible image type", HttpStatus.FORBIDDEN);
//        }
////        return new ResponseEntity<Object>("You have successfully loaded an image", HttpStatus.OK);
//
//        items.save(item);
//        return new ResponseEntity<Object>("You have successfully added and item", HttpStatus.OK);
//    }




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
    public HashMap<String, String> rentItem(@RequestParam("itemId")int itemId,  HttpSession session) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            throw new Exception("No valid user session!!!");
        }
        Item itemBorrowed = items.findFirstByItemId(itemId);
        String ownerUsername = items.findFirstByItemId(itemId).getUser().getUsername();
        Transaction transaction = new Transaction(user.getId(), items.findFirstByItemId(itemId).getUser().getId(), itemBorrowed);
        transactions.save(transaction);
        HashMap<String, String> emailDataMap = new HashMap<>();
        emailDataMap.put("borrower", user.getUsername());
        emailDataMap.put("owner", ownerUsername);
        emailDataMap.put("itemBorrowed", itemBorrowed.getItemName());
        emailDataMap.put("itemPrice", Long.toString(itemBorrowed.getAskingPrice()));
        emailDataMap.put("transactionId", Integer.toString(transaction.getTransactionId()));
        return emailDataMap;
    }


}
