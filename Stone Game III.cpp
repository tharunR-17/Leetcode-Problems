vector<int> dp;
class Solution {
public:    
    static int play(int i, vector<int>& stoneValue, int n){
        if (dp[i]!=INT_MIN) return dp[i];
        int result=stoneValue[i]-play(i+1, stoneValue, n);
        if (i+2<=n)
            result=max(result,
            stoneValue[i]+stoneValue[i+1]-play(i+2, stoneValue, n));
        if (i+3<=n)
            result=max(result,
            stoneValue[i]+stoneValue[i+1]+stoneValue[i+2]-play(i+3, stoneValue, n));
        return dp[i]=result;
    }
    static inline int iterate_play(int i, vector<int>& stoneValue, int n){
        for (int i=n-1; i>=0; i--){
            int k0=min(3, n-i);
            int s=stoneValue[i];
            int result=s-dp[(i+1)%3];
            for(int k=1; k<k0; k++){
                s+=stoneValue[i+k];
                result=max(result,s-dp[(i+k+1)%3] );
            }
            dp[i%3]=result;
        }
        return dp[0];
    }
    static string stoneGameIII(vector<int>& stoneValue) {
        const int n = stoneValue.size();
        dp.assign(3, 0);// n+1 replaced by 3
        dp[n%3]=0;
        int win=iterate_play(0, stoneValue,  n);
        
        if (win>0) return "Alice";
        if (win<0) return "Bob";
        return "Tie";
    }
};

auto init = []() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    return 'c';
}();
