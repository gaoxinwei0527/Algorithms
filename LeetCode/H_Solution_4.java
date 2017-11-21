package LeetCode;

/**
 4. Median of Two Sorted Arrays

 There are two sorted arrays nums1 and nums2 of size m and n respectively.

 Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

 Example 1:
 nums1 = [1, 3]
 nums2 = [2]

 The median is 2.0
 Example 2:
 nums1 = [1, 2]
 nums2 = [3, 4]

 The median is (2 + 3)/2 = 2.5

 */
public class H_Solution_4 {
    /**
     * @param nums1
     * @param nums2
     * @return
     *
     Same as find kth num in two sorted arrays. Everytime, exclude k / 2 (or the whole shorter array, whichever has the shorter length) elements.
     Recursive end - k == 1 or one of the array is all excluded.

     Tip: always keep the shorter array in the first place in helper function. Then we can decide should exclude k / 2 or short array's length easily.
     Time - O(log(m + n))
     Space - O(1)
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if((m + n) % 2 == 1){
            return findKthElement(nums1, 0, m-1, nums2, 0, n-1, (m + n) / 2 + 1);
        }else{
            return (findKthElement(nums1, 0, m-1, nums2, 0, n-1, (m + n) / 2) + findKthElement(nums1, 0, m-1, nums2, 0, n-1, (m + n) / 2 + 1)) / 2.0;
        }
    }

    private double findKthElement(int[] nums1, int i, int j, int[] nums2, int a, int b, int k){
        if(j - i > b - a){
            return findKthElement(nums2, a, b, nums1, i, j, k);
        }

        if(i > j) return (double) nums2[a + k - 1];
        if(k == 1) return (double) (nums1[i] > nums2[a] ? nums2[a] : nums1[i]);

        int l1 = ((k / 2 > j - i + 1) ? j - i + 1 : k / 2);
        int l2 = k - l1;
        if(nums1[i + l1 - 1] > nums2[a + l2 - 1]){
            return findKthElement(nums1, i, j, nums2, a + l2, b, k - l2);
        }else{
            return findKthElement(nums1, i + l1, j, nums2, a, b, k - l1);
        }
    }
}
