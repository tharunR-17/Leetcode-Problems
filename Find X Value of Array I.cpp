int freq[5], freq2[5];
class Solution {
public:
    static vector<long long> resultArray(vector<int>& nums, int k) {
        const int n=nums.size();
        if (k==1) return {1LL*n*(n+1)/2};// special case
        vector<long long> ans(k, 0);
        memset(freq, 0, sizeof(int)*k);// freq[r]=how many times seen for x%k
        for (int x: nums){
            const int r=x%k;
            memset(freq2, 0, sizeof(int)*k);
            ans[r]++;
            for (int j=0; j<k; j++){
                const int prod=j*r%k;
                freq2[prod]+=freq[j];
                ans[prod]+=freq[j];
            }
            freq2[r]++;
            memcpy(freq,freq2, sizeof(int)*k);
        }
        return ans;
    }
};
