package com.techart.findpairs.controller;

import com.techart.findpairs.algorithm.Algorithm;
import com.techart.findpairs.model.User;
import com.techart.findpairs.util.Util;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/")
public class PairsController {

    @PutMapping("/findpairs")
    public List<String> findPairs(@Valid @RequestBody List<User> users)
    {
        if(isNull(users))
        {
            throw new IllegalArgumentException("List can not be empty");
        }
        Algorithm algorithm = new Algorithm(Util.getMatrix(users));
        algorithm.execute();

        return  Util.getUserPairs(users, algorithm.getResult());
    }
}
