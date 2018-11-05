package com.techart.findpairs.util;

import com.techart.findpairs.model.User;

import java.util.ArrayList;
import java.util.List;

public class Util {

    private static final int SAME_USER = 0;

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

    public static int[] getRowMinimal(int[][] values)
    {
        int rowMinValues[] = new int[values.length];
        for(int row=0; row<values.length;row++){
            rowMinValues[row] = values[row][0];
            for(int col=1; col<values.length;col++){
                if(values[row][col] < rowMinValues[row] && row != col)
                    rowMinValues[row] = values[row][col];
            }
        }
        System.out.println("row min is " + rowMinValues);
        return rowMinValues;
    }

    public static int[] getColMinimal(int[][] values)
    {
        int colMinValues[] = new int[values.length];
        for(int col=0; col<values.length;col++){
            colMinValues[col] = values[0][col];
            for(int row=1; row < values.length; row++){
                if(values[row][col] < colMinValues[col] && row != col)
                    colMinValues[col] = values[row][col];
            }
        }
        System.out.println("col min is " + colMinValues);
        return colMinValues;
    }

    public static int[][] cloneMatrix(int[][] matrix){
        int[][] newMatrix = new int[matrix.length][matrix.length];
        for(int row = 0; row < matrix.length; row++){
            newMatrix[row] = matrix[row].clone();
        }
        return newMatrix;
    }

    public static void setNegativeValues(int[][] original, int[][] result)
    {
        for (int i = 0; i < result.length; i++)
        {
            for (int j = 0; j < result.length; j++)
            {
                result[i][j] = -1 * original[i][j];
            }
        }
    }

    public static void subtractRowMinValue(int[][] values, int[] rowMinValues)
    {
        for (int i = 0; i < values.length; i++)
        {
            for (int j = 0; j < values.length; j++)
            {
                values[i][j] -= rowMinValues[i];
            }
        }
    }

    public static void subtractColMinValue(int[][] values, int[] colMinValues)
    {
        for (int i = 0; i < values.length; i++)
        {
            for (int j = 0; j < values.length; j++)
            {
                values[i][j] -= colMinValues[i];
            }
        }
    }
    public static int getMaxElement(int[][] values)
    {
        int max = Math.abs(values[0][0]);
        for (int i = 0; i < values.length; i++)
        {
            for (int j = 0; j < values[i].length; j++)
            {
                if (Math.abs(values[i][j]) > max)
                {
                    max = Math.abs(values[i][j]);
                }
            }
        }
        return max;
    }
    public static void markTheSameElements(int[][] values)
    {
        for (int i = 0; i < values.length; i++)
        {
            for (int j = 0; j < values.length; j++)
            {
                if (i ==j)
                    values[i][j] = SAME_USER;
            }
        }
    }
    public static void addMaxValue(int[][] values, int maxValue)
    {
        for (int i = 0; i < values.length; i++)
        {
            for (int j = 0; j < values.length; j++)
            {
                if (i != j)
                    values[i][j] += maxValue;
            }
        }
    }
}
