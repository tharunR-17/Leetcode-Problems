class Solution {
public:
    int minOperations(vector<int>& nums) {
        int n = nums.size(), shift = ranges::find(nums, 0) - nums.begin();
        auto ring = [&](int k) { return nums[(shift + k) % n]; };  // the circle, read from 0
        auto all = [&](auto holds) { return ranges::all_of(views::iota(0, n), holds); };
        if (all([&](int k) { return ring(k) == k; }))  // counts up along the arrow
            return min(shift, n - shift + 2);  // walk ahead, or turn, walk back, turn back
        if (all([&](int k) { return ring(k) == (n - k) % n; }))  // counts up against the arrow
            return min(shift + 2, n - shift);  // walk ahead and turn, or turn and walk back
        return -1;
    }
};
