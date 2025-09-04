class Solution {
    public int findClosest(int x, int y, int z) {
        int px=Math.abs(x-z);
        int py=Math.abs(y-z);
        if(px<py){
            return 1;
        }
        else if(px>py){
            return 2;
        }
        return 0;
    }
}
