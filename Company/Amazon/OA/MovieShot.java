package Company.Amazon.OA;

import java.util.*;

/**
 * given list of movie shots represented as chars. partition them so that chars included in each partition not included in any other partition.
 * model shot occurrence window as intervals, and then do interval merge to get non-overlap intervals
 */
public class MovieShot {
    private static class Interval{
        int l;
        int r;
        public Interval(int l, int r){
            this.l = l;
            this.r = r;
        }
    }

    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    List<Integer> lengthEachScene(List<Character> inputList)
    {
        // WRITE YOUR CODE HERE

        // if no input shot, return empty list
        if(inputList.size() == 0) return new ArrayList<>();

        // find left-most & right-most indexes for each chracter (shot)
        Map<Character, Integer> left = new HashMap<>();
        Map<Character, Integer> right = new HashMap<>();
        for(int i = 0; i < inputList.size(); i++){
            char c = inputList.get(i);
            if(!left.containsKey(c)){
                left.put(c, i);
                right.put(c, i);
            }else{
                right.put(c, i);
            }
        }

        // sort the occurrance window of each character
        PriorityQueue<Interval> q = new PriorityQueue<>((Interval a, Interval b) -> {
            if(a.l != b.l) return a.l - b.l;
            return a.r - b.r;
        });

        for(char c : left.keySet()){
            Interval i = new Interval(left.get(c), right.get(c));
            q.offer(i);
        }

        // if windows have overlap, merge them to get a bigger window, until no overlap among windows.
        // size of each non-overlap window can be added to result
        List<Integer> res = new ArrayList<>();
        int start = q.peek().l;
        int end = q.poll().r;
        while(!q.isEmpty()){
            Interval next = q.poll();
            if(next.l < end){
                end = Math.max(end, next.r);
            }else{
                res.add(end - start + 1);
                start = next.l;
                end = next.r;
            }
        }

        res.add(end - start + 1);
        return res;
    }
}
