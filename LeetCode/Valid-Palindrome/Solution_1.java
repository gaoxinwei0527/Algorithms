public class Solution_1 {
    public boolean isPalindrome(String s) {
        if(s.length()==0) return true;
        s=s.toLowerCase();
        s=s.replaceAll("[^a-z0-9]","");
        if(s.length()==0) return true;
        int half=s.length()/2;
        String l=s.substring(0,half);
        String r=s.length()%2==0?s.substring(half):s.substring(half+1);
        if(new StringBuilder(l).reverse().toString().equals(r)) return true;
        return false;
    }
}
