package LeetCode;

import java.util.*;

/**
 692. Top K Frequent Words

 Given a non-empty list of words, return the k most frequent elements.

 Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.

 Example 1:
 Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 Output: ["i", "love"]
 Explanation: "i" and "love" are the two most frequent words.
 Note that "i" comes before "love" due to a lower alphabetical order.
 Example 2:
 Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 Output: ["the", "is", "sunny", "day"]
 Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
 with the number of occurrence being 4, 3, 2 and 1 respectively.
 Note:
 You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 Input words contain only lowercase letters.
 Follow up:
 Try to solve it in O(n log k) time and O(n) extra space.
 */
public class L_Leetcode_692 {
    /**
     * @param words
     * @param k
     * @return
     *
     * priority queue
     */
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap<>();
        for(String word : words) count.put(word, count.getOrDefault(word, 0) + 1);
        PriorityQueue<String> q = new PriorityQueue<>((String a, String b) ->{
            if(count.get(a) != count.get(b)) return count.get(a) - count.get(b);
            int i = 0;
            while(i < a.length() && i < b.length()){
                if(a.charAt(i) != b.charAt(i)) return b.charAt(i) - a.charAt(i);
                i++;
            }

            return i == a.length() ? 1 : -1;
        });

        for(String word : count.keySet()){
            q.offer(word);
            if(q.size() > k) q.poll();
        }

        List<String> res = new ArrayList<>();
        while(!q.isEmpty()) res.add(q.poll());
        Collections.reverse(res);
        return res;
    }
}
