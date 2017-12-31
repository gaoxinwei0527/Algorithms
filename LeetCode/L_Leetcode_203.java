package LeetCode;

/**
 203. Remove Linked List Elements

 Remove all elements from a linked list of integers that have value val.

 Example
 Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
 Return: 1 --> 2 --> 3 --> 4 --> 5

 Credits:
 Special thanks to @mithmatt for adding this problem and creating all test cases.
 */
public class L_Leetcode_203 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * @param head
     * @param val
     * @return
     *
     * iterative way, track the prev node and do one pass traverse.
     *
     * Time - O(n)
     * Space - O(1)
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        while(cur != null){
            if(cur.val == val){
                pre.next = cur.next;
                cur.next = null;
                cur = pre.next;
            }else{
                cur = cur.next;
                pre = pre.next;
            }
        }
        return dummy.next;
    }

    /**
     * @param head
     * @param val
     * @return
     *
     * recursive way
     */
    public ListNode removeElements2(ListNode head, int val) {
        if(head == null) return head;
        if(head.val == val) return removeElements2(head.next, val);
        head.next = removeElements2(head.next, val);
        return head;
    }
}
