package com.apurba.relevel.controller;

import com.apurba.relevel.model.*;
import com.apurba.relevel.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
public class userController {
    @Autowired
    userService user_service;
    /* user start */
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public int register(@RequestBody User user) {
        if(user_service.getUserById(user.getEmailid()) != null)
            return 400;
        if (user_service.addUser(user)) {
            return 200;
        }
        return 401;
    }

    


}
