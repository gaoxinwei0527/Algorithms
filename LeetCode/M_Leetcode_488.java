package LeetCode;

/**
 488. Zuma Game

 Think about Zuma Game. You have a row of balls on the table, colored red(R), yellow(Y), blue(B), green(G), and white(W). You also have several balls in your hand.

 Each time, you may choose a ball in your hand, and insert it into the row (including the leftmost place and rightmost place). Then, if there is a group of 3 or more balls in the same color touching, remove these balls. Keep doing this until no more balls can be removed.

 Find the minimal balls you have to insert to remove all the balls on the table. If you cannot remove all the balls, output -1.

 Examples:

 Input: "WRRBBW", "RB"
 Output: -1
 Explanation: WRRBBW -> WRR[R]BBW -> WBBW -> WBB[B]W -> WW

 Input: "WWRRBBWW", "WRBRW"
 Output: 2
 Explanation: WWRRBBWW -> WWRR[R]BBWW -> WWBBWW -> WWBB[B]WW -> WWWW -> empty

 Input:"G", "GGGGG"
 Output: 2
 Explanation: G -> G[G] -> GG[G] -> empty

 Input: "RBYYBBRRB", "YRBGB"
 Output: 3
 Explanation: RBYYBBRRB -> RBYY[Y]BBRRB -> RBBBRRB -> RRRB -> B -> B[B] -> BB[B] -> empty

 Note:
 You may assume that the initial row of balls on the table won’t have any 3 or more consecutive balls with the same color.
 The number of balls on the table won't exceed 20, and the string represents these balls is called "board" in the input.
 The number of balls in your hand won't exceed 5, and the string represents these balls is called "hand" in the input.
 Both input strings will be non-empty and only contain characters 'R','Y','B','G','W'.
 */
public class M_Leetcode_488 {
    /**
     * @param board
     * @param hand
     * @return
     *
     * dfs
     */
    public int findMinStep(String board, String hand) {
        int[] h = new int[26];
        for(int i = 0; i < hand.length(); i++){
            h[(int)(hand.charAt(i) - 'A')]++;
        }

        int[] res = new int[1];
        res[0] = Integer.MAX_VALUE;
        helper(res, 0, board, h);
        return (res[0] == Integer.MAX_VALUE ? -1 : res[0]);
    }

    private void helper(int[] res, int cur, String board, int[] hand){
        if(board.length() == 0){
            res[0] = Math.min(res[0], cur);
            return;
        }

        for(int i = 0; i < board.length(); i++){
            char c = board.charAt(i);
            int h = hand[(int)(c - 'A')];
            if(i < board.length() - 1 && board.charAt(i + 1) == c && h >= 1){
                String tmp = clean(board.substring(0, i) + board.substring(i + 2));
                hand[(int)(c - 'A')]--;
                helper(res, cur + 1, tmp, hand);
                hand[(int)(c - 'A')]++;
            }else if(h >= 2){
                String tmp = clean(board.substring(0, i) + board.substring(i + 1));
                hand[(int)(c - 'A')] -= 2;
                helper(res, cur + 2, tmp, hand);
                hand[(int)(c - 'A')] += 2;
            }
        }
    }

    private String clean(String a){
        int i = 0;
        while(i < a.length()){
            int j = i;
            while(j < a.length() && a.charAt(i) == a.charAt(j)) j++;
            if(j - i >= 3){
                return clean(a.substring(0, i) + a.substring(j));
            }
            i = j;
        }

        return a;
    }
}
