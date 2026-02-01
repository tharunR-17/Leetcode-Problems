class Solution {
    public int minimumCost(int[] nums) {
        int startElement = nums[0];
        int firstMin = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;
        for(int i = 1; i < nums.length; i++){
            if(firstMin > nums[i]){
                secondMin = firstMin;
                firstMin = nums[i];
            } else if (nums[i] < secondMin) {
                secondMin = nums[i];
            }
        }

        return firstMin + secondMin + startElement;
    }
}
