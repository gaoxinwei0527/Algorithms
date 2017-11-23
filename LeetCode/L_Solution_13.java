package LeetCode;

/**
 13. Roman to Integer

 Given a roman numeral, convert it to an integer.

 Input is guaranteed to be within the range from 1 to 3999.

 Symbol	I	V	X	L	C	D	M
 Value	1	5	10	50	100	500	1,000
 */
public class L_Solution_13 {
    /**
     * @param s
     * @return
     *
     * Same as Integer to Roman.
     * List all building blocks in range [1, 3999]. And every time try to use the biggest building block.
     * note that in Roman, 4, 9, 40, 90, 400, 900 should all be specially handled (hence be building block)
     *
     * Time - O(n), because the num of building blocks are constant
     * Space - O(n)
     */
    public int romanToInt(String s) {
        int[] elements = new int[]{1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        String[] roman = new String[]{"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
        int i = 0;
        int res = 0;
        while(i < s.length()){
            for(int j = elements.length - 1; j >= 0; j--){
                if(s.substring(i).startsWith(roman[j])){
                    res += elements[j];
                    i += roman[j].length();
                }
            }
        }

        return res;
    }
}
