class Solution {
public:
    int maxLength(vector<int>& nums) {
        int n = nums.size();
        const long long LIMIT = 25200;   // lcm(1..10) * 10, no valid product can exceed this
        int ans = 1;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                long long prod = 1, g = 0, l = 1;
                bool ok = true;

                for (int k = i; k <= j; k++) {
                    prod *= nums[k];
                    if (prod > LIMIT) { ok = false; break; }
                    g = gcd(g, (long long) nums[k]);
                    l = lcm(l, (long long) nums[k]);
                }

                if (ok && prod == g * l) ans = max(ans, j - i + 1);
            }
        }

        return ans;
    }
};
