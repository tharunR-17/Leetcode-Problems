class Solution {
    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        long base = 0, sumPos = 0;
        int cntPos = 0;
        long minPos = Long.MAX_VALUE;
        long bestNonpos = Long.MIN_VALUE;
        boolean sawNonpos = false;
        
        for (int x : nums) {
            base += x;
            long d = (long)(x ^ k) - x;
            if (d > 0) {
                cntPos++;
                sumPos += d;
                if (d < minPos) minPos = d;
            } else {
                if (!sawNonpos || d > bestNonpos) {
                    bestNonpos = d;
                    sawNonpos = true;
                }
            }
        }
        
        if ((cntPos & 1) == 0) {
            return base + sumPos;
        }
        long loss = minPos;
        if (sawNonpos) {
            loss = Math.min(loss, -bestNonpos);
        }
        return base + sumPos - loss;
    }
}
