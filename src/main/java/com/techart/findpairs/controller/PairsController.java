package com.techart.findpairs.controller;

import com.techart.findpairs.algorithm.Algorithm;
import com.techart.findpairs.model.User;
import com.techart.findpairs.util.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<String> maxRow(@Valid @RequestBody List<User> users)
    {
        String pairToString = "";
        int[][] matrix = Util.getMatrix(users);
        Algorithm algorithm = new Algorithm(matrix);
        algorithm.execute();
        try
        {
            pairToString = Util.pairToString(users, algorithm.getResult());
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Error");
        }
        return ResponseEntity.ok(pairToString);
    }
}
