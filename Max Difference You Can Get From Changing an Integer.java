class Solution {
    public int maxDiff(int num) {
        String s= Integer.toString(num);
        char maxArr[]= s.toCharArray();

        for(int i=0;i<maxArr.length;++i){
            if(maxArr[i]!= '9'){
                char replacement= maxArr[i];
                for(int j=0;j<maxArr.length;++j){
                    if (maxArr[j]== replacement) maxArr[j]= '9';
                }
                break;
            }
        }

        int maxVal= Integer.parseInt(new String(maxArr));

        char minArr[]= s.toCharArray();
        if(minArr[0]!= '1'){
            char replacement= minArr[0];
            for(int j=0;j<minArr.length;++j){
                if(minArr[j]== replacement) minArr[j]= '1';
            }
        }else{
            for(int i=1;i<minArr.length;++i){
                if(minArr[i]!= '0' && minArr[i]!= '1'){
                    char replacement= minArr[i];
                    for(int j=1;j<minArr.length;++j){
                        if(minArr[j]==replacement) minArr[j]= '0';
                    }
                    break;
                }
            }
        }

        int minVal= Integer.parseInt(new String(minArr));

        return maxVal-minVal;
    }
}
