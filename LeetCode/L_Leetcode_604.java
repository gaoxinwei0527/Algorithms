package LeetCode;

/**
 604. Design Compressed String Iterator

 Design and implement a data structure for a compressed string iterator. It should support the following operations: next and hasNext.

 The given compressed string will be in the form of each letter followed by a positive integer representing the number of this letter existing in the original uncompressed string.

 next() - if the original string still has uncompressed characters, return the next letter; Otherwise return a white space.
 hasNext() - Judge whether there is any letter needs to be uncompressed.

 Note:
 Please remember to RESET your class variables declared in StringIterator, as static/class variables are persisted across multiple test cases. Please see here for more details.

 Example:

 StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");

 iterator.next(); // return 'L'
 iterator.next(); // return 'e'
 iterator.next(); // return 'e'
 iterator.next(); // return 't'
 iterator.next(); // return 'C'
 iterator.next(); // return 'o'
 iterator.next(); // return 'd'
 iterator.hasNext(); // return true
 iterator.next(); // return 'e'
 iterator.hasNext(); // return false
 iterator.next(); // return ' '
 */
public class L_Leetcode_604 {
    class StringIterator {
        String compressed;
        int i;

        char c;
        int count;
        int total;


        public StringIterator(String compressedString) {
            this.compressed = compressedString;
            i = 0;
        }

        public char next() {
            if(count < total){
                count++;
                return c;
            }else{
                if(i == compressed.length()) return ' ';
                c = compressed.charAt(i++);
                int j = i;
                while(j < compressed.length() && compressed.charAt(j) >= '0' && compressed.charAt(j) <= '9') j++;
                total = Integer.parseInt(compressed.substring(i, j));
                i = j;
                count = 1;
                return c;
            }
        }

        public boolean hasNext() {
            if(count < total) return true;
            if(i == compressed.length()) return false;
            return true;
        }
    }
}
