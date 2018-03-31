package LeetCode;

import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 732. My Calendar III

 Implement a MyCalendarThree class to store your events. A new event can always be added.

 Your class will have one method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.

 A K-booking happens when K events have some non-empty intersection (ie., there is some time that is common to all K events.)

 For each call to the method MyCalendar.book, return an integer K representing the largest integer such that there exists a K-booking in the calendar.

 Your class will be called like this: MyCalendarThree cal = new MyCalendarThree(); MyCalendarThree.book(start, end)
 Example 1:
 MyCalendarThree();
 MyCalendarThree.book(10, 20); // returns 1
 MyCalendarThree.book(50, 60); // returns 1
 MyCalendarThree.book(10, 40); // returns 2
 MyCalendarThree.book(5, 15); // returns 3
 MyCalendarThree.book(5, 10); // returns 3
 MyCalendarThree.book(25, 55); // returns 3
 Explanation:
 The first two events can be booked and are disjoint, so the maximum K-booking is a 1-booking.
 The third event [10, 40) intersects the first event, and the maximum K-booking is a 2-booking.
 The remaining events cause the maximum K-booking to be only a 3-booking.
 Note that the last event locally causes a 2-booking, but the answer is still 3 because
 eg. [10, 20), [10, 40), and [5, 15) are still triple booked.
 Note:

 The number of calls to MyCalendarThree.book per test case will be at most 400.
 In calls to MyCalendarThree.book(start, end), start and end are integers in the range [0, 10^9].
 */
public class H_Leetcode_732 {
    /**
     * regard each book as Interval. rank all the intervals based upon start time, then end time.
     * process the intervals one by one, at the same time, maintain the min heap of the end time of current overlapping intervals.
     *
     */
    class MyCalendarThree {
        class Interval{
            int start;
            int end;
            public Interval(int start, int end){
                this.start = start;
                this.end = end;
            }
        }

        PriorityQueue<Interval> q;

        public MyCalendarThree() {
            q = new PriorityQueue<>((Interval a, Interval b) -> {
                if(a.start != b.start) return a.start - b.start;
                return a.end - b.end;
            });
        }

        public int book(int start, int end) {
            Interval cur = new Interval(start, end);
            q.offer(cur);
            PriorityQueue tmp = new PriorityQueue<>((Interval a, Interval b) -> {
                if(a.start != b.start) return a.start - b.start;
                return a.end - b.end;
            });

            PriorityQueue<Integer> minEnd = new PriorityQueue<>();
            int res = 0;
            while(!q.isEmpty()){
                Interval next = q.poll();
                while(!minEnd.isEmpty() && minEnd.peek() <= next.start) minEnd.poll();
                minEnd.offer(next.end);
                res = Math.max(res, minEnd.size());
                tmp.offer(next);
            }

            q = tmp;
            return res;
        }
    }

    /**
     * clearer way, regard both start & end time as time point, but differentiate them with some flag.
     * rank all the time points and process them one by one.
     * when get start point, do count++, else, do count--, update the result at the same time.
     */
    class MyCalendarThree2 {
        class Time{
            boolean start;
            int val;
            public Time(boolean start, int val){
                this.start = start;
                this.val = val;
            }
        }

        TreeSet<Time> set;

        public MyCalendarThree2() {
            set = new TreeSet<>((Time a, Time b) -> {
                if(a.val != b.val) return a.val - b.val;
                if(a.start) return 1;
                return -1;
            });
        }

        public int book(int start, int end) {
            Time a = new Time(true, start);
            Time b = new Time(false, end);
            set.add(a);
            set.add(b);

            int count = 0;
            int res = 0;
            for(Time next : set){
                if(next.start){
                    count++;
                    res = Math.max(res, count);
                }else{
                    count--;
                }
            }
            return res;
        }
    }
}
