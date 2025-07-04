class Solution {
    public char nextOne(char c) {
        if (c == 'z') return 'a';
        return (char)(c + 1);
    }

    public char kthCharacter(long k, int[] operations) {
        if (k <= 1) return 'a';

        long n = k;
        int count = 0;
        while (n > 0) {
            n = n >> 1;
            count++;
        }

        char ch;
        if ((k & (k - 1)) == 0) {
            // k is power of 2 → left part of transformation
            ch = kthCharacter(k / 2, operations);
            return (count - 2 >= 0 && operations[count - 2] == 1) ? nextOne(ch) : ch;
        } else {
            // k lies in right half
            long highestPower = 1L << (count - 1);
            long offset = k - highestPower;
            ch = kthCharacter(offset, operations);
            return (count - 1 < operations.length && operations[count - 1] == 1) ? nextOne(ch) : ch;
        }
    }
}
