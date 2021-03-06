package LeetCode;

/**
 160. Intersection of Two Linked Lists

 Write a program to find the node at which the intersection of two singly linked lists begins.


 For example, the following two linked lists:

 A:          a1 → a2
 ↘
 c1 → c2 → c3
 ↗
 B:     b1 → b2 → b3
 begin to intersect at node c1.


 Notes:

 If the two linked lists have no intersection at all, return null.
 The linked lists must retain their original structure after the function returns.
 You may assume there are no cycles anywhere in the entire linked structure.
 Your code should preferably run in O(n) time and use only O(1) memory.
 Credits:
 Special thanks to @stellari for adding this problem and creating all test cases.
 */
public class M_Leetcode_160 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * @param headA
     * @param headB
     * @return
     *
     * because the question is asking for O(1) space. so we can count list A and B, and sync the head of list A and B to have same num of following nodes.
     * then each step move both pointers on A and B until they meet.
     *
     * Time - O(n)
     * Space - O(1)
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode cur1 = headA;
        int lenA = 0;
        while(cur1 != null){
            cur1 = cur1.next;
            lenA++;
        }

        ListNode cur2 = headB;
        int lenB = 0;
        while(cur2 != null){
            cur2 = cur2.next;
            lenB++;
        }

        cur1 = headA;
        cur2 = headB;
        while(lenA != lenB){
            if(lenA > lenB){
                cur1 = cur1.next;
                lenA--;
            }else{
                cur2 = cur2.next;
                lenB--;
            }
        }

        while(cur1 != cur2){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        return cur1;
    }
}
