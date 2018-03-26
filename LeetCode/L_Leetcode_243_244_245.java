package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class L_Leetcode_243_244_245 {

    /**
     243. Shortest Word Distance

     Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

     For example,
     Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

     Given word1 = “coding”, word2 = “practice”, return 3.
     Given word1 = "makes", word2 = "coding", return 1.

     Note:
     You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
     */

    /**
     * @param words
     * @param word1
     * @param word2
     * @return
     *
     * two pointers
     *
     * Time - O(n)
     */
    public int shortestDistance(String[] words, String word1, String word2) {
        int i = 0;
        int j = 0;
        int res = Integer.MAX_VALUE;
        while(i < words.length && j < words.length){
            while(i < words.length && (!words[i].equals(word1))) i++;
            while(j < words.length && (!words[j].equals(word2))) j++;

            if(i == words.length || j == words.length) return res;
            if(j > i){
                res = Math.min(res, j - i);
                i++;
            }else{
                res = Math.min(res, i - j);
                j++;
            }
        }

        return res;
    }

    /**
     244. Shortest Word Distance II

     This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and your method will be called repeatedly many times with different parameters. How would you optimize it?

     Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list.

     For example,
     Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

     Given word1 = “coding”, word2 = “practice”, return 3.
     Given word1 = "makes", word2 = "coding", return 1.

     Note:
     You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
     */

    class WordDistance {
        Map<String, List<Integer>> map = new HashMap<>();

        /**
         * @param words
         *
         * store index list of all words
         * then for each query, we can do two pointer move on two index lists
         */
        public WordDistance(String[] words) {
            for(int i = 0; i < words.length; i++){
                if(!map.containsKey(words[i])) map.put(words[i], new ArrayList<>());
                map.get(words[i]).add(i);
            }
        }

        public int shortest(String word1, String word2) {
            List<Integer> l1 = map.get(word1);
            List<Integer> l2 = map.get(word2);
            int res = Integer.MAX_VALUE;
            int i = 0;
            int j = 0;
            while(i < l1.size() && j < l2.size()){
                if(l1.get(i) > l2.get(j)){
                    res = Math.min(res, l1.get(i) - l2.get(j));
                    j++;
                }else{
                    res = Math.min(res, l2.get(j) - l1.get(i));
                    i++;
                }
            }
            return res;
        }
    }

    /**
     245. Shortest Word Distance III

     This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.

     Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

     word1 and word2 may be the same and they represent two individual words in the list.

     For example,
     Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

     Given word1 = “makes”, word2 = “coding”, return 1.
     Given word1 = "makes", word2 = "makes", return 3.

     Note:
     You may assume word1 and word2 are both in the list.
     */

    /**
     * @param words
     * @param word1
     * @param word2
     * @return
     *
     *  same as 243. Shortest Word Distance.
     *  only when word1 & word2 are the same, we can get a index list of word1 (word2), then get the minimal index diff.
     *
     *  Time - O(n)
     */
    public int shortestWordDistance(String[] words, String word1, String word2) {
        if(!word1.equals(word2)){
            int i = 0;
            int j = 0;
            int res = Integer.MAX_VALUE;
            while(i < words.length && j < words.length){
                while(i < words.length && (!words[i].equals(word1))) i++;
                while(j < words.length && (!words[j].equals(word2))) j++;

                if(i == words.length || j == words.length) return res;
                if(j > i){
                    res = Math.min(res, j - i);
                    i++;
                }else{
                    res = Math.min(res, i - j);
                    j++;
                }
            }
            return res;
        }else{
            List<Integer> index = new ArrayList<>();
            for(int i = 0; i < words.length; i++){
                if(word1.equals(words[i])) index.add(i);
            }

            int res = Integer.MAX_VALUE;
            for(int i = 1; i < index.size(); i++){
                res = Math.min(res, index.get(i) - index.get(i - 1));
            }

            return res;
        }
    }

}
