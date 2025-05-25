class Solution {
    public int longestPalindrome(String[] words) {
        Map<String,Integer> map=new HashMap<>();
        int multiple=0;
        for(String i:words){
            if(map.containsKey(i)){
                int a=map.get(i)+1;
                map.put(i,a);
                continue;
            }
            map.put(i,1);
            if(i.charAt(0)==i.charAt(1)){
                multiple++;
            }
        }
       // System.out.println(map);
        int sum=0;
        int maxd=0;
        String ch="";
        boolean chance1=true;
        for(Map.Entry<String,Integer> mappy:map.entrySet()){
            String key=mappy.getKey();
            int freq=mappy.getValue();
            if(freq==0){
                continue;
            }
            if(key.charAt(0)==key.charAt(1)){
                if(multiple==1){
                    maxd=freq*2;
                }
                else{
                    if(freq%2==0){
                        maxd+=freq*2;
                    }
                    else{
                       
                        maxd+=(freq-1)*2;
                        if(chance1){
                            maxd+=2;
                            chance1=!chance1;
                        }
                    }
                }
                continue;
            }
            StringBuilder str=new StringBuilder();
            str.append(key);
            str.reverse();
            ch=str.toString();
            if(map.containsKey(ch)){
                int num=Math.min(freq,map.get(ch));
                sum+=num*4;
                map.put(ch,0);
                map.put(key,0);
            }
        }
        return sum+maxd;
    }
}
