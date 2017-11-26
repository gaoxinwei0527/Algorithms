package LeetCode;

import java.util.Stack;

/**
 25. Reverse Nodes in k-Group

 Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

 k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

 You may not alter the values in the nodes, only nodes itself may be changed.

 Only constant memory is allowed.

 For example,
 Given this linked list: 1->2->3->4->5

 For k = 2, you should return: 2->1->4->3->5

 For k = 3, you should return: 3->2->1->4->5
 */
public class M_Solution_25 {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * @param head
     * @param k
     * @return
     *
     * simple iterative way with stack
     *
     * Time - O(n)
     * Space - O(k)
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        while(cur != null){
            Stack<ListNode> st = new Stack<>();
            int i = 0;
            while(i < k && cur != null){
                st.push(cur);
                cur = cur.next;
                i++;
            }

            if(st.size() >= k){
                while(!st.isEmpty()){
                    pre.next = st.pop();
                    pre = pre.next;
                }

                pre.next = cur;
            }
        }

        return dummy.next;
    }
}
