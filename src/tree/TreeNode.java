package tree;

/**
 * Implement BST
 */
public class TreeNode {
    TreeNode left;
    TreeNode right;
    int val;
    public TreeNode(int x) {
        this.val = x;
    }

    public void insert(int  value) {
        if (value<=val) { // go to left
            if (left == null) {
                left = new TreeNode(value);
            } else {
                left.insert(value);
            }
        } else {
            if (right == null) {
                right = new TreeNode(value);
            } else {
                right.insert(value);
            }
        }
    }

    public boolean contains(int value) {
        if (value == val) {
            return true;
        } else if (value < val) { // go left
            if (left == null) {
                return false;
            } else {
                return left.contains(value);
            }
        } else { // go to right
            if (right == null) {
                return false;
            } else {
                return right.contains(value);
            }
        }
    }

    public void printInOrder() {
        if (left != null) {
            left.printInOrder();
        }
        System.out.println(val);
        if (right != null) {
            right.printInOrder();
        }

    }

}
