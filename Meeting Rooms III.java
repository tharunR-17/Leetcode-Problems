class Solution {
    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, (a,b)->a[0]-b[0]);
        int count[] = new int[n];
        long busy[] = new long[n];

        for(int[] meeting: meetings){
            int startTime = meeting[0];
            int endTime = meeting[1];
            Long earliyer = Long.MAX_VALUE;
            int roomIndex = -1;
            boolean assigned = false;

            for(int i=0;i<n;i++){
                if(earliyer>busy[i]){
                    earliyer = busy[i];
                    roomIndex = i;
                }
                if(busy[i]<=startTime){
                    assigned = true;
                    busy[i] = endTime;
                    count[i]++;
                    break;
                }
            }
            if(!assigned){
                
                busy[roomIndex] += (endTime - startTime);
                count[roomIndex]++;
            }
            
        }
         
        int max = 0, res = 0;
        for (int i = 0; i < n; i++) {
           
            if (count[i] > max) {
                max = count[i];
                res = i;
            }
        }
        return res;
        
    }
}
