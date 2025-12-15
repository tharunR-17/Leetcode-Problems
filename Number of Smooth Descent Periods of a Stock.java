class Solution {
    public long getDescentPeriods(int[] prices) {
        int n = prices.length;
        if (n == 1)
            return 1;

        int count = 1;
        long sum = 0;

        for (int i = 1; i < n; i++) {
            if (prices[i - 1] - prices[i] == 1) {
                count += 1;
            } else {
                long totalSubArray = ((long) count * (long) (count + 1)) / 2;
                sum += totalSubArray;
                count = 1;
            }
        }

        sum += ((long) count * (long) (count + 1)) / 2;

        return sum;
    }
}
