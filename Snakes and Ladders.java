class Solution {
     public int snakesAndLadders(int[][] board) {
        int n = board.length;
        boolean[] visited = new boolean[n * n + 1];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{1, 0}); // {position, moves}
        visited[1] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int pos = curr[0];
            int moves = curr[1];

            if (pos == n * n) return moves;

            for (int i = 1; i <= 6 && pos + i <= n * n; i++) {
                int next = pos + i;
                int[] coords = getCoordinates(next, n);
                int r = coords[0], c = coords[1];

                if (board[r][c] != -1) {
                    next = board[r][c];
                }

                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(new int[]{next, moves + 1});
                }
            }
        }
        return -1; // Not reachable
    }

    // Helper to convert square number to row and column
    private int[] getCoordinates(int s, int n) {
        int quot = (s - 1) / n;
        int rem = (s - 1) % n;
        int row = n - 1 - quot;
        int col = (quot % 2 == 0) ? rem : (n - 1 - rem);
        return new int[]{row, col};
    }

}
