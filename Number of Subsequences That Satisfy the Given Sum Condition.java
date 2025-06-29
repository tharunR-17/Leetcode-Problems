import java.util.Arrays;

class Solution {
    public int numSubseq(int[] nums, int target) {
        final int MOD = 1000000007;
        int n = nums.length;

        // Step 1: Sort the array
        Arrays.sort(nums);

        // Step 2: Precompute powers of 2
        int[] pow2 = new int[n];
        pow2[0] = 1;
        for (int i = 1; i < n; i++) {
            pow2[i] = (pow2[i - 1] * 2) % MOD;
        }

        int left = 0, right = n - 1;
        int count = 0;

        // Step 3: Use two pointers
        while (left <= right) {
            if (nums[left] + nums[right] <= target) {
                count = (count + pow2[right - left]) % MOD;
                left++;
            } else {
                right--;
            }
        }

        return count;
    }
}
