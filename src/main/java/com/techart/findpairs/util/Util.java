package com.techart.findpairs.util;

import com.techart.findpairs.model.User;

import java.util.ArrayList;
import java.util.List;

public class Util {

    private static final int SAME_USER = 9999;

    public static ArrayList<String> getEqualsInterests(List<String> arr1, List<String> arr2){
        List<String> result = new ArrayList<>();
        for (String str : arr1){
            if (arr2.contains(str)){
                result.add(str);
            }
        }
        return (ArrayList<String>) result;
    }

    public static int getCost(List<String> arr1, List<String> arr2){
        int i = 0;
        for (String str : arr1){
            if (arr2.contains(str)){
                i++;
            }
        }
        return i;
    }

    public static int[][] getMatrix(List<User> userList){
        int[][] costMatrix = new int[userList.size()][userList.size()];
        boolean [][] viseted = new boolean[userList.size()][userList.size()];

        for (int i = 0; i < userList.size(); i++) {
            for (int j = 0; j < userList.size(); j++) {
                if (!userList.get(i).getName().equals(userList.get(j).getName())) {
                    costMatrix[i][j] = Util.getCost(userList.get(i).getInterests(), userList.get(j).getInterests());

                } else {
                    costMatrix[i][j] = SAME_USER;
                }
            }
        }
        return costMatrix;
    }
}
