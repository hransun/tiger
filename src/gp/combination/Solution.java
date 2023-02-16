package gp.combination;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Traveler {
    int result;
    Traveler() {
        result = 0;
    }
    void travel (int[] nums,int pos, int sum, LinkedList<Integer> trip) { // dfs
        if (sum == 0) {
            ++result;
        }
        if (pos == nums.length ) return;
        for (int i = pos; i <nums.length ; i++) {
            if (i > pos && nums[i] == nums[i-1]) continue;
            trip.add(nums[i]);
            travel(nums,i+1,sum + nums[i],trip);
            trip.removeLast();
        }
    }
}
public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/gp/combination/input.txt"));
        int n = in.nextInt(); // how many lines of data
        while (n!=-1) {
            Traveler traveler = new Traveler();
            int m = in.nextInt();
            int[] nums = new int[m];
            for (int i = 0; i < m; i++) {
                nums[i] = in.nextInt();
            }
            Arrays.sort(nums);
            LinkedList<Integer> trip = new LinkedList<>();
            traveler.travel(nums,0,-n,trip);
            System.out.println(traveler.result);
            n = in.nextInt();
        }
        in.close(); // close file
    }
}
