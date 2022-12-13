package hashmap;

import com.apple.eawt.AppEvent;

import java.util.HashMap;
import java.util.Map;

public class SolutionTwoSum {
    private static int[]  twoSum(int[] numbers,int target) {
        // define return value
        int[] indexArray = new int[2];

        //handle corner case
        if (numbers==null || numbers.length==0) {
            return null;
        }

        // value -> index map
        Map<Integer,Integer> hashmap = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            hashmap.put(numbers[i],i);
        }

        // check every elements , check if target - element  exists in array
        // if exists,  we update  indexArray and return
        //otherwise , go to the next loop  and check  the next element
        for (int i = 0; i < numbers.length; i++) {
            if (hashmap.containsKey((target - numbers[i]))) {
                indexArray[0] = i;
                indexArray[1] = hashmap.get(target-numbers[i]);
                // we can not use the same element twice
                if  (indexArray[0] == indexArray[1]) {
                    continue;
                }
                return indexArray;
            }

        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        int  target = 9;
        int[] indexArray = twoSum(nums,target);
        System.out.printf("[%d,%d]",indexArray[0],indexArray[1]);
    }
}
