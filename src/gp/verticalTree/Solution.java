package gp.verticalTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * 对于一个二叉树，输出它的垂直遍历结果；对于同一列的节点，按照从左向右，从上向下的顺序排列。
 * 特殊要求
 * •	请使用以下数据结构
 * class Node
 * {
 *  int data;
 *  Node left;
 *  Node right
 * }
 * 例如，对于以下二叉树：
 *   1
 *  / \
 * 2   3
 *    /
 *   4
 * 垂直遍历的结果是：2 1 4 3
 * 输入输出
 * 输入
 * •
 * 第一行是n，表示节点个数（节点编号从0到n-1）；当n=-1时，表示输入结束
 * •
 * •
 * 之后的n行，每一行有三个整数，分别表示：节点的数值，左子树的编号，右子树的编号（编号-1表示节点为空）
 * •
 * 输出
 * •	针对每组输入，输出垂直遍历的结果
 * 示例输入
 * 4
 * 1 1 2
 * 2 -1 -1
 * 3 3 -1
 * 4 -1 -1
 * -1
 * 示例输出
 * 2 1 4 3
 */
class Node {
    int value;
    Node left;
    Node right;
}

class BFS {
    HashMap<Integer, LinkedList> rank;
    BFS() {
        rank = new HashMap<>();
    }
    // pos is the level /column of the node
    void traverse(Node ptr, int pos) {
        if (ptr == null) return;
        LinkedList process =  new LinkedList();
        process.add(ptr);
        process.add(pos);
        while ( !process.isEmpty()) {
            ptr = (Node) process.pollFirst();
            pos = (int)process.pollFirst();
            if (rank.containsKey(pos)== false) rank.put(pos,new LinkedList());
            rank.get(pos).add(ptr.value);
            if (ptr.left != null) {
                process.add(ptr.left);
                process.add(pos-1);
            }
            if (ptr.right!=null) {
                process.add(ptr.right);
                process.add(pos+1);

            }

        }


//        // process current node
//        if (rank.containsKey(pos)== false) rank.put(pos,new LinkedList());
//        rank.get(pos).add(ptr.value);
//        // process left
//        traverse(ptr.left,pos -1);
//        //process right
//        traverse(ptr.right,pos+1);
    }

    void display() {
        int pos = 0;
        while (rank.containsKey(pos)==true) {
            pos--;
        }
        for (int i = pos+1;rank.containsKey(i);++i) {
            rank.get(i).forEach((value)->System.out.print(" "+ value));
        }
        System.out.println();
    }
}
class DFS {
    HashMap<Integer, LinkedList> rank;
    DFS() {
        rank = new HashMap<>();
    }
    // pos is the level /column of the node
    void traverse(Node ptr, int pos) {
        if (ptr == null) return;
        // process current node
        if (rank.containsKey(pos)== false) rank.put(pos,new LinkedList());
        rank.get(pos).add(ptr.value);
        // process left
        traverse(ptr.left,pos -1);
        //process right
        traverse(ptr.right,pos+1);
    }

    void display() {
        int pos = 0;
        while (rank.containsKey(pos)==true) {
            pos--;
        }
        for (int i = pos+1;rank.containsKey(i);++i) {
            rank.get(i).forEach((value)->System.out.print(" "+ value));
        }
        System.out.println();
    }
}
public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/gp/verticalTree/input.txt"));
        int n = in.nextInt(); // how many lines of data
        while (n!=-1) {
            //build tree
            Node[] tree = new Node[n];
            for (int i = 0; i < n; i++) tree[i] = new Node();
            for (int i = 0; i < n; i++) {
                tree[i].value = in.nextInt();
                int leftID = in.nextInt();
                if (leftID != -1) tree[i].left = tree[leftID];
                int rightID = in.nextInt();
                if (rightID != -1) tree[i].right = tree[rightID];
            }
//            DFS dfs = new DFS();
//            dfs.traverse(tree[0],0 );
//            dfs.display();
            BFS bfs = new BFS();
            bfs.traverse(tree[0],0 );
            bfs.display();
            n = in.nextInt();

        }
        in.close(); // close file
    }
}
