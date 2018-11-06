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
    private List<Integer> viseted = new ArrayList<>();


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
        Util.subtractRowMinValue(values, rowsMinimal);
        int[] colsMinimal = Util.getColMinimal(values);
        Util.subtractColMinValue(values, colsMinimal);
    }

    private boolean calculate(int row){
        if(row == rows.length)
            return true;
        if (!viseted.contains(row))
        {
            viseted.add(row);
            for(int i = 0; i < values.length; i ++){
                if(values[row][i] == 0 && used[i] == 0){
                    rows[row] = i;
                    used[i] = 1;
                    viseted.add(i);
                    if(calculate(row+1))
                        return true;
                    used[i] = 0;
                }
            }
        }
        else{
            rows[row] = -1;
            calculate(row+1);
        }

        return false;
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
}
