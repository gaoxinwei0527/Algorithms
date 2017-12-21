package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 138. Copy List with Random Pointer

 A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

 Return a deep copy of the list.
 */
public class L_Leetcode_138 {
    class RandomListNode {
        int label;
        RandomListNode next, random;
        RandomListNode(int x) { this.label = x; }
    };

    Map<Integer, RandomListNode> map = new HashMap<>();

    /**
     * @param head
     * @return
     *
     * Use hashmap to store copied nodes.
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null) return null;
        if(map.containsKey(head.label)) return map.get(head.label);
        RandomListNode cloned = new RandomListNode(head.label);
        map.put(head.label, cloned);
        cloned.next = copyRandomList(head.next);
        cloned.random = copyRandomList(head.random);
        return cloned;
    }
}
