package Company.Amazon.OA;

import java.util.*;

/**
 Given a string input & a integer K
 find all substrings of input with size K and include K distinct chars

 input only includes lower case letter
 */
public class SubstringK {
    public List<String> substringKDist(String input, int K){
        int[] hash = new int[26];
        int i = 0;
        int j = 0;

        Set<String> res = new HashSet<>();
        while(j < input.length()){
            while(j < input.length() && (j - i < K) && hash[input.charAt(j) - 'a'] == 0){
                char c = input.charAt(j);
                hash[c - 'a']++;
                j++;
            }

            if(j - i == K) res.add(input.substring(i, j));
            if(j == input.length()) break;

            char c = input.charAt(j);
            if(hash[c - 'a'] == 1){
                while(hash[c - 'a'] > 0){
                    char next = input.charAt(i++);
                    hash[next - 'a']--;
                }
            }else{
                char next = input.charAt(i++);
                hash[next - 'a']--;
            }
        }

        return new ArrayList<>(res);
    }

    public static void main(String[] args){
        SubstringK substringK = new SubstringK();
        System.out.println(substringK.substringKDist("awaglknagawunagwkwagl", 4));
    }

    /**
     * @param inputString
     * @param num
     * @return
     *
     * a little different. instead of k distinct chars, it requires k - 1 distinct chars
     */
    public List<String> subStringsLessKDist(String inputString, int num) {
        // WRITE YOUR CODE HERE
        if(num <= 1) return new ArrayList<>();
        Set<String> res = new HashSet<>();
        Map<Character, Integer> window = new HashMap<>();
        int i = 0;
        int j = 0;
        for(; j < num; j++){
            char c = inputString.charAt(j);
            window.put(c, window.getOrDefault(c, 0) + 1);
        }

        if(window.size() == num - 1) res.add(inputString.substring(i, j));
        while(j < inputString.length()){
            char a = inputString.charAt(i++);
            char b = inputString.charAt(j++);
            window.put(a, window.get(a) - 1);
            if(window.get(a) == 0) window.remove(a);
            window.put(b, window.getOrDefault(b, 0) + 1);
            if(window.size() == num - 1) res.add(inputString.substring(i, j));
        }

        return new ArrayList<>(res);
    }
}
