package gp.tmpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/gp/tmpl/input.txt"));
        int n = in.nextInt(); // how many lines of data
        while (n!=-1) {
            n = in.nextInt();
            System.out.println();
        }
        in.close(); // close file
    }
}
