class Solution {
    public int findLucky(int[] arr) {
        int result = -1;  // Initialize to -1

        int[] freq = new int[501];  // To count frequency of numbers

        // Count frequencies
        for (int i = 0; i < arr.length; i++) {
            freq[arr[i]]++;
        }

        // Find the largest lucky number
        for (int i = 1; i < freq.length; i++) {
            if (freq[i] == i) {
                result = i;  // Update if i is a lucky number
            }
        }

        return result;  // Return result at the end
    }
}
