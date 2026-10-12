class Solution {
public:
    int maxSum(vector<int>& nums, int k) {
        const long long MOD = 1e9 + 7;
        int cnt[30] = {};  // coins in each bit column, never changes
        for (int x : nums)
            for (int b = 0; b < 30; b++) cnt[b] += x >> b & 1;
        long long total = 0;
        for (int i = 0; i < k; i++) {  // build row i of the stacked picture
            long long row = 0;
            for (int b = 0; b < 30; b++)
                if (cnt[b] > i) row |= 1LL << b;  // a coin from every column with more than i coins
            total = (total + row * row) % MOD;
        }
        return total;
    }
};
