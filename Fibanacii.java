/*
There is one kind of numbers call Fibonacci number (forgot the specific name), which satisfy the following situation:
A. can be spilt into several numbers;
B. The first two number are the same, the next number is equal to the sum of previous two
eg. 112 (2 = 1 + 1), 12,122,436(12 + 12 = 24, 12 + 24 = 36)
If you are given a range by the user, print all numbers that are Fibonacci number in the range.

Complexity: O(m*n)
*/

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public List<Integer> Fibonacci(int start, int end) {
        ArrayList<Integer> res=new ArrayList<Integer>();
        for(int i=start;i<=end;i++){
        	if(isFibonacci(Integer.toString(i))) res.add(i);
        }
        return res;
    }
	
	private boolean isFibonacci(String num){
		for(int i=1;i<=num.length()/2;i++){
			String first=num.substring(0,i);
			String sub=num.substring(i);
			if(!sub.startsWith(first)){
				continue;
			}else{
				sub=sub.substring(first.length());
				int l=Integer.parseInt(first);
				int r=Integer.parseInt(first);
				while(sub.length()>0 && sub.startsWith(Integer.toString(l+r))){
					sub=sub.substring(Integer.toString((l+r)).length());
					int temp=l+r;
					l=r;
					r=temp;
				}
				if(sub.length()==0) return true;
			}
		}
		return false;
	}
    
    public static void main(String[] args){
    	List<Integer> res=new Solution().Fibonacci(1,22222222);
    	for(int i:res){
    		System.out.println(i);
    	}
    }
}
