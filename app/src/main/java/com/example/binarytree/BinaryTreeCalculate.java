package com.example.binarytree;

/**
 * https://mp.weixin.qq.com/s/AZPtJ63b65u-sBZWEatYvA
 */
public class BinaryTreeCalculate {

    /**
     * @param node
     * @return 求二叉树的最大深度
     */
    int maxDeath(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = maxDeath(node.left);
        int right = maxDeath(node.right);
        return Math.max(left, right) + 1;
    }

    /**
     * @param root
     * @return 求二叉树的最小深度
     */
    int getMinDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getMin(root);
    }

    int getMin(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return Math.min(getMin(root.left), getMin(root.right)) + 1;
    }

    /**
     * @param root
     * @return 求二叉树中节点的个数
     */
    int numOfTreeNode(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = numOfTreeNode(root.left);
        int right = numOfTreeNode(root.right);
        return left + right + 1;
    }

    /**
     * @param root
     * @return 求二叉树中叶子节点的个数
     */
    int numsOfNoChildNode(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return numOfTreeNode(root.left) + numOfTreeNode(root.right);
    }

    /**
     * @param root
     * @param k
     * @return 求二叉树中第k层节点的个数
     */
    int numsOfkLevelTreeNode(TreeNode root, int k) {
        if (root == null || k < 1) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }
        int numsLeft = numsOfkLevelTreeNode(root.left, k - 1);
        int numsRight = numsOfkLevelTreeNode(root.right, k - 1);
        return numsLeft + numsRight;
    }
}
