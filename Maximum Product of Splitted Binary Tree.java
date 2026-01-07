class Solution {
    long totalSum = 0;
    long maxProd = 0;
    static final int MOD = 1_000_000_007;

    private void dfsTotal(TreeNode root) {
        if (root == null) return;
        totalSum += root.val;
        dfsTotal(root.left);
        dfsTotal(root.right);
    }

    private long dfs(TreeNode root) {
        if (root == null) return 0;

        long left = dfs(root.left);
        long right = dfs(root.right);

        long subSum = left + right + root.val;
        maxProd = Math.max(maxProd, subSum * (totalSum - subSum));

        return subSum;
    }

    public int maxProduct(TreeNode root) {
        dfsTotal(root);
        dfs(root);
        return (int)(maxProd % MOD);
    }
}
