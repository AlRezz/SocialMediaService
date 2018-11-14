package com.techart.findpairs.algorithm;

import com.techart.findpairs.model.User;
import com.techart.findpairs.util.Util;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AlgorithmTest {

    @Test(expected = IllegalArgumentException.class)
    public void matrixSizeTest()
    {
        int[][] matrix = new int[0][0];
        Algorithm algorithm = new Algorithm(matrix);
    }

    @Test
    public void algorithmWorkTest()
    {
        List<User> users = initUsersList();
        Algorithm algorithm = new Algorithm(Util.getMatrix(users));
        algorithm.execute();
        int pairsCount = Util.getUserPairs(users, algorithm.getResult()).size();
        assertEquals(2,pairsCount);
    }


    public List<User> initUsersList()
    {
        List<String> arr1 = new ArrayList<>();
        List<String> arr2 = new ArrayList<>();
        List<String> arr3 = new ArrayList<>();
        List<String> arr4 = new ArrayList<>();

        arr1.add("movies");
        arr1.add("music");
        User user1 = new User("Jane", arr1);

        arr2.add("cars");
        arr2.add("movies");
        User user2 = new User("Peter", arr2);

        arr3.add("movies");
        arr3.add("cars");
        arr3.add("hiking");
        arr3.add("music");
        User user3 = new User("Dustin", arr3);


        arr4.add("cars");
        arr4.add("dancing");
        User user4 = new User("Lana", arr4);

        List<User> userList = new ArrayList<User>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        return userList;
    }
}