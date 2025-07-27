class Solution {
    public int countHillValley(int[] nums) {
        int n = nums.length;
        int prev = nums[0];
        int ans = 0;
        for(int i = 1;i<n-1;i++){
            if(nums[i] == nums[i+1]) continue;
            if((nums[i] > prev && nums[i] > nums[i+1]) ||( nums[i] < prev && nums[i] < nums[i+1] )){
                ans++;
            }
            prev = nums[i];
        }
        return ans;
    }
}
