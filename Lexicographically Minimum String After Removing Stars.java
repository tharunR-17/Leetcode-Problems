class Solution {
    public String clearStars(String s) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{
            if(a[0]!=b[0]) return a[0]-b[0];
            return b[1]-a[1];
        });
        int n=s.length();
        for(int i=0;i<n;i++){
            char ch = s.charAt(i);
            if(ch == '*')   pq.poll();
            else    pq.offer(new int[]{ch,i});
        }
        List<int[]> list = new ArrayList<>(pq);
        list.sort(Comparator.comparingInt(a->a[1]));
        StringBuilder sb = new StringBuilder();
        for(int ch[]:list){
            sb.append((char)ch[0]);
        }
        return sb.toString();
    }
}
