package com.techart.findpairs.controller;

import com.techart.findpairs.model.User;
import com.techart.findpairs.util.Util;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class PairsController {

    @PutMapping("/user")
    public User getUser(@Valid @RequestBody User user)
    {
        return user;
    }

    @PutMapping("/findpairs")
    public int[][] findPairs(@Valid @RequestBody List<User> users)
    {
        return Util.getMatrix(users);
    }
}
