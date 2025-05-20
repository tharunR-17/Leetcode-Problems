class Solution {
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int[] sweep = new int[nums.length+1];

        for(int i=0; i<queries.length; i++) {
            sweep[queries[i][0]] -= 1;
            sweep[queries[i][1] + 1] += 1;
        }

        int sweepSum = 0;
        for(int i=0; i<nums.length; i++) {
            sweepSum += sweep[i];
            if(Math.max(nums[i]+sweepSum, 0) != 0) return false;
        }
        return true;
    }
}
