package gp.happy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

/**
 * 判断一个正整数是否是快乐数字？
 * 如果一个数字能够通过有限次快乐变换成为1，则是快乐数字。
 * 快乐变换是对一个数字的每一位的平方数求和。
 * 例如：
 * 对于68
 * •	68 => 6^2+8^2= 100
 * •	100 => 1^2 + 0^2 + 0^2 = 1
 * 因此68是快乐数字
 * 输入输出
 * 输入
 * •	第一行是n；如果n为-1，表示输入结束
 * 输出
 * •	针对每组数据，输出是否是快乐数字 true/false
 * 示例输入
 * 68
 * -1
 * 示例输出
 * true
 */
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
