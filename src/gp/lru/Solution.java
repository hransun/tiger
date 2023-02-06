package gp.lru;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
class LRUCache {
    // cache content
    class Node {
        Integer key;
        Integer value;
        Node prev;
        Node next;
        Node( Integer key,Integer value) {
            this.key = key;
            this.value = value;
            prev = null;
            next = null;
        }
    }
    private HashMap<Integer,Node> map;
    private int capacity;
    private Node head; // doubly linked list
    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(null,null);
        tail = new Node(null,null);
        // head -> tail
        head.next = tail;
        head.prev = tail;
        tail.next = head;
        tail.prev = head;
    }

    public void put(int key,int value) {
        // already existing
        Node node = map.get(key);
        if  (node == null) {
            if (map.size() == capacity) {
                // remove from head and attach from tail
                map.remove(head.next.key);
                detach(head.next);
            }
            node = new Node(key,value);
            map.put(key,node);
            attach(node);
        } else {
            // key already there
            node.value = value;
            detach(node);
            attach(node);
        }
    }
    public Integer get(int key) {
        Node node = map.get(key);
        if (node !=   null) {
            detach(node);
            attach(node);
        }
        return node == null?null:node.value;

    }

    private void attach(Node node) {
        node.next = tail;
        node.prev =  tail.prev;
        tail.prev.next = node;
        tail.prev = node;
    }

    private void detach(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
    }


}
public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/gp/lru/input.txt"));
        int capacity = in.nextInt(); // how many lines of data
        while (capacity!=-1) {
           LRUCache cache = new LRUCache(capacity);
           int m = in.nextInt(); // num of command
            while (m-- != 0) {
                in.nextLine();
                String op = in.next();
                if (op.equals("SET")) cache.put(in.nextInt(),in.nextInt());
                else System.out.println(cache.get(in.nextInt()));
            }
           capacity = in.nextInt();
        }
        in.close(); // close file
    }
}
