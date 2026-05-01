class Solution {
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;

        long total = 0;
        for (int x : nums) total += x;

        long[] dp = new long[n];

        long f = 0;
        for (int i = 0; i < n; i++) {
            f += (long)i * nums[i];
        }

        dp[0] = f;

        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] + total - (long)n * nums[n - i];
        }

        long res = dp[0];
        for (int i = 1; i < n; i++) {
            res = Math.max(res, dp[i]);
        }

        return (int)res;
    }
}
