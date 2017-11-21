package LeetCode;

/**
 6. ZigZag Conversion

 The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

 P   A   H   N
 A P L S I I G
 Y   I   R
 And then read line by line: "PAHNAPLSIIGYIR"
 Write the code that will take a string and make this conversion given a number of rows:

 string convert(string text, int nRows);
 convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".

 */
public class L_Solution_6 {
    /**
     * @param s
     * @param numRows
     * @return
     *
     * for each row, setup a StringBuilder. Move vertically to construct the substrings
     *
     * Time - O(n)
     * Space - O(n)
     */
    public String convert(String s, int numRows) {
        char[] arr = s.toCharArray();
        StringBuilder[] rows = new StringBuilder[numRows];
        for(int i = 0; i < numRows; i++){
            rows[i] = new StringBuilder();
        }

        int i = 0;
        while(i < s.length()){
            int k = 0;
            while(k < numRows && i < s.length()){
                rows[k++].append(arr[i++]);
            }

            k = numRows - 2;
            while(k > 0 && i < s.length()){
                rows[k--].append(arr[i++]);
            }
        }

        StringBuilder res = new StringBuilder();
        for(i = 0; i < numRows; i++){
            res.append(rows[i].toString());
        }

        return res.toString();
    }
}
