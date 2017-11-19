public class Solution_3 {  
    public boolean isNumber(String s) {  
        int transitionTable[][] = new int[][] {  
                { -1, 0, 3, 1, 2, -1 }, // next states for state 0  
                { -1, 8, -1, 1, 4, 5 }, // next states for state 1  
                { -1, -1, -1, 4, -1, -1 }, // next states for state 2  
                { -1, -1, -1, 1, 2, -1 }, // next states for state 3  
                { -1, 8, -1, 4, -1, 5 }, // next states for state 4  
                { -1, -1, 6, 7, -1, -1 }, // next states for state 5  
                { -1, -1, -1, 7, -1, -1 }, // next states for state 6  
                { -1, 8, -1, 7, -1, -1 }, // next states for state 7  
                { -1, 8, -1, -1, -1, -1 } // next states for state 8  
        };  
        int state = 0;  
        for (int i = 0; i < s.length(); ++i) {  
            int inputType = 0;  
            if (Character.isSpaceChar(s.charAt(i)))  
                inputType = 1;  
            else if (s.charAt(i) == '+' || s.charAt(i) == '-')  
                inputType = 2;  
            else if (Character.isDigit(s.charAt(i)))  
                inputType = 3;  
            else if (s.charAt(i) == '.')  
                inputType = 4;  
            else if (s.charAt(i) == 'e' || s.charAt(i) == 'E')  
                inputType = 5;  
            // Get next state from current state and input symbol  
            state = transitionTable[state][inputType];  
            // Invalid input  
            if (state == -1)  
                return false;  
        }  
        // If the current state belongs to one of the accepting (final) states,  
        // then the number is valid  
        return state == 1 || state == 4 || state == 7 || state == 8;  
    }  
}
