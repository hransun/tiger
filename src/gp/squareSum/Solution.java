package gp.squareSum;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
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
    Perfect() {
        cache = new HashMap<>();
    }
    int search(int n ) {
        // cache result
        if  (cache.containsKey(n)) {
            return cache.get(n);

        }
            int result=n;
        //  start from n^0.5
        int ceiling = (int)Math.sqrt(n);
        if (ceiling*ceiling==n) result = 1;
        else {
            for (int i = ceiling; i>0 ; i--) {
                int temp = search(n - i*i);
                if (temp+1 < result) result = temp+1;
                if (temp == 1) break;

            }
                    }


        cache.put(n,result);
        return  result;
    }
}
public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/gp/squareSum/input.txt"));
        int n = in.nextInt();
        Perfect perfect = new Perfect();
        while (n!= -1) {
            long start = System.nanoTime();

            System.out.println(n+ " Result: " + perfect.search(n));
            long end = System.nanoTime();
            System.out.println("Time: " + (end-start)/1000000 + "ms");
            n = in.nextInt();
        }

        in.close();

    }
}
