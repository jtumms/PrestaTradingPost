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
    public ResponseEntity<Object> postLogin(HttpSession session, @RequestBody User user) throws com.tummsmedia.utilities.PasswordStorage.InvalidHashException, com.tummsmedia.utilities.PasswordStorage.CannotPerformOperationException {
        User userFromDb = users.findFirstByUsername(user.getUsername());
        if (userFromDb == null) {
            return new ResponseEntity<Object>("User does not exist", HttpStatus.FORBIDDEN);
        } else if (!com.tummsmedia.utilities.PasswordStorage.verifyPassword(user.getPassword(), userFromDb.getPassword())) {
            return new ResponseEntity<Object>("Incorrect Password", HttpStatus.FORBIDDEN);
        }

        session.setAttribute("username", user.getUsername());
        return new ResponseEntity<Object>(userFromDb, HttpStatus.OK);
    }

    @RequestMapping(path = "/create-user", method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(@RequestBody User user, HttpSession session) throws PasswordStorage.CannotPerformOperationException {
        String username = user.getUsername();
        if (!isValidEmail(username)){
            return new ResponseEntity<Object>("Not a valid email address", HttpStatus.FORBIDDEN);
        }
        user.setPassword(PasswordStorage.createHash(user.getPassword()));
        users.save(user);
        session.setAttribute("username", user.getUsername());
        return new ResponseEntity<Object> (user, HttpStatus.OK);
    }
    @RequestMapping(value = "/get-user/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getSingleUser(@PathVariable("id")int id, HttpSession session) {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user == null) {
            return new ResponseEntity<Object>("Not logged in", HttpStatus.FORBIDDEN);
        }
        if (user.getId() != id){
            return new ResponseEntity<Object>("User tried to access profile other than his/her own profile", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<Object>(users.findFirstById(id), HttpStatus.OK);
    }
    @RequestMapping(path = "/logout", method = RequestMethod.DELETE)
    public ResponseEntity<Object> logout(HttpSession session) {
        String name = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(name);
        session.invalidate();
        return new ResponseEntity<Object> (user, HttpStatus.OK);
    }
    @RequestMapping(path = "/check-auth", method = RequestMethod.GET)
    public ResponseEntity<Object> checkAuth(HttpSession session) {
        String sessId = session.getId();
        if (session.getAttribute("username") != null){
            String name = (String) session.getAttribute("username");
            User user = users.findFirstByUsername(name);
            return new ResponseEntity<Object> (user, HttpStatus.OK);
        }
        return new ResponseEntity<Object>("No active user!!",HttpStatus.FORBIDDEN);
    }
}
