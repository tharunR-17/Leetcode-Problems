class Solution {
    public int minimumScore(int[] nums, int[][] edges) {
        // nums: array of numbers for each node
        // edges: list of connections between nodes
        int n = nums.length; // Number of nodes in the tree

        // Create an adjacency list to represent the tree.
        // Think of it like a phone book: for each node, it lists all its neighbors. 📞
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]); // Add connection: node e[0] is connected to e[1]
            adj.get(e[1]).add(e[0]); // Add connection: node e[1] is connected to e[0] (tree is undirected)
        }

        // Arrays to store XOR values, parent nodes, and depth of each node.
        int[] xor = new int[n], parent = new int[n], depth = new int[n];
        Arrays.fill(parent, -1); // Initially, no node has a parent

        // Perform Depth-First Search (DFS) to calculate XOR values, parent nodes, and depth.
        dfs(0, -1, adj, nums, xor, parent, depth); // Start DFS from node 0

        int totalXor = xor[0], minScore = Integer.MAX_VALUE; // Initialize totalXor and minScore

        // Loop through all possible pairs of edges to remove. ✂️
        for (int i = 0; i < edges.length; i++) {
            for (int j = i + 1; j < edges.length; j++) {
                int[] e1 = edges[i], e2 = edges[j]; // Get the two edges
                int v1 = (depth[e1[0]] > depth[e1[1]]) ? e1[0] : e1[1]; // Get the deeper node of edge e1
                int v2 = (depth[e2[0]] > depth[e2[1]]) ? e2[0] : e2[1]; // Get the deeper node of edge e2

                // Calculate XOR values for the three parts of the tree.
                int x1, x2, x3;
                if (isAncestor(v1, v2, parent)) {
                    // v1 is an ancestor of v2.
                    x1 = xor[v2]; // XOR value of the subtree rooted at v2
                    x2 = xor[v1] ^ x1; // XOR value of the path from v1 to root, excluding v2
                    x3 = totalXor ^ xor[v1]; // XOR value of the rest of the tree, excluding v1
                } else if (isAncestor(v2, v1, parent)) {
                    // v2 is an ancestor of v1.
                    x1 = xor[v1]; // XOR value of the subtree rooted at v1
                    x2 = xor[v2] ^ x1; // XOR value of the path from v2 to root, excluding v1
                    x3 = totalXor ^ xor[v2]; // XOR value of the rest of the tree, excluding v2
                } else {
                    // v1 and v2 are in different subtrees.
                    x1 = xor[v1]; // XOR value of the subtree rooted at v1
                    x2 = xor[v2]; // XOR value of the subtree rooted at v2
                    x3 = totalXor ^ x1 ^ x2; // XOR value of the rest of the tree, excluding v1 and v2
                }

                // Calculate the score (difference between max and min XOR values).
                int score = Math.max(x1, Math.max(x2, x3)) - Math.min(x1, Math.min(x2, x3));
                minScore = Math.min(minScore, score); // Update minScore if the current score is smaller
            }
        }
        return minScore; // Return the minimum score found
    }

    // Perform Depth-First Search (DFS) to calculate XOR values, parent nodes, and depth.
    private void dfs(int u, int p, List<List<Integer>> adj, int[] nums, int[] xor, int[] parent, int[] depth) {
        // u: current node
        // p: parent node
        parent[u] = p; // Set parent of u to p
        xor[u] = nums[u]; // Initialize XOR value of u to the number on the node
        depth[u] = p == -1 ? 0 : depth[p] + 1; // Set depth of u (root has depth 0)
        for (int v : adj.get(u)) if (v != p) { // Iterate through neighbors of u
            dfs(v, u, adj, nums, xor, parent, depth); // Recursively call DFS on neighbor v
            xor[u] ^= xor[v]; // Update XOR value of u by XORing it with XOR value of v
        }
    }

    // Check if a is an ancestor of b.
    private boolean isAncestor(int a, int b, int[] parent) {
        // a: potential ancestor
        // b: potential descendant
        while (b != -1 && b != a) b = parent[b]; // Traverse up the tree from b to the root
        return b == a; // If we reach a, then a is an ancestor of b
    }
}
