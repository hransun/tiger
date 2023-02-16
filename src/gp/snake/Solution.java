package gp.snake;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ForkJoinPool;

class Snake {
    class Point {
        int x ;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    LinkedList<Point> body; // record shape of the snake
    char[][] space; // board
    int spaceSize;
    int direction; // 0 right, 1 down , 2 left, 3 up
    Point[] offset;
    Snake(int size) {
        this.spaceSize = size;
        direction = 0; // right
        body = new LinkedList<>();
        body.add(new Point(1,1)); // there's wall outside the board

        space =  new char[spaceSize+2][spaceSize+2];
        for (int i = 0; i < spaceSize + 2; i++) {
            space[0][i] = 'W' ;// 0 row
            space[spaceSize+1][i] = 'W'; // last row
            space[i][0] = 'W'; // 0 col
            space[i][spaceSize+1] = 'W';// last col
        }
        for (int i = 1; i <= spaceSize; i++) {
            for (int j = 1; j <=spaceSize; j++) {
                space[i][j] = '.'; // fill the board
            }
        }
        space[1][1] = 'S';
        offset = new Point[4];
        offset[0] = new Point(0,1);
        offset[1] = new Point(1,0);
        offset[2] = new Point(0,-1);
        offset[3] = new Point(-1,0);

    }

    void display() {
        for (int i = 0; i < spaceSize+2; i++) {
            for (int j = 0; j < spaceSize+2; j++) {
                System.out.print(space[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    boolean move(char order) {
        switch (order) {
            case 'L':
                direction = (direction +3) % 4;
                break;
            case 'R':
                direction = (direction+1) % 4;
                break;
        }
        Point head = body.getFirst();
        int newX = head.x + offset[direction].x;
        int newY = head.y + offset[direction].y;
        Point tail;
        switch (space[newX][newY]) {
            case 'W':
                return false;
            case  'o':
                space[newX][newY] = 'S';
                body.addFirst(new Point(newX,newY)); // add to the head of list
                break;
            case '.':
                space[newX][newY] = 'S';
                body.addFirst(new Point(newX,newY));
                // move tail
                tail = body.removeLast();
                space[tail.x][tail.y] ='.';
                break;
            case 'S':
                tail = body.getLast();
                if (tail.x != newX ||  tail.y != newY) return false;
                body.addFirst(body.removeLast());
                break;
        }
        return true;
    }
}


public class Solution {

    public static void main(String[] args) throws FileNotFoundException {
//        Scanner in = new Scanner(new File("src/gp/snake/input.txt"));
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // how many lines of data
        while (n!=-1) {
            Snake snake = new Snake(n); // create a board
            snake.display();
            int m = in.nextInt();
            while (m--!= 0) {
                int x = new Random().nextInt(n);
                int y = new Random().nextInt(n);

                snake.space[x+1][y+1] = 'o'; // offset the wall +1
                snake.display();
            }
            //in.nextLine();
            //String movement = in.nextLine();
            int move;
            move = in.nextInt();
            while   (move != -1) {
                switch (move) {
                    case 0:
                        snake.move('D');
                        break;
                    case 1: // left
                        snake.move('L');
                        break;
                    case 2: // right
                        snake.move('R');
                        break;
                }
                snake.display();
                move = in.nextInt();
            }
            n = in.nextInt();
        }
        in.close(); // close file
    }
}
