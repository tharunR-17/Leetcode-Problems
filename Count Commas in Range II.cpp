class Solution {
public:
    int64_t pow[6] = {1000, 1000000, 1000000000, 1000000000000, 1000000000000000, 1000000000000000000};
    
    int64_t countCommas(auto& n) {
        int64_t k = 0;        
        for (auto& p : pow) k += n >= p;
        
        return k * (n + 1) - (pow[k] - 1000) / 999;
    }
};
