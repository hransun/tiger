package gp.happy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/gp/happy/input.txt"));
        int n = in.nextInt();
        Happyness happyness = new Happyness();
        for (int i = 2; i <=810 ; i++) {
            happyness.isHappy(i,1);
        }
        while(n != -1) {
            System.out.println(happyness.isHappy(happyness.transform(n),1));
            n = in.nextInt();
        }
        in.close();

    }
}
