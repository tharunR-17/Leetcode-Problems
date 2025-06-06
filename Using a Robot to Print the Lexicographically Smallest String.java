class Solution {
    private int[] small(String s) {
        char ch = 256;
        int count = 0;
        for (char e : s.toCharArray()) {
            if (ch > e) {
                ch = e;
                count = 1;
            } else if (ch == e) {
                count++;
            }
        }
        return new int[]{ch, count};
    }

    public String robotWithString(String s) {
        StringBuilder paper = new StringBuilder();
        StringBuilder t = new StringBuilder();

        while (!s.isEmpty()) {
            int[] temp = small(s);
            char ch = (char) temp[0];
            int count = temp[1];

            while (!t.isEmpty() && t.charAt(t.length() - 1) <= ch) {
                paper.append(t.charAt(t.length() - 1));
                t.deleteCharAt(t.length() - 1);
            }

            int i = 0;
            while (i < s.length()) {
                char e = s.charAt(i);
                if (e == ch) {
                    paper.append(e);
                    count--;
                    if (count == 0) break;
                } else {
                    t.append(e);
                }
                i++;
            }

            s = s.substring(i + 1);
        }

        return paper.append(t.reverse()).toString();
    }
}
