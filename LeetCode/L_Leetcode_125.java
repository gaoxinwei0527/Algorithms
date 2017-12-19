package LeetCode;

/**
 125. Valid Palindrome

 Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

 For example,
 "A man, a plan, a canal: Panama" is a palindrome.
 "race a car" is not a palindrome.

 Note:
 Have you consider that the string might be empty? This is a good question to ask during an interview.

 For the purpose of this problem, we define empty string as valid palindrome.
 */
public class L_Leetcode_125 {
    /**
     * @param s
     * @return
     *
     * Time - O(n)
     */
    public boolean isPalindrome(String s) {
        char[] arr = s.replaceAll("\\s+", "").toLowerCase().replaceAll("[^0-9a-z]", "").toCharArray();
        int i = 0;
        int j = arr.length - 1;
        while(i < j){
            if(arr[i++] != arr[j--]) return false;
        }
        return true;
    }
}
