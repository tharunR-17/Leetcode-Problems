class Solution {
    public long minimumCost(int[] nums, int k, int dist) {
        int n = nums.length;
        int targetK = k - 1;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int m = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || sorted[i] != sorted[i - 1]) sorted[m++] = sorted[i];
        }

        long[] bitSum = new long[m + 1];
        int[] bitCount = new int[m + 1];
        int maxPow2 = Integer.highestOneBit(m);

        long minExtra = Long.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int rank = Arrays.binarySearch(sorted, 0, m, nums[i]) + 1;
            update(rank, nums[i], 1, bitSum, bitCount, m);
            if (i > dist + 1) {
                int oldRank = Arrays.binarySearch(sorted, 0, m, nums[i - dist - 1]) + 1;
                update(oldRank, -nums[i - dist - 1], -1, bitSum, bitCount, m);
            }
            if (i >= targetK) {
                minExtra = Math.min(minExtra, query(targetK, bitSum, bitCount, sorted, m, maxPow2));
            }
        }
        return nums[0] + minExtra;
    }

    private void update(int idx, int val, int cnt, long[] bSum, int[] bCnt, int m) {
        for (; idx <= m; idx += idx & -idx) {
            bSum[idx] += val;
            bCnt[idx] += cnt;
        }
    }

    private long query(int k, long[] bSum, int[] bCnt, int[] sorted, int m, int maxPow2) {
        int idx = 0, curCnt = 0;
        long curSum = 0;
        for (int i = maxPow2; i > 0; i >>= 1) {
            if (idx + i <= m && curCnt + bCnt[idx + i] < k) {
                idx += i;
                curCnt += bCnt[idx];
                curSum += bSum[idx];
            }
        }
        if (curCnt < k) curSum += (long) (k - curCnt) * sorted[idx];
        return curSum;
    }
}
