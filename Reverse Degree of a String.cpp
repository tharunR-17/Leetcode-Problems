class Solution {
public:
    int reverseDegree(string s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s[i];

            int reverseValue = 26 - (c - 'a');
            int position = i + 1;

            sum += reverseValue * position;
        }
        return sum;
    }
};
