package LeetCode;

import sun.jvm.hotspot.utilities.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L_Leetcode_252_253 {
    public class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    /**
     252. Meeting Rooms

     Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

     For example,
     Given [[0, 30],[5, 10],[15, 20]],
     return false.
     */

    /**
     * @param intervals
     * @return
     *
     * sort & check overlap
     */
    public boolean canAttendMeetings(Interval[] intervals) {
        Arrays.sort(intervals, (Interval a, Interval b) -> {
            if(a.start != b.start) return a.start - b.start;
            return a.end - b.end;
        });

        int t = -1;
        for(Interval i : intervals){
            if(i.start < t) return false;
            t = i.end;
        }

        return true;
    }

    /**
     253. Meeting Rooms II

     Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

     For example,
     Given [[0, 30],[5, 10],[15, 20]],
     return 2.
     */

    /**
     * @param intervals
     * @return
     *
     * sort & greedily reuse rooms.
     */
    public int minMeetingRooms(Interval[] intervals) {
        if(intervals.length == 0) return 0;
        Arrays.sort(intervals, (Interval a, Interval b) -> {
            if(a.start != b.start) return a.start - b.start;
            return a.end - b.end;
        });

        List<Integer> t = new ArrayList<>();
        t.add(-1);

        for(Interval i : intervals){
            for(int k = 0; k < t.size(); k++){
                if(t.get(k) <= i.start){
                    int x = t.remove(k);
                    break;
                }
            }
            t.add(i.end);
        }

        return t.size();
    }
}
