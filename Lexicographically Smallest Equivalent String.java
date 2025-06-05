class Solution {
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        // adjacency list
        Map<Character, List<Character>> adj = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            char ch1 = s1.charAt(i);
            char ch2 = s2.charAt(i);

            adj.putIfAbsent(ch1, new ArrayList<>());
            adj.get(ch1).add(ch2);

            adj.putIfAbsent(ch2, new ArrayList<>());
            adj.get(ch2).add(ch1);
        }

        int baseN = baseStr.length();
        char[] result = new char[baseN];
        int i = 0;

        for (char ch : baseStr.toCharArray()) {
            boolean[] visited = new boolean[26];
            result[i++] = dfs(ch, adj, visited);
        }

        return String.valueOf(result);
    }

    public char dfs(char minChar, Map<Character, List<Character>> adj, boolean[] visited) {
        if (visited[minChar - 'a']) {
            return minChar;
        }

        visited[minChar - 'a'] = true;

        for (char ch : adj.getOrDefault(minChar, new ArrayList<>())) {
            char dfsChar = dfs(ch, adj, visited);
            if (dfsChar < minChar) {
                minChar = dfsChar;
            }
        }

        return minChar;
    }
}
