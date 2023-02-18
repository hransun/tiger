package gp.fib;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
class Fib {
    HashMap<Integer,Integer> cache;
    Fib() {
        cache = new HashMap<>();
        cache.put(0,0);
        cache.put(1,1);

    }
    int get(int n) {
        if (cache.containsKey(n)) return cache.get(n);
        int result = get(n-1) + get(n-2);
        cache.put(n,result);
        return result;


    }
}
public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/gp/fib/input.txt"));
        int n = in.nextInt(); // how many lines of data
        Fib fib = new Fib();
        while (n!=-1) {
            System.out.println(fib.get(n));
            n = in.nextInt();
        }
        in.close(); // close file
    }
}
