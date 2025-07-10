class Solution {
    public int maxFreeTime(int eventTime, int[] startTime, int[] endTime) {
        int st = 0;
        ArrayList<Integer> gaps = new ArrayList<>();
        int n = startTime.length;
        for (int i = 0; i < n; i++) {
            gaps.add(startTime[i] - st);
            st = endTime[i];
        }
        gaps.add(eventTime - st);
       int[] g = new int[gaps.size()];
        for (int i = 0; i < gaps.size(); i++) {
            g[i] = gaps.get(i);
        }
        int len=g.length;
        int left[]=new int[len];
        
        int right[]=new int[len];

        left[0]=0;
        right[len-1]=0;

        for(int i=1;i<len-1;i++){
            left[i]=Math.max(left[i-1],g[i-1]);
        }
        for(int i=len-2;i>=0;i--){
            right[i]=Math.max(right[i+1],g[i+1]);
        }
        int max=0;
        for(int i=0;i<len-1;i++){
            int curr=g[i]+g[i+1];
            int find=endTime[i]-startTime[i];
            max=Math.max(curr,max);
            
            if(left[i]>= find || right[i+1]>=find){
                max=Math.max(curr+find,max);
            }
        }
        return max;
    }
}
