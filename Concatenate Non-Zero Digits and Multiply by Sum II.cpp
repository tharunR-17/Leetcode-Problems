class Solution {
public:
    vector<int> sumAndMultiply(string s, vector<vector<int>>& queries) {
        const int MOD = 1e9 + 7;
        int n = s.size();

        vector<long long> pow10(n + 1, 1);
        for (int i = 1; i <= n; i++)
            pow10[i] = (pow10[i - 1] * 10) % MOD;

        // idx[i] = number of non-zero digits before index i
        vector<int> idx(n + 1, 0);

        // val[i] = number formed by first i non-zero digits
        vector<long long> val(n + 1, 0);

        // total[i] = sum of first i non-zero digits
        vector<long long> total(n + 1, 0);

        int cnt = 0;

        for (int i = 0; i < n; i++) {
            int digit = s[i] - '0';

            if (digit != 0) {
                cnt++;

                val[cnt] = (val[cnt - 1] * 10 + digit) % MOD;
                total[cnt] = total[cnt - 1] + digit;
            }

            idx[i + 1] = cnt;
        }

        vector<int> ans;

        for (auto &q : queries) {

            int l = q[0];
            int r = q[1];

            int left = idx[l];
            int right = idx[r + 1];
            //no non-zero digit in the range
            if (left == right) { 
                ans.push_back(0);
                continue;
            }

            int len = right - left;

            long long number =(val[right] - val[left] * pow10[len]) % MOD;

            if (number < 0)
                number += MOD;

            long long sum = total[right] - total[left];
            ans.push_back((number * sum) % MOD);
        }

        return ans;
    }
};
