package com.techart.findpairs.algorithm;

import com.techart.findpairs.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Algorithm {

    private int[][] matrix;
    private int[][] values;
    private int[] rows;
    private int[] used;


    public Algorithm (int[][] matrix)
    {
        this.matrix = matrix;
        this.values = new int[this.matrix.length][this.matrix.length];
        this.rows = new int[this.values.length];
        this.used = new int[this.values.length];

    }

    public void prepare()
    {
        Util.setNegativeValues(matrix, values);
        int maxValue = Util.getMaxElement(values);
        Util.addMaxValue(values, maxValue);
        int[] rowsMinimal = Util.getRowMinimal(values);
        int[] colsMinimal = Util.getColMinimal(values);
        Util.subtractRowMinValue(values, rowsMinimal);
        Util.subtractRowMinValue(values, colsMinimal);
    }

    private boolean calculate(int row){
        if(row == rows.length) // If all rows were assigned a cell
            return true;

        for(int col = 0; col < values.length; col ++){ // Try all columns
            if(values[row][col] == 0 && used[col] == 0){ // If the current cell at column `col` has a value of zero, and the column is not reserved by a previous row
                rows[row] = col; // Assign the current row the current column cell
                used[col] = 1; // Mark the column as reserved
                if(calculate(row+1)) // If the next rows were assigned successfully a cell from a unique column, return true
                    return true;
                used[col] = 0; // If the next rows were not able to get a cell, go back and try for the previous rows another cell from another column
            }
        }
        return false; // If no cell were assigned for the current row, return false to go back one row to try to assign to it another cell from another column
    }

    public void execute()
    {
        prepare();
        calculate(0);
    }
    public int[] getResult()
    {
        return rows;
    }


    public String resultToString(int[] data)
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i =0; i < data.length; i++)
        {
            stringBuilder.append("Row " + (i+1) + " -> Col " + (data[i] +1) + " value = " + matrix[i][data[i]] +"\n");
        }
        return stringBuilder.toString();
    }
}
