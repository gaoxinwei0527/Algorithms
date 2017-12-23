package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 146. LRU Cache

 Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

 get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

 Follow up:
 Could you do both operations in O(1) time complexity?

 Example:

 LRUCache cache = new LRUCache( 2 capacity );

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4
 */
public class H_Leetcode_146 {
    /**
     * Use doubly-linked list to maintain recent usage sequence
     * And use hash map to guarantee fast query of ListNode
     * If the cache reaches capacity, then remove the ListNode at tail.
     *
     * Time - O(1) for both get & put
     */
    class LRUCache {
        private class ListNode{
            public int key;
            public int val;
            public ListNode pre;
            public ListNode next;

            public ListNode(int key, int val){
                this.key = key;
                this.val = val;
                this.pre = null;
                this.next = null;
            }
        }

        ListNode head;
        ListNode tail;
        Map<Integer, ListNode> map;
        int capacity;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.head = new ListNode(0, 0);
            this.tail = new ListNode(0, 0);
            head.next = tail;
            tail.pre = head;
            map = new HashMap<>();
        }

        public int get(int key) {
            if(!map.containsKey(key)) return -1;
            ListNode target = map.get(key);
            moveToHead(target);
            return target.val;
        }

        public void put(int key, int value) {
            if(map.containsKey(key)){
                ListNode target = map.get(key);
                moveToHead(target);
                target.val = value;
            }else{
                ListNode target = new ListNode(key, value);
                map.put(key, target);
                target.next = head.next;
                target.pre = head;
                head.next.pre = target;
                head.next = target;

                if(map.size() > capacity){
                    ListNode removed = tail.pre;
                    map.remove(removed.key);
                    tail.pre = removed.pre;
                    tail.pre.next = tail;
                    removed.pre = null;
                    removed.next = null;
                }
            }
        }

        private void moveToHead(ListNode target){
            target.pre.next = target.next;
            target.next.pre = target.pre;
            target.next = head.next;
            target.pre = head;
            head.next.pre = target;
            head.next = target;
        }
    }
}
