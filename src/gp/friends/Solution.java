package gp.friends;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

class Node {
    LinkedList<Node> neighbors;
    boolean visited;
    Node() {
        visited = false;
        neighbors = new LinkedList<>();
    }
}

class Travel {
    void DFS(Node node) {
        if (node.visited) return;
        node.visited = true;
        node.neighbors.forEach( (neighbor) -> DFS(neighbor));
    }
}
public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/gp/friends/input.txt"));
        int n = in.nextInt(); // how many lines of data
        Travel travel = new Travel();
        while (n!=-1) {
            // build graph
            Node[] graph = new Node[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new Node();
            }
            int m = in.nextInt(); // relations
            while (m-- != 0) {
                int  first = in.nextInt();
                int  second = in.nextInt();
                // bi lateral relation
                graph[first].neighbors.add(graph[second]);
                graph[second].neighbors.add(graph[first]);

            }
            //dfs
            int count = 0;
            for (int i = 0; i < n; i++) { // n is nodes counter
                if (!graph[i].visited) {
                    count++;
                    travel.DFS(graph[i]);
                }
            }
            System.out.println(count);
            n = in.nextInt();
        }
        in.close(); // close file
    }
}
