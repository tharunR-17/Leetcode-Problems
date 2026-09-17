class Solution {
public:
    int minSumOfLengths(vector<int>& arr, int target) {
        int n=arr.size();
        int INF=1e9;
        vector<int>left(n,INF);
        int curr=0,l=0,best=INF;
        for(int r=0;r<n;r++){
            curr+=arr[r];
            while(curr>target){
                curr-=arr[l];
                l++;
            }
            if(curr==target)best=min(best,r-l+1);
            left[r]=best;
        }

        vector<int>right(n,INF);
        curr=0;
        int r=n-1;
        best=INF;
        for(l=n-1;l>=0;l--){
            curr+=arr[l];
            while(curr>target&&r>=l){
                curr-=arr[r];
                r--;
            }
            if(curr==target)best=min(best,r-l+1);
            right[l]=best;
        }
        int ans=INF;
        for(int i=0;i<n-1;i++){
            if(left[i]!=INF&&right[i+1]!=INF)
                ans=min(ans,left[i]+right[i+1]);
        }
        return ans==INF?-1:ans;
    }
};
