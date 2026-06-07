/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left),
 * right(right) {}
 * };
 */
class Solution {
public:
    static TreeNode* createBinaryTree(vector<vector<int>>& descriptions) {
        const int N = 100001;
        bitset<N> seen = 0;
        int root = -1;
        int parent[N] = {0};//parent val
        TreeNode* node[N] = {NULL};// array for TreeNode*

        for (auto& d : descriptions) {
            int x = d[0], y = d[1], l = d[2];
            if (!seen[x]) {
                node[x] = new TreeNode(x);
                seen[x] = 1;
                if (parent[x] == 0)
                    root = x;
            }
            if (!seen[y]) {
                node[y] = new TreeNode(y);
                seen[y] = 1;
            }
            parent[y] = x;
            if (l)
                node[x]->left = node[y];
            else
                node[x]->right = node[y];
        }
        while (parent[root] != 0)// find real root
            root = parent[root];
        return node[root];
    }
};
