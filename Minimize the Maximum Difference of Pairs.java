class Solution {
    public int minimizeMax(int[] nums, int p) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums[nums.length - 1] - nums[0];
        while(left < right)
        {
            int mid = (left + right) / 2;
            if(CountAllPairs(nums , mid , p))
            {
                right = mid;
            }
            else
            {
                left = mid + 1;
            }
        }
    return left;}

    public boolean CountAllPairs(int [] nums , int mid , int p)
    {
        int count = 0;
        for(int i = 1 ; i < nums.length && count < p;)
        {
            if(nums[i] - nums[i - 1] <= mid)
            {
                count++;
                i += 2;
            }
            else
            {
                i++;
            }
        }
        return count >= p;
    }
}
