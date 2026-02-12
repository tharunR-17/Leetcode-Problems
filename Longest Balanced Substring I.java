class Solution {
    public int longestBalanced(String s) {
        int max = 0;
        int n = s.length();
        
        for(int i = 0; i < n; i++){
            int freq[] = new int[26];
            
            for(int j = i; j < n; j++){
                char ch = s.charAt(j);
                freq[ch - 'a']++;
                
                if(issamefreq(freq)){
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }
    
    public static boolean issamefreq(int arr[]){
        int value = 0;
        
        for(int a : arr){
            if(a == 0) continue;
            
            if(value == 0) 
                value = a;
            else if(value != a) 
                return false;
        }
        return true;
    }
}
