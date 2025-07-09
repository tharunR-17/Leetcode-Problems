class Solution {
    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        int n = startTime.length;
        int[] gap = new int[n + 1];

        // Calculate gaps between events
        for (int i = 1; i < n; i++) {
            gap[i] = startTime[i] - endTime[i - 1];
        }
        gap[0] = startTime[0];                // Gap before the first meeting
        gap[n] = eventTime - endTime[n - 1];  // Gap after the last meeting

        int i = 0, j = k;
        int maxa = 0, sum = 0;

        // Initial window sum
        for (int w = i; w <= n && w <= j; w++) {
            sum += gap[w];
        }
        maxa = Math.max(maxa, sum);

        // Slide the window
        while (j < n) {
            sum -= gap[i++];
            sum += gap[++j];
            maxa = Math.max(maxa, sum);
        }

        return maxa;
    }
}
