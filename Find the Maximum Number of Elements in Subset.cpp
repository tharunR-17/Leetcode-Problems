class Solution {
public:
    int maximumLength(vector<int>& nums) {
        unordered_map<long long, int> mp;

        for (int x : nums)
            mp[x]++;

        int ans = 1;

        // Handle 1 separately
        if (mp.count(1)) {
            int cnt = mp[1];
            ans = max(ans, cnt % 2 ? cnt : cnt - 1);
        }

        for (auto &[num, f] : mp) {
            if (num == 1) continue;

            long long x = num;
            int len = 0;

            while (mp.count(x) && mp[x] >= 2) {
                len += 2;

                if (x > 1e9) break; // avoid overflow
                x = x * x;
            }

            if (mp.count(x))
                len++;

            else
                len--;

            ans = max(ans, len);
        }

        return ans;
    }
};
