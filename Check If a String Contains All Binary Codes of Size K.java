class Solution {
    class RollingHash {
        long hash, power;
        int len;

        RollingHash(String s, int len) {
            this.len = len;
            this.power = 1L << (len - 1);

            for (int i = 0; i < len; i++) {
                hash = (hash << 1) | (s.charAt(i) - '0');
            }
        }

        long next(char old_char, char new_char) {
            hash = ((hash - (old_char - '0') * power) << 1) | (new_char - '0');
            return hash;
        }
    }

    public boolean hasAllCodes(String s, int k) {
        int n = s.length();
        if (n - k + 1 < (1 << k)) return false;
        Set<Long> set = new HashSet<>();
        RollingHash rh = new RollingHash(s, k);
        set.add(rh.hash);

        for (int i = k; i <= n - 1; i++) {
            char old_symbol = s.charAt(i - k), new_symbol = s.charAt(i);
            long new_s = rh.next(old_symbol, new_symbol);
            set.add(new_s);
        }

        return set.size() == (1 << k);
    }
}
