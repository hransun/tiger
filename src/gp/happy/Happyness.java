package gp.happy;

import java.util.HashMap;
import java.util.HashSet;

class Happyness {
    HashMap<Integer,Boolean> cache;
    Happyness() {
        cache = new HashMap<>();
        cache.put(1,true);
    }
    boolean isHappy(int n,int depth) {

        while (cache.containsKey(n)) { // add  successful means n not in set
            System.out.println(depth);
            return cache.get(n);
        }
        cache.put(n,false);
        boolean result = isHappy(transform(n),depth+1);
        cache.replace(n,result);
        return result;
    }

    int transform(int n) {
        int result = 0;
        while (n != 0) {
            result += n % 10 * n % 10;
            n /= 10;
        }
        return result;
    }
}
