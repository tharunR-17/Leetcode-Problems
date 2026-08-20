class Solution {
public:
    vector<int> resultArray(vector<int>& nums) {
        vector<int> arr1, arr2;

        arr1.reserve(nums.size());
        arr2.reserve(nums.size());

        arr1.push_back(nums[0]);

        arr2.push_back(nums[1]);

        for (int i = 2; i < nums.size(); i++) {
            if (arr1.back() > arr2.back()) {
                arr1.push_back(nums[i]);
            } else {
                arr2.push_back(nums[i]);
            }
        }

        vector<int> result;
        result.reserve(nums.size());

        result.insert(result.end(), arr1.begin(), arr1.end());

        result.insert(result.end(), arr2.begin(), arr2.end());

        return result;
    }
};
