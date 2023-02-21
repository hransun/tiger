package gp.gold;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

class Street {
    int[] houses;
    HashMap<Integer,Integer> cache;
    Street(int[] houses) {
        this.houses = houses;
        cache = new HashMap<>();
    }

    int findResult(int pos , int  count) {
        // pos  curr house index,
        // count  continued steal house count
        if (pos == houses.length) return 0;
        if (cache.containsKey(3 * pos + count)) return cache.get(3 * pos + count);
        int  butou = findResult(pos+1,0);
        int tou = 0;
        if  (count < 2) {
            tou = houses[pos] + findResult(pos+1,count+1);
        }
        cache.put(3 * pos + count,Math.max(tou,butou));
        return cache.get(3 * pos + count); // from pos  to end  how many to rob

    }
}

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/gp/gold/input.txt"));
        int n = in.nextInt(); // how many lines of data

        while (n!=-1) {
            int[] houses = new int[n];
            for (int i = 0; i < n; i++) {
                houses[i] = in.nextInt();
            }
            Street st = new Street(houses);
            int x = st.findResult(0,0);
            System.out.print(x);
            n = in.nextInt();
        }
        in.close(); // close file
    }
}
