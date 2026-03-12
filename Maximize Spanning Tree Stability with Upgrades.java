class Solution {
    class Edge {
        int u, v, strength;
        boolean must;
        
        Edge(int u, int v, int strength, boolean must) {
            this.u = u;
            this.v = v;
            this.strength = strength;
            this.must = must;
        }
    }
    
    class DSU {
        int[] parent, rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];  
        }

        void union(int x, int y) {
            int root_x = find(x), root_y = find(y);
            
            if (root_x == root_y) return;

            if (rank[root_x] < rank[root_y]) {
                parent[root_x] = root_y;
            } else if (rank[root_x] > rank[root_y]) {
                parent[root_y] = root_x;
            } else {
                parent[root_y] = root_x;
                rank[root_x]++;
            }
        }
    }

    public int maxStability(int n, int[][] edges, int k) {
        List<Edge> mandatory = new ArrayList<>();
        List<Edge> optional = new ArrayList<>();
        
        for (int[] e : edges) {	
            Edge edge = new Edge(e[0], e[1], e[2], e[3] == 1);
            if (edge.must) {
                mandatory.add(edge);
            } else {
                optional.add(edge);
            }
        }
    
        DSU dsu = new DSU(n);
        
        for (Edge e : mandatory) {
            if (dsu.find(e.u) == dsu.find(e.v)) {
                return -1;
            }
            dsu.union(e.u, e.v);
        }
        
        int[] parent_snap = new int[n];
        int[] rank_snap = new int[n];
        for (int i = 0; i < n; i++) {
            parent_snap[i] = dsu.parent[i];
            rank_snap[i] = dsu.rank[i];
        }
        
        int left = 1;
        int right = 0;
        for (int[] e : edges) {
            right = Math.max(right, e[2] * 2);
        }

        int answer = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (canAchieve(n, mandatory, optional, k, mid, parent_snap, rank_snap)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    private boolean canAchieve(int n, List<Edge> mandatory, List<Edge> optional, 
                               int k, int minStability, int[] parent_snap, int[] rank_snap) {
        DSU dsu = new DSU(n);
        dsu.parent = parent_snap.clone();
        dsu.rank = rank_snap.clone();

        for (Edge e : mandatory) {
            if (e.strength < minStability) {
                return false;
            }
        }

        List<Edge> goodWithUpgrade = new ArrayList<>();
        List<Edge> goodWithoutUpgrade = new ArrayList<>();

        for (Edge e : optional) {
            if (e.strength >= minStability) {
                goodWithoutUpgrade.add(e);
            } else if (e.strength * 2 >= minStability) {
                goodWithUpgrade.add(e);
            }
        }

        for (Edge e : goodWithoutUpgrade) {
            if (dsu.find(e.u) != dsu.find(e.v)) {
                dsu.union(e.u, e.v);
            }
        }
        
        int used = 0;
        for (Edge e : goodWithUpgrade) {
            if (used >= k) break;
            
            if (dsu.find(e.u) != dsu.find(e.v)) {
                dsu.union(e.u, e.v);
                used++;
            }
        }
        
        int root = dsu.find(0);
        for (int i = 1; i < n; i++) {
            if (dsu.find(i) != root) return false;
        }
        
        return true;
    }
}
