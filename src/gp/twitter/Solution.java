package gp.twitter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Twitter {
    class Pair implements Comparable<Pair> {
        int pID; // publish twitter article id
        ListIterator<Integer> itr;
        Pair(int pID,ListIterator<Integer> itr) {
            this.pID = pID;
            this.itr = itr;
        }

        @Override
        public int compareTo(Pair other) {
            return other.pID - pID; // ascent
        }
    }
    HashMap<Integer,HashSet<Integer>> relationship; // friends relations
    HashMap<Integer,LinkedList<Integer>> post; // post article

    Twitter() {
        relationship = new HashMap<>();
        post = new HashMap<>();
    }

    void subscribe(int uID, int followID) {
        if (uID == followID) return;
        if (!relationship.containsKey(uID)) relationship.put(uID,new HashSet<>());
        relationship.get(uID).add(followID);

    }

    void unsubscribe(int uID, int followID) {
        if (!relationship.containsKey(uID)) return;
        relationship.get(uID).remove(followID);
    }

    void publish(int uID,int  pID) {
        if (!post.containsKey(uID)) post.put(uID,new LinkedList<>());
        post.get(uID).addFirst(pID); // newest is in the first, insert from header.
    }

    ArrayList<Integer> get(int uID,int limit) {
        ArrayList<Integer> result =  new ArrayList<>();
        PriorityQueue<Pair> pq =  new PriorityQueue<>();
        if (post.containsKey(uID)) {
            ListIterator<Integer> itr = post.get(uID).listIterator();
            pq.add(new Pair(itr.next(),itr)); // user self publish
        }
        if (relationship.containsKey(uID)) {
            for (int following : relationship.get(uID)) {
                if (post.containsKey(following)) { // user's following publish
                    ListIterator<Integer> itr = post.get(following).listIterator();
                    pq.add(new Pair(itr.next(),itr));
                }
            }
        }
        while (!pq.isEmpty() && result.size() < limit) {
            Pair temp = pq.poll();
            result.add(temp.pID);
            if (temp.itr.hasNext()) {
                temp.pID = temp.itr.next();
                pq.add(temp);
            }
        }
        return result;
    }
}
public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/gp/twitter/input.txt"));
        Twitter twitter = new Twitter();
        int uID = in.nextInt();
        while (uID != -1) {
            String op = in.next();
            switch (op) {
                case "Publish":
                    twitter.publish(uID,in.nextInt());
                    break;
                case "Subscribe":
                    twitter.subscribe(uID,in.nextInt());
                    break;
                case "Unsubscribe":
                    twitter.unsubscribe(uID, in.nextInt());
                    break;
                case "Get":
                    twitter.get(uID,10).forEach((pID)-> System.out.print(pID + " "));
                    System.out.println();
                    break;
            }
            uID = in.nextInt();
        }
        in.close(); // close file
    }
}
