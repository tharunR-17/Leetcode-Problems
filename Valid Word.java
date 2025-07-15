class Solution {
    public boolean isValid(String word) {
        if(word.length()<3){
            return false;
        }
        int v=0;
        int c=0;
        int d=0;
        for(int i=0;i<word.length();i++){
            char ch=word.charAt(i);
            if(ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u' || ch=='A' || ch=='E' || ch=='I' || ch=='O' || ch=='U'){
                v+=1;
            }
            else if(ch>='a' && ch<='z'){
                c+=1;
            }
            else if(ch>='A' && ch<='Z'){
                c+=1;
            }
            else if(ch>='0' && ch<='9'){
                d+=1;
            }
            else{
                return false;
            }
        }
        if(v==0 || c==0){
            return false;
        }
        return true;
    }
}
