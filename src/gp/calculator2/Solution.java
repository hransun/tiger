package gp.calculator2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;


class Calculator {
    HashMap<Character,Integer> priority;
    Calculator() {
        priority = new HashMap<>();
        priority.put('+',1);
        priority.put('-',1);
        priority.put('*',2);
        priority.put('/',2);
        priority.put('^',3);
        priority.put('(',0);

    }
    int eval(String equation) {
        int num = 0;
        Stack<Integer> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();
        for (int i =0;i < equation.length();++i) {
            char next = equation.charAt(i);
            switch (next) {
                case '(':
                    ops.add(next);
                    break;
                case ')':
                    while (ops.peek() != '(') {
                        nums.add(cal(nums.pop(),nums.pop(),ops.pop()));
                    }
                    ops.pop(); // pop left (
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                case '^':
                    if (ops.isEmpty() || priority.get(next) > priority.get(ops.peek())) {
                        // add op
                        ops.add(next);
                    } else {
                        // need to calc value
                        nums.add(cal(nums.pop(),nums.pop(),ops.pop()));
                        // add  current op
                        ops.add(next);
                        }
                    break;
                default:
                    // deal with number
                    num = num * 10 + ( int)(next - '0');
                    if (!Character.isDigit(equation.charAt(i+1))) {
                        // is op then add num
                        nums.add(num);
                        num = 0;

                    }
            }

        }
        return nums.pop();
    }

    int cal(int  second,int first,char op) {
        switch (op) {
            case '+':
                return first+ second;
            case '-':
                return first - second;
            case '*':
                return first * second;
            case '/':
                return first / second;
            case '^':
                return (int)Math.pow(first,second);
        }
        return -1;

    }
}

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/gp/calculator2/input.txt"));
        String equation = in.nextLine();
        Calculator cal = new Calculator();
        while (!equation.equals("END")) {
            System.out.println(cal.eval("("+equation + ")")); // safe zone
            equation = in.nextLine();
        }
        in.close(); // close file
    }
}
