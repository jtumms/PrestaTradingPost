package com.tummsmedia.controllers;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
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
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by john.tumminelli on 11/16/16.
 */
@RestController
public class ItemController {
    private static final String MAPS_API_KEY = "AIzaSyCfVwsmmrQ1ptS9ohzm779XRS8RgaiSTtg";

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
    public static String getGeolocatioMapLink(String itemAddress) throws Exception {
        GeoApiContext context = new GeoApiContext().setApiKey(MAPS_API_KEY);
        GeocodingResult[] results =  GeocodingApi.geocode(context,
                itemAddress).await();
        return results[0].formattedAddress.toString();
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
        for (int i = 1 ; i < 13; i++){
            Random rand = new Random();
            int randomItemId = rand.nextInt((max - min) + 1) + min;
            Item randomItem = items.findFirstByItemId(randomItemId);
            randomItemsList.add(randomItem);
        }
        return new ResponseEntity<ArrayList<Item>>(randomItemsList, HttpStatus.OK);
    }

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Item>> showItemByCategory(@RequestParam("category")String category, HttpSession session){
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        return new ResponseEntity<Iterable<Item>>(items.findAllByCategory(category), HttpStatus.OK);
    }
    @RequestMapping(path = "/add-item", method = RequestMethod.POST)
    public ResponseEntity<Item> addNewItem(@RequestBody Item item, HttpSession session){
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        items.save(item);
        return new ResponseEntity<Item>(item, HttpStatus.OK);
    }
    @RequestMapping(value = "/get-item", method = RequestMethod.GET)
    public ResponseEntity<Item> getSingleItem(@RequestParam("itemId")int itemId, HttpSession session){
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);

        return new ResponseEntity<Item>(items.findFirstByItemId(itemId), HttpStatus.OK);
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
    @RequestMapping(value = "/get-map", method = RequestMethod.GET)
    public HashMap<String, String> sendMapLink(@RequestParam("itemId")int itemId, HttpSession session) throws Exception {
        String username = (String) session.getAttribute("username");

        Item item = items.findFirstByItemId(itemId);
        String street = item.getUser().getUserDetail().getStreet();
        String city = item.getUser().getUserDetail().getState();
        String state = item.getUser().getUserDetail().getState();
        String zip = Integer.toString(item.getUser().getUserDetail().getZipcode());
        String address = String.format("%s %s, %s %s", street, city, state, zip);
        String mapInfo = getGeolocatioMapLink(address);
        HashMap<String, String> geoMap = new HashMap<>();
        geoMap.put("googleMap", mapInfo);
        return geoMap;
    }
}
