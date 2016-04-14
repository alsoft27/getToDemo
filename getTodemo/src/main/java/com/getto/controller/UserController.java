package com.getto.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.getto.domain.User;
import com.getto.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> create(@RequestBody User user) {
        service.saveUser(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = { "name" })
    public ResponseEntity<User> findByName(@RequestParam String name) {
        User user = service.getUserByName(name);
        ResponseEntity<User> res = null;
        if (user == null) {
            res = new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
        } else {
            res = new ResponseEntity<User>(user, HttpStatus.OK);
        }
        return res;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> list() {
        Iterable<User> res = service.listAllUsers();
        List<User> users = new ArrayList<User>();
        for (User user : res) {
            users.add(user);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
}
