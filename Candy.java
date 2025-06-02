// Technique: Candy Distribution
// Sorcerer: Gojo Satoru, disguised as a Java developer

class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;

        // Even weak sorcerers get one candy—minimum respect.
        int[] candies = new int[n];
        for (int i = 0; i < n; i++) candies[i] = 1;

        // Sweep forward: Acknowledge stronger ratings ahead.
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        // Sweep backward: Even Gojo doesn’t ignore what's behind him.
        int count = candies[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
            count += candies[i];
        }

        // Tally the domain's reward
        return count;
    }
}
