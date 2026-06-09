class Solution {
public:
    static long long maxTotalValue(vector<int>& nums, int k) {
        auto [m, M]=minmax_element(nums.begin(), nums.end());
        return (long long)k*(*M-*m);
    }
};
