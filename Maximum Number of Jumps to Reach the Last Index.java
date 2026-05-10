class Solution {
    public int maximumJumps(int[] nums, int target) {

        int[] dp = new int[nums.length];

        // -1 means unreachable
        Arrays.fill(dp, -1);

        // starting index
        dp[0] = 0;

        for (int i = 0; i < nums.length; i++) {

            // skip unreachable indices
            if (i > 0 && dp[i] <= 0) {
                continue;
            }

            for (int j = i + 1; j < nums.length; j++) {

                // valid jump condition
                if (Math.abs(nums[i] - nums[j]) <= Math.abs(target)) {

                    // maximize number of jumps
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                }
            }
        }

        return dp[nums.length - 1];
    }
}
