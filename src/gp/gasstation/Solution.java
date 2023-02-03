package gp.gasstation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;


public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/gp/gasstation/input.txt"));
        int n = in.nextInt();
        while (n!= -1) {
            int tank = 0;
            int  begin = 0;
            int total = 0;
            for (int i = 0; i < n; i++) {
                int diff = in.nextInt() - in.nextInt(); // gas - distance
                total += diff;
                if (tank + diff >=0) {
                    tank += diff;
                } else { // diff +tank < 0
                    begin = i+1;
                    tank = 0;
                }
            }
            System.out.println(begin< n && total >= 0? begin:-1);
            n = in.nextInt();


        }
        in.close(); // close file

    }
}
