class Solution {
    public int possibleStringCount(String word) {
        int ans=1;
        char last=word.charAt(0);
        for(int i=1;i<word.length();i++){
          while(i<word.length() && last==word.charAt(i)){
            ans++;
            i++;
          }
          if(i<word.length())
          last=word.charAt(i);
        }
        return ans;

    }
}
