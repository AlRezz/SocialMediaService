package com.techart.findpairs.algorithm;

import com.techart.findpairs.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Algorithm
{

    private int[][] matrix;
    private int[][] values;
    private int[] rows;
    private int[] used;
    private List<Integer> viseted = new ArrayList<>();
    private int numLines;
    private int[][] lines;


    public Algorithm (int[][] matrix)
    {
        if (matrix.length == 0)
        {
            throw new IllegalArgumentException("Matrix size should be greater then 0");
        }
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

    private boolean calculate(int row)
    {
        if(row == rows.length)
            return true;
        if (!viseted.contains(row))
        {
            viseted.add(row);
            for(int i = 0; i < values.length; i ++)
            {
                if(values[row][i] == 0 && used[i] == 0)
                {
                    rows[row] = i;
                    used[i] = 1;
                    viseted.add(i);
                    if(calculate(row+1))
                        return true;
                    used[i] = 0;
                }
            }
        }
        else
        {
            rows[row] = -1;
            calculate(row+1);
        }

        return false;
    }

    public void execute()
    {
        prepare();
        countLinesWithZeroValue();
        if (numLines < values.length)
        {
            createAdditionalZeros();
            countLinesWithZeroValue();
        }
        calculate(0);
    }
    public int[] getResult()
    {
        return rows;
    }

    private void countLinesWithZeroValue()
    {
        numLines = 0;
        lines = new int[values.length][values.length];

        for(int i = 0; i < values.length; i ++)
        {
            for(int j = 0; j < values.length; j ++)
            {
                if(values[i][j] == 0)
                    markCell(i, j, checkDirection(i, j));
            }
        }
    }

    private int checkDirection(int row, int col)
    {
        int result = 0;
        for(int i=0; i<values.length;i++)
        {
            if(values[i][col] == 0)
                result++;
            if(values[row][i] == 0)
                result--;
        }
        return result;
    }

    private void markCell(int row, int col, int maxVH)
    {
        if(lines[row][col] == 2)
            return;

        if(maxVH > 0 && lines[row][col] == 1)
            return;

        if(maxVH <= 0 && lines[row][col] == -1)
            return;

        for(int i=0; i<values.length;i++){
            if(maxVH > 0)
                lines[i][col] = lines[i][col] == -1 || lines[i][col] == 2 ? 2 : 1;
            else
                lines[row][i] = lines[row][i] == 1 || lines[row][i] == 2 ? 2 : -1;
        }

        numLines++;
    }

    public void createAdditionalZeros()
    {
        int minUncoveredValue = 0;

        for(int i = 0; i < values.length; i ++)
        {
            for(int j =0; j < values.length; j ++)
            {
                if(lines[i][j] == 0 && (values[i][j] < minUncoveredValue || minUncoveredValue == 0))
                    minUncoveredValue = values[i][j];
            }
        }

        for(int k = 0; k <values.length; k ++)
        {
            for(int n = 0; n < values.length; n++)
            {
                if(lines[k][n] == 0)
                {
                    values[k][n] -= minUncoveredValue;
                }
                else if(lines[k][n] == 2)
                {
                    values[k][n] += minUncoveredValue;
                }
            }
        }
    }
}
