package com.techart.findpairs.algorithm;

import java.awt.*;
import java.util.*;
import java.util.List;


public class HungarianAlgorithm {
    private int[][] in;
    private int[][] mat;
    private int[][] costMat;
    private int minCost;
    private int maxCost;
    private int dim;
    private List<Point> zeros;

    public HungarianAlgorithm(int[][] mat) {
        if(mat.length != mat[0].length) {
            System.err.println("Error: Unmatched dimensions");
            dim = Math.min(mat[0].length, mat.length);
        }
        dim = mat.length;
        in = new int[dim][];
        this.mat = new int[dim][];
        for(int i = 0; i < dim; i++) {
            in[i] = Arrays.copyOf(mat[i], mat[i].length);
            this.mat[i] = Arrays.copyOf(mat[i], mat[i].length);
        }
    }

    private void reduce() {
        boolean[] columnZero = new boolean[dim];
        // reduce row
        for(int i = 0; i < dim; i++) {
            int min = Integer.MAX_VALUE;
            for(int j = 0; j < dim; j++) {
                min = Math.min(min, mat[i][j]);
            }
            for(int j = 0; j < dim; j++) {
                mat[i][j] -= min;
                if(mat[i][j] == 0) columnZero[j] = true;
            }
        }
        // reduce column
        for(int i = 0; i < dim; i++) {
            if(!columnZero[i]) {
                int min = Integer.MAX_VALUE;
                for(int j = 0; j < dim; j++) {
                    min = Math.min(min, mat[j][i]);
                }
                for(int j = 0; j < dim; j++) {
                    mat[j][i] -= min;
                }
            }
        }
    }

    private void coveredByZeros() {
        int count = 0;
        while(count < dim) {
            count = 0;
            int[][] coverMat = new int[dim][dim];
            int[] xLut = new int[dim];
            int[] yLut = new int[dim];
            for(int i = 0; i < dim; i++) {
                for(int j = 0; j < dim; j++) {
                    if(mat[i][j] == 0) {
                        xLut[j]++;
                        yLut[i]++;
                    }
                }
            }
            minCost = Integer.MAX_VALUE;
            maxCost = Integer.MIN_VALUE;
            costMat = new int[dim][dim];
            for(int i = 0; i < dim; i++) {
                for(int j = 0; j < dim; j++) {
                    if(mat[i][j] == 0) {
                        costMat[i][j] = xLut[j] + yLut[i] -1;
                        minCost = (minCost > costMat[i][j]) ? costMat[i][j] : minCost;
                        maxCost = (maxCost < costMat[i][j]) ? costMat[i][j] : maxCost;
                    } else
                        costMat[i][j] = -1;
                }
            }

            for(int c = minCost; c <= maxCost; c++) {
                for (int i = 0; i < dim; i++) {
                    for (int j = 0; j < dim; j++) {
                        if (costMat[i][j] == c && coverMat[i][j] == 0 && mat[i][j] == 0) {
                            if (yLut[i] <= xLut[j]) {
                                for (int ii = 0; ii < dim; ii++)
                                    coverMat[ii][j]++;
                                xLut[j] = 0;
                            } else {
                                for (int jj = 0; jj < dim; jj++)
                                    coverMat[i][jj]++;
                                yLut[i] = 0;
                            }
                            count++;
                        }
                    }
                }
            }
            if (count < dim) {
                int min = Integer.MAX_VALUE;
                for (int i = 0; i < dim; i++) {
                    for (int j = 0; j < dim; j++) {
                        if (coverMat[i][j] == 0 && min > mat[i][j])
                            min = mat[i][j];
                    }
                }
                for (int i = 0; i < dim; i++) {
                    for (int j = 0; j < dim; j++) {
                        if (coverMat[i][j] == 0)
                            mat[i][j] -= min;
                        if (coverMat[i][j] > 1)
                            mat[i][j] += min;
                    }
                }
            }
        }
    }

    private void maxMatching() {
        zeros = new ArrayList<>();
        for(int c = minCost; c <= maxCost; c++) {
            for (int i = 0; i < dim; i++) {
                for (int j = 0; j < dim; j++) {
                    if (costMat[i][j] == c) {
                        zeros.add(new Point(j, i));
                        Arrays.fill(costMat[i], -1);
                        for(int ii = 0; ii < dim; ii++)
                            costMat[ii][j] = -1;
                    }
                }
            }
        }
    }

    private int getMin() {
        int min = 0;
        for(Point p : zeros)
            min += in[p.y][p.x];
        return min;
    }

    public int evaluate() {
        reduce();
        coveredByZeros();
        maxMatching();
        return getMin();
    }

    public static void main(String[] args) {
        int[][] mat = {{6, 12, 15, 15},
                {4, 8, 9, 11},
                {10, 5, 7, 8},
                {12, 10, 6, 9}};    // ans: 28
//        int[][] mat = { {250, 400, 350},
//                {400, 600, 350},
//                {200, 400, 250}};   // ans: 950
//        int[][] mat = {{90, 75, 75, 80},
//                {35, 85, 55, 65},
//                {125, 95, 90, 105},
//                {45, 110, 95, 115 }}; // ans: 275
        HungarianAlgorithm ha = new HungarianAlgorithm(mat);
        System.out.println("min = " + ha.evaluate());
    }
}
