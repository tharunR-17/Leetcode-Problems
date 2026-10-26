class Solution {
public:
    int countIslands(vector<vector<int>>& grid, int k) {
        int m = grid.size(), n = grid[0].size(), ans = 0;
        int dirs[5] = {1, 0, -1, 0, 1};  // down, left, up, right
        vector<pair<int, int>> island;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                if (!grid[i][j]) continue;
                long long total = grid[i][j];  // can reach 1e11: needs 64 bits
                grid[i][j] = 0;
                island.assign(1, {i, j});
                for (size_t h = 0; h < island.size(); h++) {  // the list grows while we walk it: a BFS queue
                    auto [x, y] = island[h];
                    for (int d = 0; d < 4; d++) {
                        int a = x + dirs[d], b = y + dirs[d + 1];
                        if (a >= 0 && a < m && b >= 0 && b < n && grid[a][b]) {
                            total += grid[a][b];
                            grid[a][b] = 0;  // sink it so it's counted once
                            island.push_back({a, b});
                        }
                    }
                }
                ans += total % k == 0;
            }
        return ans;
    }
};
