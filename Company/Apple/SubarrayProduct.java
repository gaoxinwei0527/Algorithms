package Company.Apple;

import com.sun.tools.javac.util.Assert;

public class SubarrayProduct {
    /*
    Given an array A of positive integers, for each query Q(x, y) return the product of all integers in A between indices x and y inclusively.
    */
    private static class Solution{
        int[] product;
        public Solution(int[] arr){
            product = new int[arr.length + 1];
            product[0] = 1;
            for(int i = 1; i <= arr.length; i++){
                product[i] = product[i - 1] * arr[i - 1];
            }
        }

        public int product(int i, int j){
            return product[j + 1] / product[i];
        }
    }

    /*
    What if the number in array is non-negative, means there are 0s in array
    */
    private static class Solution2{
        int[] product;
        int[] zero;
        public Solution2(int[] arr){
            int len = arr.length;
            product = new int[len + 1];
            product[0] = 1;
            zero = new int[len + 1];
            for(int i = 1; i <= len; i++){
                if(arr[i - 1] == 0){
                    zero[i] = i;
                    product[i] = 1;
                }else{
                    zero[i] = zero[i - 1];
                    product[i] = product[i - 1] * arr[i - 1];
                }
            }
        }

        public int product2(int i, int j){
            if(zero[j + 1] > i) return 0;
            return product[j + 1] / product[i];
        }
    }

    public static void main(String[] args){
        int[] arr1 = new int[]{1,2,3,4,5};
        int[] arr2 = new int[]{1,0,2,3,0,4,5};

        Solution solution = new Solution(arr1);
        Assert.check(solution.product(2, 4) == 60);

        Solution2 solution2 = new Solution2(arr2);
        Assert.check(solution2.product2(0, 2) == 0);
        Assert.check(solution2.product2(2, 3) == 6);
        Assert.check(solution2.product2(5, 6) == 20);
    }
}
