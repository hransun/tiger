package gp.gasstation2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/gp/gasstation2/input.txt"));

        int n = in.nextInt(); // num of gas station
        while (n!= -1) {
            int m = in.nextInt(); // max gas volume
            int[] gas  = new int[n];
            int[] dis = new int[n];
            for (int i = 0; i < n; i++) {
                gas[i] = in.nextInt();
                dis[i] = in.nextInt();
            }
            int trip = 0;
            int tank = 0;
            int begin = 0;
            for (int i = 0; i < 2*n; i++) {
                int curr = i%n;
                tank += gas[curr]; // add gas in the station
                if (tank  > m) tank = m; // check the max gas
                tank -= dis[curr]; // go to the next station
                if (tank < 0) {//  failed
                    begin = i+1;
                    tank = 0;
                    trip = 0;
                } else {
                    if (++trip == n) break;
                }
            }
            System.out.println(trip ==  n? begin:-1);
            n = in.nextInt();


        }
        in.close(); // close file

    }
}
