class Solution {
    public int partitionArray(int[] nums, int k) {
        // 1. First, let's sort the array! Think of it as organizing your cards before playing a game. 🎴
        Arrays.sort(nums);

        // 2. Get the length of the array. It's like counting how many cards you have. 🔢
        int count = 0, start = 0;

        // 3. Loop through each number in the sorted array. 🚶‍♀️
        for (int i = 0; i < nums.length; i++) {
            // 4. Check if the difference between the current number and the start of our subsequence is too big (greater than k). 🤔
            if (nums[i] - nums[start] > k) {
                // 5. If the difference is too big, it means we need a new subsequence! 🎉
                count++; // Increment the subsequence count.
                start = i; // Update the start index to the current number.
            }
        }

        // 6. Don't forget to count the last subsequence! We always need to count the last group. 🎁
        count++;

        // 7. Return the total number of subsequences. That's the answer! 🏆
        return count;
    }
}
