package com.techart.findpairs.controller;

import com.techart.findpairs.algorithm.Algorithm;
import com.techart.findpairs.model.User;
import com.techart.findpairs.model.UserPair;
import com.techart.findpairs.util.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class PairsController {

    @PutMapping("/findpairs")
    public List<String> maxRow(@Valid @RequestBody List<User> users)
    {
        List<String> pairToString = new ArrayList<>();
        int[][] matrix = Util.getMatrix(users);
        Algorithm algorithm = new Algorithm(matrix);
        algorithm.execute();

        return  Util.getUserPairs(users, algorithm.getResult());
    }
}
