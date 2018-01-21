package LeetCode;

/**
 551. Student Attendance Record I

 You are given a string representing an attendance record for a student. The record only contains the following three characters:
 'A' : Absent.
 'L' : Late.
 'P' : Present.
 A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).

 You need to return whether the student could be rewarded according to his attendance record.

 Example 1:
 Input: "PPALLP"
 Output: True
 Example 2:
 Input: "PPALLL"
 Output: False
 */
public class L_Leetcode_551 {
    public boolean checkRecord(String s) {
        char[] arr = s.toCharArray();
        int A = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == 'A'){
                A++;
                if(A > 1) return false;
            }else if(arr[i] == 'L'){
                int j = i + 1;
                while(j < arr.length && arr[j] == 'L') j++;
                if(j - i > 2) return false;
                i = j - 1;
            }
        }

        return true;
    }
}
