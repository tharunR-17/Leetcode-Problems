class Solution {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        int n = nums.length;
        List<Integer> idx = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        for(int i=0;i<n; i++){
            if(nums[i] == key) idx.add(i);
        }
        
        for(int i=0; i<n; i++){

            for(int j=0; j<idx.size(); j++){
                if(Math.abs(i-idx.get(j))<=k){
                    list.add(i);
                    break;
                }
            }

        } 

        return list;
    }
}
