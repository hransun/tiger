package Queue;



/**
 * Implement a queue
 */
public class Queue {
    private  static class Node {
        private int val;
        private Node next;
        private Node(int x) {
            this.val = x;
        }
    }

    private Node head; // remove from the head
    private Node tail; // add more here

    public boolean isEmpty() {
        return head == null;
    }

    public int peek() {
        return  head.val;
    }

    public void add(int  value) {
        Node node = new Node(value);
        if  (tail != null) {
            tail.next = node;
        }

        tail = node;
        if (head == null) {
            head = node;
        }
    }

    public int remove() {
        int value = head.val;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return value;
    }
}
