package org.atbyuan.note.box.algorithm;


import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 回溯算法
 *
 * @author atbyuan
 * @since 2022/3/31 16:19
 */
public class Backtrack {

    public static void main(String[] args) {
        // 全排列
        String orgStr = "ABC";
        List<List<Character>> res = new ArrayList<>();
        fulLArrangement(orgStr, res, null, 0);
        System.out.println("全排列: " + res);

        // 组合总数2
        System.out.println("组合总数2: " + combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));

        // 组合总数3
        System.out.println("组合总数3: " + combinationSum3(3, 9));

    }

    /**
     * 全排列
     *
     * @param orgStr 原始字符串
     * @param res    结果集
     * @param branch 当前选择分支
     * @param depth  当前选择分支深度
     */
    public static void fulLArrangement(String orgStr, List<List<Character>> res, List<Character> branch, int depth) {
        if (branch != null && branch.size() == orgStr.length()) {
            res.add(new ArrayList<>(branch));
        }
        if (branch == null) {
            branch = Lists.newArrayList();
        }
        for (int i = 0; i < orgStr.length(); i++) {
            if (!branch.contains(orgStr.charAt(i))) {
                branch.add(orgStr.charAt(i));
                fulLArrangement(orgStr, res, branch, depth + 1);
                branch.remove(branch.size() - 1);
            }
        }
    }


    /**
     * @see <a href="https://leetcode-cn.com/problems/combination-sum-ii/">
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();

        // Arrays.sort(candidates);
        boolean[] used = new boolean[candidates.length];
        dfs2(res, new ArrayList<>(), candidates, target, 0, 0);

        return res;
    }

    public static void dfs2(List<List<Integer>> res, List<Integer> branch, int[] candidates, int target, int sum, int position) {
        if (sum == target && !res.contains(branch)) {
            res.add(new ArrayList<>(branch));
        }
        for (int i = position; i < candidates.length; i++) {
            if (sum + candidates[i] > target) {
                continue;
            }


            branch.add(candidates[i]);
            dfs2(res, branch, candidates, target, sum + candidates[i], i + 1);
            branch.remove(branch.size() - 1);
        }
    }

    /**
     * @see <a href="https://leetcode-cn.com/problems/combination-sum-iii/">
     */
    public static List<List<Integer>> combinationSum3(int k, int n) {
        // 初始过滤筛选
        int x = k;
        int y = 9;
        int sum = 0;
        while (x-- > 0) {
            sum += y--;
        }
        if (sum < n) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        dfs3(res, new ArrayList<>(), k, n, 1, 0);
        return res;
    }

    public static void dfs3(List<List<Integer>> res, List<Integer> branch, int k, int n, int position, int sum) {
        if (branch.size() == k && sum == n) {
            res.add(new ArrayList<>(branch));
        }
        for (int i = position; i <= 9; i++) {
            // 分支选择时，不能重复
            if (branch.contains(i)) {
                continue;
            }
            // 分支选择时，大小不能超过 n，个数不能超过 k
            if (sum + i > n && branch.size() > k) {
                break;
            }
            branch.add(i);
            dfs3(res, branch, k, n, i + 1, sum + i);
            branch.remove(branch.size() - 1);
        }
    }


    public static class LcList<T> extends ArrayList<T> {

        static List<String> list = new ArrayList<>();

        @Override
        public boolean add(T e) {
            if (e instanceof List) {
                List<Integer> val = (List<Integer>) e;
                list.add(val.stream().sorted().collect(Collectors.toList()).toString());
            }
            return super.add(e);
        }

        @Override
        public boolean contains(Object o) {
            if (o instanceof List) {
                List<Integer> val = (List<Integer>) o;
                return list.contains(val.stream().sorted().collect(Collectors.toList()).toString());
            }
            return false;
        }
    }
}
