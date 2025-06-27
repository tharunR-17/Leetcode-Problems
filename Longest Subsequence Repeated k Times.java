class Solution {
    public String longestSubsequenceRepeatedK(String s, int k) {
    String ans = "";
    Queue<String> q = new LinkedList<>();
    q.add("");

    while(!q.isEmpty())
    {
      String str = q.poll();  
      for(char ch = 'a'; ch<='z'; ch++)
      {
        String temp = str + ch; 
        if(isKTimesRepeated(temp, s, k))
        {
        ans = temp;
        q.add(temp);
        }
      }
    }
     return ans;
    }
    public boolean isKTimesRepeated(String temp, String src, int k)
    {
      int p = 0 , counter = 0; 
      for(char ch : src.toCharArray())
      {
        if(ch==temp.charAt(p))
        {
          if(++p==temp.length())
          {
          p = 0; 
          if(++counter==k)
          return true;  
          } 
        }  
      }
       return false;
    }
}
