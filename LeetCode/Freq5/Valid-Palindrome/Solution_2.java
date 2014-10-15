/*
Use two indexs move from side to mid.
Complexity: O(n)
*/

public class Solution_2 {
    public boolean isPalindrome(String s) {
        int i=0,j=s.length()-1;
        while(i<j){
            if(!Character.isDigit(s.charAt(i))&&!Character.isAlphabetic(s.charAt(i))){
                i++;
                continue;
            }else if(!Character.isDigit(s.charAt(j))&&!Character.isAlphabetic(s.charAt(j))){
                j--;
                continue;
            }else{
                if(!s.substring(i,i+1).toLowerCase().equals(s.substring(j,j+1).toLowerCase())) return false;
                i++;
                j--;
            }
        }
        return true;
    }
}
