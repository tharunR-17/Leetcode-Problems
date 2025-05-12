class Solution {
    public int[] findEvenNumbers(int[] digits) {
        if (digits == null || digits.length < 3) {
            return null;
        }

        final int DIGITS = 10;
        int[] digitCounts = new int[DIGITS];
        for (int digit : digits) {
            if (digit >= 0 && digit <= 9) {
                digitCounts[digit]++;
            }
        }

        List<Integer> items = new ArrayList<>();
        for (int hund = 1; hund < DIGITS; hund++) {
            if (digitCounts[hund] == 0) {
                continue;
            }
            digitCounts[hund]--;
            int oneDigit = 100 * hund;
            for (int tens = 0; tens < DIGITS; tens++) {
                if (digitCounts[tens] == 0) {
                    continue;
                }
                digitCounts[tens]--;
                int twoDigits = oneDigit +  (tens * 10);
                
                for (int ones = 0; ones < DIGITS; ones += 2) {
                    if (digitCounts[ones] == 0) {
                        continue;
                    }
                    items.add(twoDigits + ones);
                }

                digitCounts[tens]++;
            }

            digitCounts[hund]++;
        }

        int[] ans = new int[items.size()];
        int idx = 0;
        for (int num : items) {
            ans[idx++] = num;
        }

        return ans;
    }
}
