package gp.meetingroom;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
class Event implements Comparable<Event> {
    int t; // time
    int state; // 1 begin  -1 end , end is before start
    Event(int t , int state) {
        this.t = t;
        this.state = state;
    }

    @Override
    public int compareTo(Event temp) {
        if ( t == temp.t) return Integer.compare(state,temp.state);
        return Integer.compare(t, temp.t);
    }
}
public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/gp/meetingroom/input.txt"));
        int n = in.nextInt(); // how many lines of data
        while (n!=-1) {
            Event[] timelines = new Event[n* 2]; // why , start and end?
            for (int i = 0; i < n; i++) {
                timelines[i* 2] = new Event(in.nextInt(),1);
                timelines[i* 2 +1] = new Event(in.nextInt(),-1);
            }
            Arrays.sort(timelines);

            int result = 0;
            int count = 0;
            for (int i = 0; i < timelines.length; i++) {
                count += timelines[i].state; // start +1 ,  end -1]
                if (count > result)  result = count;
            }
            System.out.println(result);
            n = in.nextInt();

        }
        in.close(); // close file
    }
}
