package com.tummsmedia.controllers;

import com.tummsmedia.entities.Item;
import com.tummsmedia.entities.ItemWrapper;
import com.tummsmedia.entities.User;
import com.tummsmedia.services.ItemRepo;
import com.tummsmedia.services.UserRepo;
import jodd.json.JsonParser;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by john.tumminelli on 11/16/16.
 */
@RestController
public class ItemController {
    @Autowired
    ItemRepo items;

    @Autowired
    UserRepo users;

    Server h2;

    @PostConstruct
    public void init() throws SQLException, com.tummsmedia.utilities.PasswordStorage.CannotPerformOperationException, IOException {
        h2 = Server.createWebServer().start();

        if (items.count() == 0) {
            loadUser();
            loadItemData();
        }
    }
    @PreDestroy
    public void destroy() {
        h2.stop();
    }
    public void loadItemData() throws IOException {

        File f = new File("ptp_item_data_json");
        FileReader fr = new FileReader(f);
        int fileSize = (int) f.length();     //cast to int
        char[] contents = new char[fileSize];
        fr.read(contents, 0, fileSize);
        JsonParser parser = new JsonParser();
        ItemWrapper itemWrapper = parser.parse(contents, ItemWrapper.class);
        items.save(itemWrapper.items);
    }

    public void loadUser() throws IOException {
        File f = new File("ptp_user_data_json");
        FileReader fr = new FileReader(f);
        int fileSize = (int) f.length();
        char[] contents = new char[fileSize];
        fr.read(contents, 0, fileSize);
        JsonParser parser = new JsonParser();
        User user = parser.parse(contents, User.class);
        users.save(user);
    }

    @RequestMapping(path = "/items", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Item>> getItems(HttpSession session) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            return new ResponseEntity<Iterable<Item>>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<Iterable<Item>>(items.findAll(), HttpStatus.OK);

    }
}
