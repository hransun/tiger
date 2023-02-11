package gp.ksmall;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Quicker {
    int select(int[] nums, int k) {
        return select(nums,0,nums.length-1,k -1);

    }
    int  select(int[] nums,int start, int end, int k) { // recursion call
        int pivot = nums[end];
        int left = start;
        for (int i = start; i <end ; i++) {// exit i == end
            if (nums[i] < pivot) {
                //
                swap(nums,i,left++);
            }
        }

        // finish one pivot
        if (left == k) return nums[end];
        // discard left side 3 partitions , left of left point how many?
        if (left < k) return select(nums,left,end -1,k - (left-start) -1);
        // left > k , discard right
        else return select(nums,0,left -1,k);
    }
    void swap(int[] nums,int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/gp/ksmall/input.txt"));
        int n = in.nextInt(); // nums.length
        Quicker quicker = new Quicker();
        while (n!=-1) {
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = in.nextInt();
            }
            int m = in.nextInt();
            while (m--!=0) {
                System.out.println(quicker.select(nums,in.nextInt()));
            }
            n = in.nextInt();
        }
        in.close(); // close file
    }
}
