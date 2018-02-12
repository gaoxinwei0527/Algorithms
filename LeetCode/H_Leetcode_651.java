package LeetCode;

/**
 651. 4 Keys Keyboard

 Imagine you have a special keyboard with the following keys:

 Key 1: (A): Print one 'A' on screen.

 Key 2: (Ctrl-A): Select the whole screen.

 Key 3: (Ctrl-C): Copy selection to buffer.

 Key 4: (Ctrl-V): Print buffer on screen appending it after what has already been printed.

 Now, you can only press the keyboard for N times (with the above four keys), find out the maximum numbers of 'A' you can print on screen.

 Example 1:
 Input: N = 3
 Output: 3
 Explanation:
 We can at most get 3 A's on screen by pressing following key sequence:
 A, A, A
 Example 2:
 Input: N = 7
 Output: 9
 Explanation:
 We can at most get 9 A's on screen by pressing following key sequence:
 A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V
 Note:
 1 <= N <= 50
 Answers will be in the range of 32-bit signed integer.
 */
public class H_Leetcode_651 {
    /**
     * @param N
     * @return
     *
     * A(i) means the max num of 'A' with i ops end with press 'A'
     * AC(i) means the max num of 'A' with i ops end with press (Ctrl-A + Ctrl-C)
     * V(i) means the max num of 'A' with i ops end with press Ctrl-V
     * A[i] = Math.max(A[i - 1], V[i - 1]) + 1;
     * AC[i] = Math.max(A[i - 2], V[i - 2]);
     * for(int j = i - 1; j >= 3; j--){
     *      V[i] = Math.max(V[i], AC[j] * (i - j + 1));
     * }
     */
    public int maxA(int N) {
        if(N <= 3) return N;
        int[] A = new int[N + 1];
        int[] AC = new int[N + 1];
        int[] V = new int[N + 1];
        A[1] = 1;
        A[2] = 2;
        A[3] = 3;

        for(int i = 4; i <= N; i++){
            A[i] = Math.max(A[i - 1], V[i - 1]) + 1;
            AC[i] = Math.max(A[i - 2], V[i - 2]);
            for(int j = i - 1; j >= 3; j--){
                V[i] = Math.max(V[i], AC[j] * (i - j + 1));
            }
        }

        return Math.max(A[N], V[N]);
    }
}
