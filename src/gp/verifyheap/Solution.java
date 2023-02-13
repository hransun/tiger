package gp.verifyheap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Node {
    int value;
    Node left;
    Node right;
    Node() {
        left = null;
        right = null;
        value = -1;
    }
}

class Checker {
    boolean  isHeap(Node root) {
        return isComplete(root) && isSmaller(root);
    }

    private boolean isSmaller(Node root) { // check min heap recursively
        if (root == null) return true;
        if (root.left != null && root.value > root.left.value) return false;
        if (root.right != null && root.value > root.right.value) return false;
        return isSmaller(root.left) && isSmaller(root.right);
    }

    private boolean isComplete(Node root) { // check if the  tree is a complete tree
        return  countNode(root) == getMaxIndex(root,0) +1;
    }

    private int getMaxIndex(Node root, int index) {
        if (root == null) return -1;  // index  is 1 less than nodes count
        return Math.max(index,Math.max(getMaxIndex(root.left,2 * index + 1),
                getMaxIndex(root.right,2 * index + 2)));
    }

    private int countNode(Node root) { // how many nodes?
        return root == null ? 0: 1+ countNode(root.left) + countNode(root.right);
    }
}
public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/gp/verifyheap/input.txt"));
        int n = in.nextInt(); // how many lines of data
        Checker checker = new Checker();
        while (n!=-1) {
            // build tree
            Node[] tree = new  Node[n];
            for (int i = 0; i < n; i++) {
                tree[i] = new Node();
            }
            for (int i = 0; i < n; i++) {
                tree[i].value =  in.nextInt();
                int leftId = in.nextInt();
                if (leftId != -1) tree[i].left = tree[leftId];
                int rightId = in.nextInt();
                if (rightId != -1) tree[i].right = tree[rightId];
            }
            System.out.println(checker.isHeap(tree[0]));
            n = in.nextInt();

        }
        in.close(); // close file
    }
}
