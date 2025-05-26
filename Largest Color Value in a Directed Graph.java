class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        int length = colors.length();
        int[] indegrees = new int[length];
        Map<Integer, List<Integer>> edgesMap = new HashMap<>();
        for (int[] edge : edges) {
            int start = edge[0], end = edge[1];
            indegrees[end]++;
            edgesMap.computeIfAbsent(start, k -> new ArrayList<>()).add(end);
        }
        int remain = length;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            if (indegrees[i] == 0)
                queue.offer(i);
        }
        int[][] dp = new int[length][26];
        while (!queue.isEmpty()) {
            int node = queue.poll();
            remain--;
            dp[node][colors.charAt(node) - 'a']++;
            for (int nextNode : edgesMap.getOrDefault(node, new ArrayList<>())) {
                indegrees[nextNode]--;
                if (indegrees[nextNode] == 0)
                    queue.offer(nextNode);
                for (int i = 0; i < 26; i++)
                    dp[nextNode][i] = Math.max(dp[nextNode][i], dp[node][i]);
            }
        }
        if (remain > 0)
            return -1;
        return Arrays.stream(dp).flatMapToInt(Arrays::stream).max().getAsInt();
    }
}
