class Solution {
    public int[] sortByBits(int[] arr) {
        Integer[] boxedarr = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            boxedarr[i] = arr[i];
        }
        
        java.util.Arrays.sort(boxedarr, (a, b) -> {
            int counta = Integer.bitCount(a);
            int countb = Integer.bitCount(b);
            if (counta == countb) {
                return a.compareTo(b);
            }
            return Integer.compare(counta, countb);
        });
        
        for (int i = 0; i < arr.length; i++) {
            arr[i] = boxedarr[i];
        }
        return arr;
    }
}
