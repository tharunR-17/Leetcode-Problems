class Solution {
    public int minNumberOperations(int[] target) {
        int res = target[0];
        int prev = target[0];
        for (int i = 0; i< target.length; i++){
            if (target[i] > prev){
                res += target[i] - prev;
            }
            prev = target[i];
        }
        return res;
    }
}
