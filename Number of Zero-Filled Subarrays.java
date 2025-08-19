class Solution {
    public long zeroFilledSubarray(int[] Nums) {
        ArrayList<Integer> nums = new ArrayList<>();
        for(int num:Nums){
            nums.add(num);
        }
        nums.add(-1);
        long count = 0;
        int start = -1;
        for(int i =0;i<nums.size();i++){
            if(nums.get(i) == 0 && start==-1){
                start = i;
            }
            if(nums.get(i)!=0 && start!=-1){
                int n = i-start;
                start = -1;
                count += (long)n*(n+1)/2;
            }
        }
        return count;
    }
}
