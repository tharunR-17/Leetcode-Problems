import java.util.LinkedList; // Import the LinkedList class to create a queue
import java.util.Queue; // Import the Queue interface for working with queues

class Solution {
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        // status: array where 1 = box is initially open, 0 = box is initially closed
        // candies: array with the number of candies in each box
        // keys: 2D array where keys[i] is an array of keys to other boxes found in box i
        // containedBoxes: 2D array where containedBoxes[i] is an array of indices of boxes inside box i
        // initialBoxes: array of indices of the boxes we start with

        int n = status.length; // Number of boxes
        boolean[] hasBox = new boolean[n]; // Array indicating if we HAVE the box (not necessarily open)
        boolean[] opened = new boolean[n]; // Array indicating if the box is open
        Queue<Integer> q = new LinkedList<>(); // Queue to process the boxes
        int totalCandies = 0; // Total number of candies collected
        int boxesAvailable = 0; // Number of boxes we have but are initially locked

        // Initialize the boxes we start with
        for (int boxIndex : initialBoxes) {
            hasBox[boxIndex] = true; // We have this box
            if (status[boxIndex] == 1) { // If it's initially open
                q.offer(boxIndex); // Add it to the queue for processing
            } else {
                boxesAvailable++; // Otherwise, increment the count of locked but available boxes
            }
        }

        // Main loop to process the boxes
        while (!q.isEmpty()) {
            int boxIndex = q.poll(); // Get a box from the queue

            if (opened[boxIndex]) continue; // If it's already open, skip it

            opened[boxIndex] = true; // Open the box
            totalCandies += candies[boxIndex]; // Add the candies to our total

            // Get the keys from this box
            for (int key : keys[boxIndex]) {
                if (status[key] == 0) { // If the box with this key is initially closed
                    status[key] = 1; // Unlock it (as if we found a key)
                    if (hasBox[key]) { // If we HAVE this box
                        q.offer(key); // Add it to the queue for processing
                        boxesAvailable--; // Decrement the count of locked boxes
                    }
                }
            }

            // Get the boxes contained within this box
            for (int containedBox : containedBoxes[boxIndex]) {
                if (!hasBox[containedBox]) { // If we don't have this box yet
                    hasBox[containedBox] = true; // Now we have it
                    if (status[containedBox] == 1) { // If it's initially open
                        q.offer(containedBox); // Add it to the queue for processing
                    } else {
                        boxesAvailable++; // Increment the count of locked boxes
                    }
                }
            }

            // If the queue is empty but we have locked boxes, try to unlock them
            if (q.isEmpty() && boxesAvailable > 0) {
                boolean canOpen = false; // Flag to indicate if we opened at least one box
                for (int i = 0; i < n; i++) {
                    if (hasBox[i] && !opened[i] && status[i] == 1) { // If we HAVE the box, it's not open, and it's unlocked by status
                        q.offer(i); // Add it to the queue
                        canOpen = true; // Indicate we opened a box
                        boxesAvailable--; // Decrement the count of locked boxes
                    }
                }
                if (!canOpen) return totalCandies; // If we couldn't open any boxes, return the result
            }
        }

        return totalCandies; // Return the total number of candies collected
    }
}
