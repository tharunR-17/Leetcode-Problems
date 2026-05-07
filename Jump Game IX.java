class Solution {
    public int[] maxValue(int[] nums) {
        int n = nums.length;

        int[] suffixMin = new int[n + 1];
        suffixMin[n] = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            suffixMin[i] = Math.min(nums[i], suffixMin[i + 1]);
        }

        int[] ans = new int[n];
        int l = 0;

        while (l < n) {
            int r = l;
            int componentMax = nums[l];

            while (r + 1 < n && componentMax > suffixMin[r + 1]) {
                r++;
                componentMax = Math.max(componentMax, nums[r]);
            }

            for (int i = l; i <= r; i++) {
                ans[i] = componentMax;
            }

            l = r + 1;
        }

        return ans;
    }
}
