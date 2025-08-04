class Solution {
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        int lastFruit = -1, secondLastFruit = -1;
        int lastCount = 0, currMax = 0, max = 0;

        for (int i = 0; i < n; i++) {
            int fruit = fruits[i];

            if (fruit == lastFruit || fruit == secondLastFruit) {
                currMax++;
            } else {
                currMax = lastCount + 1;
            }

            if (fruit == lastFruit) {
                lastCount++;
            } else {
                lastCount = 1;
                secondLastFruit = lastFruit;
                lastFruit = fruit;
            }

            max = Math.max(max, currMax);
        }

        return max;
    }
}
