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
        StringBuilder stringBuilder = new StringBuilder();
        int[][] matrix = Util.getMatrix(users);
        Algorithm algorithm = new Algorithm(matrix);
        algorithm.execute();
        int [] result = algorithm.getResult();
        for (int i =0; i < result.length; i++)
        {
            stringBuilder.append(users.get(i).getName() + " -  " + users.get(result[i]).getName() + " interests = "
                    + Util.getEqualsInterests(users.get(i).getInterests(), users.get(result[i]).getInterests()) +"\n");
        }
        return stringBuilder.toString();
    }
}
