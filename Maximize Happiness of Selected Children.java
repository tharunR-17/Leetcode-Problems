class Solution {
    public long maximumHappinessSum(int[] happiness, int k) {
        Arrays.sort(happiness);
        int count = 0;
        long res = 0;
        for(int i=happiness.length-1;i>=happiness.length-k;i--){
            if(happiness[i] + count > 0){
                res+=(long)(happiness[i] + count);
            }
            count--;
        }

        return res;
    }
}
