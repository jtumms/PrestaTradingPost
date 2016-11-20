package com.tummsmedia.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tummsmedia.entities.Item;
import com.tummsmedia.entities.User;
import com.tummsmedia.entities.UserDetail;
import com.tummsmedia.services.UserDetailRepo;
import com.tummsmedia.services.UserRepo;
import com.tummsmedia.utilities.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;

/**
 * Created by john.tumminelli on 11/16/16.
 */
@RestController
public class UserController {

    @Autowired
    UserRepo users;

    @Autowired
    UserDetailRepo userDetails;

    public Boolean isValidEmail(String username) {
        return username.matches("[a-zA-Z0-9\\.]+@[a-zA-Z0-9\\-\\_\\.]+\\.[a-zA-Z0-9]{3}");
    }


    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<User> postLogin(HttpSession session, @RequestBody User user) throws com.tummsmedia.utilities.PasswordStorage.InvalidHashException, com.tummsmedia.utilities.PasswordStorage.CannotPerformOperationException {
        User userFromDb = users.findFirstByUsername(user.getUsername());
        if (userFromDb == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        } else if (!com.tummsmedia.utilities.PasswordStorage.verifyPassword(user.getPassword(), userFromDb.getPassword())) {
            return new ResponseEntity<User>(HttpStatus.FORBIDDEN);
        }

        session.setAttribute("username", user.getUsername());
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(path = "/create-user", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user) throws PasswordStorage.CannotPerformOperationException {
        String username = user.getUsername();
        if (!isValidEmail(username)){
            return new ResponseEntity<User>(HttpStatus.FORBIDDEN);
        }
        user.setPassword(PasswordStorage.createHash(user.getPassword()));
        users.save(user);
        return new ResponseEntity<User> (user, HttpStatus.OK);
    }
    @RequestMapping(value = "/get-user/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getSingleUser(@PathVariable("id")int id, HttpSession session) {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<User>(users.findFirstById(id), HttpStatus.OK);
    }
    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
