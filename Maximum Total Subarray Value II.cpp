class Solution {
public:
    vector<vector<int>> stMax, stMin;
    vector<int> lg;

    long long getValue(int l, int r) {

        int len = r - l + 1;
        int p = lg[len];

        int mx = max(
            stMax[p][l],
            stMax[p][r - (1 << p) + 1]
        );

        int mn = min(
            stMin[p][l],
            stMin[p][r - (1 << p) + 1]
        );

        return 1LL * mx - mn;
    }

    long long maxTotalValue(vector<int>& nums, int k) {
        int n = nums.size();

        // Precompute logs
        //lg[i] stores the exponent of the largest power of 2 that is ≤ i.
        lg.resize(n + 1);
        for (int i = 2; i <= n; i++) {
            lg[i] = lg[i / 2] + 1;
        }

        int LOG = lg[n] + 1; //total layers in sparse table

        stMax.assign(LOG, vector<int>(n));
        stMin.assign(LOG, vector<int>(n));

        // Level 0 of sparse table
        for (int i = 0; i < n; i++) {
            stMax[0][i] = nums[i];
            stMin[0][i] = nums[i];
        }

        // Build Sparse Tables
        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i + (1 << j) <= n; i++) {

                stMax[j][i] = max(
                    stMax[j - 1][i],
                    stMax[j - 1][i + (1 << (j - 1))]
                );

                stMin[j][i] = min(
                    stMin[j - 1][i],
                    stMin[j - 1][i + (1 << (j - 1))]
                );
            }
        }

        using T = tuple<long long, int, int>;
        priority_queue<T> pq;

        // For every starting index, begin with the interval [l, n-1]
        for (int l = 0; l < n; l++) {
            pq.push({getValue(l, n - 1), l, n - 1});
        }

        long long ans = 0;

        while (k--) {
            auto [val, l, r] = pq.top();
            pq.pop();

            ans += val;

            if (r > l) {
                pq.push({getValue(l, r - 1), l, r - 1});
            }
        }

        return ans;
    }
};
