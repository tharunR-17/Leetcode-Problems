class Solution {

    // DIRS: These are the four possible diagonal directions we can move.
    // The order is important! It dictates how we find the next direction after a turn.
    // If we are currently moving in direction k, the next direction after a 90-degree clockwise turn is (k + 1) % 4.
    // Example: If we are moving down-right (k=0), a turn leads us up-right (k=1).
    private static final int[][] DIRS = {{1, 1}, {1, -1}, {-1, -1}, {-1, 1}}; // {dr, dc} -> {change in row, change in column}

    /**
     * The main function that finds the longest V-shaped diagonal segment.
     *
     * @param grid The 2D map with 0s, 1s, and 2s.
     * @return The length of the longest V-shaped diagonal segment.
     */
    public int lenOfVDiagonal(int[][] grid) {
        int m = grid.length;      // Total number of rows on the map.
        int n = grid[0].length;   // Total number of columns on the map.

        // memo[i][j][state]: This is our "memory". It stores the result of a DFS call
        // starting at cell (i, j) with a specific 'state'.
        // The 'state' is a clever way to encode two things:
        // 1. The current direction of movement (k: 0 to 3).
        // 2. Whether we are still allowed to make a turn (canTurn: 0 or 1).
        // We pack these into a single integer 'mask' using bitwise operations.
        // 'k << 1' shifts the direction bits, and '| canTurn' adds the turn flag.
        // So, memo[i][j][(k << 1) | canTurn] stores the max length from this state.
        int[][][] memo = new int[m][n][8]; // 8 states: 4 directions * 2 possibilities for canTurn.

        int maxLen = 0; // This variable will keep track of the longest V-shape we find.

        // We need to start our search from every cell that contains a '1',
        // as '1' is the mandatory starting point of a V-shape.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If the current cell is not a '1', it can't be the start of our V-shape, so we skip it.
                if (grid[i][j] != 1) {
                    continue;
                }

                // If we found a '1', it's a potential starting point.
                // From this '1', we can potentially start moving in any of the 4 diagonal directions.
                for (int k = 0; k < 4; k++) {
                    // We call our DFS helper function to explore paths starting from (i, j)
                    // in direction 'k'.
                    // Initial state:
                    // - (i, j): Starting cell.
                    // - k: The initial direction (0 to 3).
                    // - canTurn: 1 (we are allowed to make a turn initially).
                    // - target: 2 (the value we expect immediately after '1').
                    // - grid: The map.
                    // - memo: Our memory array.
                    
                    // The DFS returns the length of the path *after* the starting '1'.
                    // So, we add 1 to include the starting '1' itself.
                    // We update maxLen if this path is longer than any we've found so far.
                    maxLen = Math.max(maxLen, dfs(i, j, k, 1, 2, grid, memo) + 1);
                }
            }
        }
        return maxLen; // Finally, return the longest V-shape found.
    }

    /**
     * The recursive Depth-First Search function.
     * It explores paths starting from (i, j) in direction 'dir', looking for 'target' value,
     * and considering if a turn is allowed.
     *
     * @param i Current row.
     * @param j Current column.
     * @param dir Current direction index (0-3 in DIRS array).
     * @param canTurn Flag: 1 if a turn is allowed, 0 if not.
     * @param target The value we expect to find in the next cell (either 0 or 2).
     * @param grid The treasure map.
     * @param memo The memoization table to store computed results.
     * @return The maximum length of the V-shaped path starting from (i, j) with the given state.
     */
    private int dfs(int i, int j, int dir, int canTurn, int target, int[][] grid, int[][][] memo) {
        // Calculate the coordinates of the next cell based on the current direction.
        i += DIRS[dir][0]; // Move to the next row.
        j += DIRS[dir][1]; // Move to the next column.

        // --- Base Cases (When to stop exploring this path) ---
        // 1. Check if we've gone out of bounds of the grid.
        // 2. Check if the value in the next cell is NOT what we are looking for ('target').
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] != target) {
            return 0; // If any of these conditions are met, this path ends here. Return 0 length for this step.
        }

        // --- Memoization: Have we been here with this state before? ---
        // We create a 'state' integer by combining the current direction ('dir') and the 'canTurn' flag.
        // 'dir << 1' shifts the direction bits to the left (e.g., 00 becomes 000, 01 becomes 010).
        // '| canTurn' combines this with the turn flag (0 or 1).
        // This gives us a unique identifier for our current situation (position + direction + turn status).
        int state = (dir << 1) | canTurn;
        // If we've already computed the result for this (i, j, state), we just return it from the memo table.
        if (memo[i][j][state] != 0) {
            return memo[i][j][state]; // Return the cached result.
        }

        // --- Recursive Steps: Explore possibilities ---
        // We found the 'target' value at (i, j)! Now we need to see how far we can go from here.

        // Possibility 1: Continue in the SAME direction ('dir').
        // We need to look for the NEXT value in the sequence (0 if target was 2, or 2 if target was 0).
        // We call dfs recursively for the next step in the same direction.
        // 'canTurn' remains the same because we haven't used our turn yet.
        int res = dfs(i, j, dir, canTurn, 2 - target, grid, memo); // Note: 2 - target cleverly switches between 0 and 2.

        // Possibility 2: Make a TURN (if we are allowed to).
        // If 'canTurn' is 1, it means we haven't used our turn yet.
        if (canTurn == 1) {
            // Find the next direction after a 90-degree clockwise turn.
            // If current direction index is 'dir', the next one is '(dir + 1) % 4'.
            int nextDir = (dir + 1) % 4;

            // Now, we explore the path starting from the current cell (i, j) BUT in the NEW direction ('nextDir').
            // We are still looking for the SAME 'target' value in the next step.
            // Crucially, 'canTurn' becomes 0 because we have now used our one allowed turn.
            res = Math.max(res, dfs(i, j, nextDir, 0, 2 - target, grid, memo));
        }

        // --- Memoization & Return ---
        // We've explored both possibilities (continue straight or turn and continue).
        // 'res' now holds the maximum length from the NEXT step onwards.
        // To get the total length *including* the current cell (i, j), we add 1.
        // We store this result in our memo table for future use and then return it.
        return memo[i][j][state] = res + 1;
    }
}
