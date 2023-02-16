package gp.tmpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Node {
    public int id;
    public ArrayList<Node> neighbors;
    Node(int id) {
        this.id = id;
        neighbors = new ArrayList<>();
    }
    public Node clone() {
        HashMap<Node , Node> map =  new HashMap<>();
        LinkedList<Node> processing = new LinkedList<>();
        map.put(this,new  Node(this.id));
        processing.add(this);
        // bfs
        while (!processing.isEmpty()) {
            Node temp = processing.pop();
            Node cloned = map.get(temp);
            for (Node node : temp.neighbors) {
                if (!map.containsKey(node)) {
                    // clone new node
                    map.put(node,new Node(node.id));
                    // add curr node to the queue
                    processing.add(node);
                }
                cloned.neighbors.add(map.get(node)); // map value is cloned node
            }
        }
        return map.get(this); // return cloned node
    }

    public void BFS() {
        HashSet<Node> visited = new HashSet<>();
        LinkedList<Node> processing = new LinkedList<>();
        visited.add(this);
        processing.add(this);
        while (processing.isEmpty() == false) {
            Node temp = processing.pop();
            System.out.print(temp.id + " ");
            for  (Node node: temp.neighbors) {
                if (visited.add(node)) { // node is not in the set so adding return 0
                    processing.add(node);
                }
            }
        }
        System.out.println();

    }
}

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/gp/clonegraph/input.txt"));
        int n = in.nextInt(); // how many lines of data
        while (n!=-1) {
            Node[] tree = new Node[n];
            for (int i = 0; i < n; i++) {
                tree[i] = new Node(i);
            }
            for (int i = 0; i < n; i++) {
                int m = in.nextInt(); // neighbors count
                while (m-- !=0) {
                    tree[i].neighbors.add(tree[in.nextInt()]);
                }
            }
            tree[0].clone().BFS();
            n = in.nextInt();
            System.out.println();
        }
        in.close(); // close file
    }
}
