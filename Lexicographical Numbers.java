class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<>();

        for (int i = 1; i <= Math.min(9, n); i++) {
            result.add(i);
            for (int j = i * 10; j <= Math.min((i * 10) + 9, n); j++) {
                result.add(j);
                for (int k = j * 10; k <= Math.min((j * 10) + 9, n); k++) {
                    result.add(k);
                    for (int l = k * 10; l <= Math.min((k * 10) + 9, n); l++) {
                        result.add(l);
                        for (int m = l * 10; m <= Math.min((l * 10) + 9, n); m++) {
                            result.add(m);
                        }
                    }
                }
            }
        }

        return result;
    }
}
