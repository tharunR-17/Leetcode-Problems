class Solution {
    // a, b are main direction else direction enemy
    private int solve(char a, char b, String s, int k) {
        int dist = 0, mx = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == a || ch == b) {
                dist++;
            } else {
                if (k > 0) {
                    k--;
                    dist++;
                } else {
                    mx = Math.max(mx, dist);
                    dist--;
                }
            }
        }

        mx = Math.max(mx, dist);
        return mx;
    }

    public int maxDistance(String s, int k) {
        if (k == s.length()) return k;

        // good direction
        int nw = solve('N', 'W', s, k);
        int ne = solve('N', 'E', s, k);
        int sw = solve('S', 'W', s, k);
        int se = solve('S', 'E', s, k);

        return Math.max(Math.max(nw, ne), Math.max(sw, se));
    }
}
