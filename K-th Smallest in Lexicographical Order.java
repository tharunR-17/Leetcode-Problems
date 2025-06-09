class Solution {
    public int findKthNumber(long n, long k) {
        long curr = 1L;
        k--;
        while (k>0){
            long next = curr + 1L;
            long steps = getcount(n,curr,next);
            if (k>=steps){
                curr+=1;
                k = k - steps;
            }else{
                curr = curr*10;
                k--;
            }
        }
        return (int)curr;
    }
    public long getcount(long n, long curr , long next){
        long steps = 0L;
        while (curr<=n){
            steps += Math.min(n+1 , next) - curr;
            curr = curr*10;
            next = next*10;
        }
        return steps;
    }
}
