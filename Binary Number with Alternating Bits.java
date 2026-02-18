class Solution {
    public boolean hasAlternatingBits(int n) {
        String str=Integer.toBinaryString(n);
        
        for(int i=0;i<str.length();i+=2) {
            if(str.charAt(i)!='1') {
                return false;
            }
        }

        for(int i=1;i<str.length();i+=2) {
            if(str.charAt(i)!='0') {
                return false;
            }
        }

        return true;
    }
}
