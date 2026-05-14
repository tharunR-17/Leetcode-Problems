class Solution {
    public boolean isGood(int[] nums) {
        int mx = 0;

        for (int x : nums) {
            mx = Math.max(mx, x);
        }

        // size must be mx + 1
        if (nums.length != mx + 1)
            return false;

        int[] freq = new int[mx + 1];

        for (int x : nums) {
            // invalid number
            if (x < 1 || x > mx)
                return false;

            freq[x]++;
        }

        // 1 to mx-1 should appear once
        for (int i = 1; i < mx; i++) {

            if (freq[i] != 1)
                return false;
        }

        // mx should appear twice
        return freq[mx] == 2;
    }
}
