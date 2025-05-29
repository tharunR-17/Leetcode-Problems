class Solution {
    public int[] build(int[][]edgs,boolean eve){
        int n = edgs.length + 1;
        List<Integer>[] ls = new List[n];
        for(int i=0;i<n;i++){
            ls[i]= new ArrayList<>();
        }
        for(int[]ed:edgs){
            ls[ed[0]].add(ed[1]);
            ls[ed[1]].add(ed[0]);
        }
        Deque<Integer>q = new ArrayDeque<>();
        boolean vis[] = new boolean[n];
        q.offerFirst(0);
        vis[0] = true;
        int cnt = 0;
        Set<Integer> hs = new HashSet<>();
        while(!q.isEmpty()){
            int m = q.size();
            for(int i=0;i<m;i++){
                int u = q.pollLast();
                if(eve){
                    cnt++;
                    hs.add(u);
                }
                for(int v:ls[u]){
                    if(!vis[v]) q.offerFirst(v);
                    vis[v] = true;
                }
            }
            eve = !eve;
        } 
        int ans[] = new int[n];
        for(int i=0;i<n;i++){
            ans[i] = hs.contains(i) ? cnt:n-cnt; 
            //for 2nd tree its opps (tho doesnt matter as we need max of 2 for 2nd tree)
        }
        return ans;
    }
    public int getMax(int[][]edgs,boolean eve){
        int n = edgs.length + 1;
        List<Integer>[] ls = new List[n];
        for(int i=0;i<n;i++){
            ls[i]= new ArrayList<>();
        }
        for(int[]ed:edgs){
            ls[ed[0]].add(ed[1]);
            ls[ed[1]].add(ed[0]);
        }
        Deque<Integer>q = new ArrayDeque<>();
        boolean vis[] = new boolean[n];
        q.offerFirst(0);
        vis[0] = true;
        int cnt = 0;
        while(!q.isEmpty()){
            int m = q.size();
            if(eve) cnt+=m;
            for(int i=0;i<m;i++){
                int u = q.pollLast();
                for(int v:ls[u]){
                    if(!vis[v]) q.offerFirst(v);
                    vis[v] = true;
                }
            }
            eve = !eve;
        } 
        return Math.max(cnt,n-cnt);
    }
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
        int[] ar1 = build(edges1,true);
        //int[] ar2 = build(edges2,false);
        //int mxm = Arrays.stream(ar2).max().getAsInt();
        int mxm = getMax(edges2,false);
        for(int i=0;i<ar1.length;i++){
            ar1[i] += mxm;
        }
        return ar1;
    }
}
