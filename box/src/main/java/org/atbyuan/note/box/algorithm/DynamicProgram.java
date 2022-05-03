package org.atbyuan.note.box.algorithm;

/**
 * @author atbyuan
 * @since 2022/4/1 10:20
 */
public class DynamicProgram {

    public static void main(String[] args) {
        int[] nums = {2, 3, 2};
        System.out.println(house_robber_ii(nums));
    }

    /**
     * 打家劫舍 II
     *
     * @param nums 各个房子的价值
     * @return 可偷窃的最高金额
     * @see <a href="https://leetcode-cn.com/problems/house-robber-ii/">打家劫舍 II</a>
     */
    public static int house_robber_ii(int[] nums) {
        int len;
        if (nums == null || 0 == (len = nums.length)) {
            return 0;
        }
        int[] dp = new int[len];
        int[] pd = new int[len];

        if (len == 1) {
            return nums[0];
        }
        if (len == 2) {
            return Math.max(nums[0], nums[1]);
        }

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len - 1; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        pd[len - 1] = nums[len - 1];
        pd[len - 2] = Math.max(nums[len - 1], nums[len - 2]);
        for (int i = len - 3; i >= 1; i--) {
            pd[i] = Math.max(pd[i + 2] + nums[i], pd[i + 1]);
        }

        return Math.max(dp[len - 2], pd[1]);
    }
}
