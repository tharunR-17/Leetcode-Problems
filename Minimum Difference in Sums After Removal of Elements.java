class Solution {
    public long minimumDifference(int[] nums) {
       int n=nums.length/3;
       long fsum[]=new long[3*n+1]; 
       long lsum[]=new long[3*n+1];
       long sum=0;
       PriorityQueue<Integer>maxheap=new PriorityQueue<>((a,b) -> b-a);
       for(int i=0;i<n;i++){
        maxheap.offer(nums[i]);
        sum+=nums[i];
       }  
       fsum[n]=sum;
       for(int i=n+1;i<=2*n;i++){
        int x=nums[i-1];
        if(x<maxheap.peek()){
            sum=sum-maxheap.poll()+x;
            maxheap.offer(x);
        }
        fsum[i]=sum;
       }
       sum=0;
       PriorityQueue<Integer>minheap=new PriorityQueue<>();
       for(int i=2*n;i<3*n;i++){
        minheap.offer(nums[i]);
        sum+=nums[i];
       }  
       lsum[2*n]=sum;
       for(int i=2*n-1;i>=n;i--){
        int x=nums[i];
        if(x>minheap.peek()){
            sum=sum-minheap.poll()+x;
            minheap.offer(x);
        }
        lsum[i]=sum;
       }
       long diff=Long.MAX_VALUE;
       for(int i=n;i<=2*n;i++){
        diff=Math.min(diff,fsum[i]-lsum[i]);
       }
       return diff;
    }
}
