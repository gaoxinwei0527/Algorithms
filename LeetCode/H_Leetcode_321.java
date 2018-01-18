package LeetCode;

/**
 321. Create Maximum Number

 Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return an array of the k digits. You should try to optimize your time and space complexity.

 Example 1:
 nums1 = [3, 4, 6, 5]
 nums2 = [9, 1, 2, 5, 8, 3]
 k = 5
 return [9, 8, 6, 5, 3]

 Example 2:
 nums1 = [6, 7]
 nums2 = [6, 0, 4]
 k = 5
 return [6, 7, 6, 0, 4]

 Example 3:
 nums1 = [3, 9]
 nums2 = [8, 9]
 k = 3
 return [9, 8, 9]
 */
public class H_Leetcode_321 {
    /**
     * @param nums1
     * @param nums2
     * @param k
     * @return
     *
     * try all combinations of i & k-i picks from nums1 & nums2, and merge them and compare with current result.
     * need two helpers-
     * 1. pick max subsequence with k digits from nums using stack, similar to '316 Remove Duplicate Letters'
     * 2. merge two array to max merged array, trick is, when two nums are the same, pick the num with larger following nums.
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        if(nums1.length < nums2.length){
            int[] tmp = nums1;
            nums1 = nums2;
            nums2 = tmp;
        }

        int[] res = new int[k];
        for(int i = 0; i <= k && i <= nums1.length; i++){
            int j = k - i;
            if(j <= nums2.length){
                int[] p1 = pick(nums1, i);
                int[] p2 = pick(nums2, j);
                int[] merged = merge(p1, p2);
                if(compare(merged, 0, res, 0)) res = merged;
            }
        }

        return res;
    }

    private int[] pick(int[] num, int k){
        int[] res = new int[k];
        int len = 0;
        for(int i = 0; i < num.length; i++){
            // all remaining nums should be included
            if(k - len == num.length - i){
                res[len++] = num[i];
                continue;
            }

            while(len > 0 && res[len - 1] < num[i] && (k - len + 1) <= num.length - i) len--;
            if(len < k){
                res[len++] = num[i];
            }
        }

        return res;
    }

    private int[] merge(int[] a, int[] b){
        int[] res = new int[a.length + b.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while(i < a.length && j < b.length){
            if(compare(a, i, b, j)){
                res[k++] = a[i++];
            }else{
                res[k++] = b[j++];
            }
        }

        while(i < a.length) res[k++] = a[i++];
        while(j < b.length) res[k++] = b[j++];
        return res;
    }

    private boolean compare(int[] a, int i, int[] b, int j){
        while(i < a.length && j < b.length){
            if(a[i] > b[j]) return true;
            else if(a[i] < b[j]) return false;
            i++;
            j++;
        }
        return (i < a.length);
    }
}
