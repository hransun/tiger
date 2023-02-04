package gp.deduplicate1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/gp/deduplicate1/input.txt"));
        int n = in.nextInt(); // how many lines of data
        while (n!=-1) {
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = in.nextInt();
            }
            int last = 0;
            for (int i = 1; i < n; i++) {
                if (nums[i] != nums[i-1]) {
                    nums[++last] = nums[i];
                }

            }
            for (int i = 0; i <= last; i++) {
                if (nums.length == 0) return;
                System.out.print(nums[i] +  " ");
            }
            System.out.println();
            n = in.nextInt();

        }
        in.close(); // close file
    }
}
