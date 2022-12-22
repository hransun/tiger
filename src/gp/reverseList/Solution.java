package gp.reverseList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 翻转一个链表
 * 特殊要求
 * •	请使用以下链表结构
 * class Node
 * {
 *     int value;
 *     Node next;
 * }
 * 输入输出
 * 输入
 * •
 * 第一行是n，表示链表长度；当n=-1时，表示输入结束
 * •
 * •
 * 第二行是n个整数，表示链表每一位所存储的内容
 * •
 * 输出
 * •	针对每组输出，输出翻转后的链表的内容
 * 示例输入
 * 4
 * 1 3 5 7
 * -1
 * 示例输出
 * 7 5 3 1

 */
class  Node {
    int value;
    Node  next;
    Node() {
        next = null;
    }
    public void display() {
        Node head = this;
        while (head !=null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }
}

class Iteration {
    public Node reverse(Node head) {

        Node cur = head;
        Node pre = null;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return  pre;
    }

}

class Recursion {
    public Node reverse(Node head) {
        if (head == null || head.next == null) return head;
        Node newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/gp/reverseList/input.txt"));
        int n = in.nextInt();
        while (n!= -1) {
            Node head = null;
            Node pre = null;
            // build linkedlist
            while (n-- !=0) { // number of elements e.g. 4, 10.
                Node  temp = new Node();
                temp.value = in.nextInt();
                if (head == null) {
                    head = temp;
                } else {
                    pre.next = temp;
                }

                pre = temp;
            }
//            Iteration itr = new Iteration();
            Recursion rec = new Recursion();
            head.display();
            Node newHead = rec.reverse(head);
            newHead.display();

            n = in.nextInt();
        }

        in.close();

    }
}
