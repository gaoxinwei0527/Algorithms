package LeetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class L_Leetcode_170 {
    /**
     * suppose more add then find
     */
    class TwoSum {
        Map<Integer, Integer> map = new HashMap<>();

        /** Initialize your data structure here. */
        public TwoSum() {
            //no-op
        }

        /** Add the number to an internal data structure.. */
        public void add(int number) {
            map.put(number, map.getOrDefault(number, 0) + 1);
        }

        /** Find if there exists any pair of numbers which sum is equal to the value. */
        public boolean find(int value) {
            for(int i : map.keySet()){
                int j = value - i;
                if((i == j && map.get(i) >= 2) || i != j && map.containsKey(j)){
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * suppose it's read heavy.
     * This impl won't pass leetcode, but it's worth mentioned here.
     */
    class TwoSum2 {
        Set<Integer> num = new HashSet<>();
        Set<Integer> sum = new HashSet<>();

        /** Initialize your data structure here. */
        public TwoSum2() {
            //no-op
        }

        /** Add the number to an internal data structure.. */
        public void add(int number) {
            for(int i : num){
                sum.add(i + number);
            }
            num.add(number);
        }

        /** Find if there exists any pair of numbers which sum is equal to the value. */
        public boolean find(int value) {
            return sum.contains(value);
        }
    }
}
