package com.techart.findpairs.controller;

import com.techart.findpairs.algorithm.Algorithm;
import com.techart.findpairs.model.User;
import com.techart.findpairs.util.Util;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class PairsController {

    @PutMapping("/findpairs")
    public int[][] findPairs(@Valid @RequestBody List<User> users)
    {
        return Util.getMatrix(users);
    }

    @PutMapping("/values")
    public String maxRow(@Valid @RequestBody List<User> users)
    {
        int[][] matrix = Util.getMatrix(users);
        Algorithm algorithm = new Algorithm(matrix);
        algorithm.execute();
        return algorithm.resultToString(algorithm.getResult());
    }
}
