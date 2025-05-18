class Solution {
    static final int MOD = 1_000_000_007;
    List<Integer> states = new ArrayList<>();
    Map<Integer, List<Integer>> transitions = new HashMap<>();
    public int colorTheGrid(int m, int n) {
        generateStates(0, 0, m, new int[m]);
        for (int a : states) {
            transitions.put(a, new ArrayList<>());
            for (int b : states) {
                if (isCompatible(a, b, m)) {
                    transitions.get(a).add(b);
                }
            }
        }

        Map<Integer, Integer> dp = new HashMap<>();
        for (int s : states) {
            dp.put(s, 1);
        }

        for (int col = 1; col < n; col++) {
            Map<Integer, Integer> newDp = new HashMap<>();
            for (int prevState : dp.keySet()) {
                for (int nextState : transitions.get(prevState)) {
                    newDp.put(nextState, (newDp.getOrDefault(nextState, 0) + dp.get(prevState)) % MOD);
                }
            }
            dp = newDp;
        }

        int result = 0;
        for (int count : dp.values()) {
            result = (result + count) % MOD;
        }

        return result;
    }
    private void generateStates(int row, int currentState, int m, int[] colors) {
        if (row == m) {
            int state = 0;
            for (int i = 0; i < m; i++) {
                state = state * 3 + colors[i];
            }
            states.add(state);
            return;
        }

        for (int color = 0; color < 3; color++) {
            if (row > 0 && colors[row - 1] == color) continue;
            colors[row] = color;
            generateStates(row + 1, currentState, m, colors);
        }
    }
    private boolean isCompatible(int a, int b, int m) {
        for (int i = 0; i < m; i++) {
            if (a % 3 == b % 3) return false;
            a /= 3;
            b /= 3;
        }
        return true;
    }
}
