package gp.squareSum;

import com.sun.deploy.security.CeilingPolicy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

/**
 对于一个非负整数n，最少需要几个完全平方数，使其和为n？
 输入输出
 输入
 •	第一行是n；如果n为-1，表示输入结束
 输出
 •	针对每组数据，输出最少需要的完全平方数
 示例输入
 3
 4
 5
 -1
 示例输出
 3
 1
 2

 */



class Perfect {
    HashMap<Integer,Integer> cache;
    int best;
    Perfect() {
        cache = new HashMap<>();
    }
    int search(int n , int depth) {
        //prone
        if (depth > best) return -1;
        // cache result
        if  (cache.containsKey(n)) {
            return cache.get(n);

        }
            int result=-1;
        //  start from n^0.5
        int ceiling = (int)Math.sqrt(n);
        if (ceiling*ceiling==n) {
            result = 1;
            if (depth<best) best = result;
        }
        else {
            for (int i = ceiling; i>0 ; i--) {
                int temp = search(n - i*i,depth+1);
                if  (temp == -1) continue;

                if (result == -1 || temp+1 < result) result = temp+1;
                if (temp+depth < best) best = temp + depth;
                if (temp == 1) break;

            }
                    }


        if (result!= -1) cache.put(n,result);
        return  result;
    }

    int bfs(int n) {
        LinkedList<Integer> processing = new LinkedList<>();
        processing.add(n);
        processing.add(-1);
        HashSet<Integer> used = new HashSet<>();
        int  need = 1; // how many levels
        while (!processing.isEmpty()) {
            int  temp = processing.pollFirst(); // cur processing number
            if (temp <0) { // why here calc level info , level info all is minus -1, -2, -3...
                processing.addLast(temp-1); //
                need++;
            } else {
                int ceiling  = (int) Math.pow(temp,0.5);
                if (ceiling* ceiling == temp) break;
                for (int i = ceiling;i >0;i--) {
                    if (used.add(temp - i*i)) processing.addLast(temp - i*i);
                }
            }

        }
        return need;
    }
}
public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/gp/squareSum/input.txt"));
        int n = in.nextInt();
        Perfect perfect = new Perfect();
        while (n!= -1) {
            long start = System.nanoTime();
            perfect.best = 4;

//            System.out.println(n+ " Result: " + perfect.search(n,1));
            System.out.println(n+ " Result: " + perfect.bfs(n));
            long end = System.nanoTime();
            System.out.println("Time: " + (end-start)/1000000 + "ms");
            n = in.nextInt();
        }

        in.close();

    }
}
