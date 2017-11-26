package LeetCode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 23. Merge k Sorted Lists

 Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 */
public class L_Solution_23 {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * @param lists
     * @return
     *
     * Use priority queue to maintain sorted head for remaining lists. Each time append the smallest head to cur.
     *
     * Time - O(n), where n means num of all nodes
     * Space - length of lists array
     */
    public ListNode mergeKLists(ListNode[] lists) {
        int n = lists.length;
        if(n == 0) return null;
        PriorityQueue<ListNode> q = new PriorityQueue<>(n, new Comparator<ListNode>(){
            @Override
            public int compare(ListNode a, ListNode b){
                return a.val - b.val;
            }
        });

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for(int i = 0; i < n; i++){
            if(lists[i] != null) q.offer(lists[i]);
        }

        while(!q.isEmpty()){
            cur.next = q.poll();
            cur = cur.next;
            if(cur.next != null){
                q.offer(cur.next);
            }
            cur.next = null;
        }

        return dummy.next;
    }
}
