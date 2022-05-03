package org.atbyuan.note.box.algorithm;

/**
 * @author atbyuan
 * @since 2022/4/2 12:47
 */
public class Arr {

    public static void main(String[] args) {
        int[][] grid = {{3, 0, 8, 4}, {2, 4, 5, 7}, {9, 2, 6, 3}, {0, 3, 1, 0}};


    }

    public static int maxIncreaseKeepingSkyline(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int[][] inc = new int[grid.length][grid[0].length];
        inc[0][0] = grid[0][0];

        int sum = inc[0][0];

        // 原点。Math.max({0行最大值}, {0列最大值})
        int max = grid[0][0];
        for (int i = 1; i < grid.length; i++) {
            max = Math.max(max, grid[i][0]);
        }
        for (int i = 1; i < grid[0].length; i++) {
            max = Math.max(max, grid[0][i]);
        }
        inc[0][0] = max;

        // 0列。Math.max({横向最大值}, 原点);
        for (int i = 1; i < grid.length; i++) {
            inc[i][0] = grid[i][0];
            for (int j = 1; j < grid[1].length; j++) {

            }
        }
        // 0行。Math.max({纵向最大值}, 原点);

        // 循环数组。Math.max(inc[0][j], inc[i][0]);


        return sum;
        // // 行数
        // int col = grid.length;
        // // 列数
        // int row = grid[0].length;
        //
        // int[] cols = new int[col];
        // int[] rows = new int[row];
        //
        // // 第一列
        // for(int i = 0; i < col; i++) {
        //     cols[0] = grid[i][0];
        //     for(int j = 1; j < row; j++) {
        //         cols[i] = Math.max(cols[i], grid[i][j]);
        //     }
        // }
        //
        // // 第一行
        // for(int i = 0; i < row; i++) {
        //     rows[0] = grid[0][i];
        //     for(int j = 1; j < col; j++) {
        //         rows[i] = Math.max(cols[i], grid[j][]);
        //     }
        // }

    }

}
