package LeetCode;

/**
 12. Integer to Roman

 Given an integer, convert it to a roman numeral.

 Input is guaranteed to be within the range from 1 to 3999.

 Symbol	I	V	X	L	C	D	M
 Value	1	5	10	50	100	500	1,000

 */
public class M_Solution_12 {
    /**
     * @param num
     * @return
     *
     * List all building blocks in range [1, 3999]. And every time try to use the biggest building block.
     * note that in Roman, 4, 9, 40, 90, 400, 900 should all be specially handled (hence be building block)
     *
     * Time - O(n), because the num of building blocks are constant
     * Space - O(n)
     */
    public String intToRoman(int num) {
        int[] elements = new int[]{1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        String[] roman = new String[]{"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
        StringBuilder res = new StringBuilder();
        while(num > 0){
            for(int i = elements.length - 1; i >= 0; i--){
                if(num >= elements[i]){
                    num -= elements[i];
                    res.append(roman[i]);
                    break;
                }
            }
        }

        return res.toString();
    }
}
