package LeetCode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 218. The Skyline Problem

 A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).

 Buildings  Skyline Contour
 The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

 For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

 The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

 For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

 Notes:

 The number of buildings in any input list is guaranteed to be in the range [0, 10000].
 The input list is already sorted in ascending order by the left x position Li.
 The output list must be sorted by the x position.
 There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
 Credits:
 Special thanks to @stellari for adding this problem, creating these two awesome images and all test cases.
 */
public class H_Leetcode_218 {
    /**
     * @param buildings
     * @return
     *
    /*
    Summary:
    基本思路： 把每个建筑抽象成两条边， 把所有边放入队列中排序，然后一次处理。
    处理方法： 记录当前所有重合建筑的高度 - overlap_height, 如果是左边， 则把该边的高度加入overlap_height， 如果高度刷新了原来的最大高度，则记录下坐标（pos，height）， 可理解为左上角顶点。 如果是右边， 则把该边的高度从overlap_height中移除 （可以理解为这个建筑的阴影结束），如果移除的是最高高度， 则记录坐标（pos， next highest height），可以理解为凹角处顶点。
    trick： 这个题难点在于边可以重合，所以边的排序上变的复杂。 理想的排序是-
    1. 如果位置不同，从左到右排
    2. 如果位置重合，左边优先于右边 （因为先处理右边，再处理左边，会产生两个坐标，而实际上两座建筑的阴影连接起来了，所以这两个是废坐标）
    3. 如果同为左边，按高度降序。 如果同为右边，按高度升序。 （可以理解为墙高的建筑包围墙矮的建筑，虽然它们的边重合。 如果重合左边是升序，在处理时，会依次上升刷新最高高度，因此每次都会产生新的废坐标。同理，如果重合右边是降序，每次处理都会弹出最高高度，产生废坐标）

    优化： 由于排序的复杂性，comparator ～ 15行。 其实把所有右边高度设成负数，可以巧妙的利用自然数倒序完成排序，代码会简洁很多。
     */
    public List<int[]> getSkyline(int[][] buildings) {
        PriorityQueue<int[]> walls = new PriorityQueue<>((int[] a, int[] b) ->{
            if(a[0] != b[0]) return a[0] - b[0];
            return b[1] - a[1];
        });

        for(int[] building : buildings){
            walls.offer(new int[]{building[0], building[2]});
            walls.offer(new int[]{building[1], -building[2]});
        }

        PriorityQueue<Integer> h = new PriorityQueue<>(Comparator.reverseOrder());
        h.offer(0);

        List<int[]> res = new ArrayList<>();
        while(!walls.isEmpty()){
            int[] next = walls.poll();
            int height = next[1];
            if(height > 0){
                if(height > h.peek()){
                    res.add(new int[]{next[0], height});
                }
                h.offer(height);
            }else{
                height = -height;
                h.remove(height);
                if(height > h.peek()){
                    res.add(new int[]{next[0], h.peek()});
                }
            }
        }

        return res;
    }
}
