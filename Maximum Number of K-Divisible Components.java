class Solution {

    List<List<Integer>> adj;
    int[] values;
    int k;
    int ans;

    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        this.values = values;
        this.k = k;
        this.ans = 0;

        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        for (int[] e : edges) {
            int u = e[0], v = e[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        dfs(0, -1);
        return ans;
    }

    private long dfs(int u, int parent) {
        long sum = values[u] % k;

        for (int v : adj.get(u)) {
            if (v == parent) continue;
            sum = (sum + dfs(v, u)) % k;
        }

        if (sum % k == 0) {
            ans++;
            return 0;
        }
        return sum;
    }
}
