constexpr int N=1e5;
constexpr int LOG=17; // because 2^17 > 1e5

static int q[N], front=0, back=0; // custom queue
static int adj[N+1];              // array linked list heads
struct Edge {
    int to=-1, nxt=-1;
};
static Edge E[2*N]; // undirected edges capacity
static int eCnt=0;  // count E

static inline void addEdge(int u, int v) {
    E[eCnt]={v, adj[u]};
    adj[u]=eCnt++;
}
// 1-indexed
// up[i][j] stores the (2^j)-th ancestor of node i
static int level[N+1], parent[N+1], up[N+1][LOG];
static constexpr int mod=1e9+7;

class Solution {
public:
    static long long modPow(long long x, int exp) {
        long long y=1; 
        for (; exp; exp>>=1) {
            if (exp & 1) y=y*x%mod;
            x=x*x%mod;
        }
        return y;
    }

    static long long pow2(int x) {
        if (x<30) return 1<<x;
        long long B=(1<<30) % mod;
        auto [qq, r]=div(x, 30);
        return modPow(B, qq)*pow2(r)%mod;
    }

    static int lca(int u, int v) {
        if (level[u]<level[v]) swap(u, v);
        int diff=level[u]-level[v];
        // Equalize levels using ctz
        for(; diff; diff&=(diff-1)) {
            const int i=__builtin_ctz(diff);
            u=up[u][i];
        }
        if (u==v) return u;
        // Lift simultaneously
        for (int i=LOG-1; i>=0; i--) {
            if (up[u][i]!=up[v][i]) {
                u=up[u][i];
                v=up[v][i];
            }
        }
        return up[u][0];
    }

    static inline int distance(int u, int v) {
        int a=lca(u, v);
        return level[u]+level[v]-2*level[a];
    }

    vector<int> assignEdgeWeights(vector<vector<int>>& edges, vector<vector<int>>& queries) {
        const int n=edges.size()+1;  
        // reset
        memset(adj, -1, sizeof(int)*(n+1));
        memset(level, 0, sizeof(int)*(n+1));
        memset(parent, 0, sizeof(int)*(n+1));
        eCnt=0;
        
        for (auto& e : edges) {
            int u=e[0], v=e[1];
            addEdge(u, v);
            addEdge(v, u);
        }

        // BFS traversal
        static bitset<N+1> viz;
        viz.reset();
        front=back=0;
        q[back++]=1;
        viz[1]=1;
        parent[1]=0; // Root's parent points to dummy node 0
        level[1]=1;  // Root starts at level 1
        while (front<back) {
            int u=q[front++];
            for (int e=adj[u]; e!=-1; e=E[e].nxt) {
                int v=E[e].to;
                if (!viz[v]) {
                    viz[v]=1;
                    parent[v]=u;
                    level[v]=level[u]+1;
                    q[back++]=v;
                }
            }
        }

        // Build binary lifting DP table up to index n
        for (int i=1; i<=n; i++) 
            up[i][0]=parent[i];
        
        
        for (int j=1; j<LOG; j++) {
            for (int i=0; i<=n; i++) 
                up[i][j]=up[up[i][j-1]][j-1];
        }

        const int m=queries.size();
        vector<int> ans(m);
        for (int i=0; i<m; i++) {
            int u=queries[i][0];
            int v=queries[i][1];
            int d=distance(u, v);
            ans[i]=(d>0)?pow2(d-1):0;
        }
        return ans;
    }
};
