package gp.calculator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;


class Calculator {
    int eval(String equation) {
        int num = 0;
        Stack<Integer> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();
        for (int i =0;i < equation.length();++i) {
            char next = equation.charAt(i);
            switch (next) {
                case '+':
                case '-':
                    // last time num
                    nums.add(num);
                    num = 0;
                    if (nums.size()> 1) {
                       nums.add(cal(nums.pop(),nums.pop(),ops.pop()));
                    }
                    ops.add(next);
                    break;
                default:
                    // deal with number
                    num = num * 10 + ( int)(next - '0');
            }

        }
        nums.add(num); // as last number not added to the stack
        while (nums.size()> 1) { // calculate last equation
            nums.add(cal(nums.pop(),nums.pop(),ops.pop()));
        }
        return nums.pop();
    }

    int cal(int  second,int first,char op) {
        switch (op) {
            case '+':
                return first+ second;
            case '-':
                return first - second;
        }
        return -1;

    }
}
public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/gp/calculator/input.txt"));
        String equation = in.nextLine();
        Calculator cal = new Calculator();
        while (!equation.equals("END")) {
            System.out.println(cal.eval(equation));
            equation = in.nextLine();
        }
        in.close(); // close file
    }
}
