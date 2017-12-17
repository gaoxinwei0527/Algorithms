package LeetCode;

/**
 302. Smallest Rectangle Enclosing Black Pixels

 An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

 For example, given the following image:

 [
 "0010",
 "0110",
 "0100"
 ]
 and x = 0, y = 2,
 Return 6.
 */
public class H_Leetcode_302 {
    /**
     * @param image
     * @param x
     * @param y
     * @return
     *
     * Binary search-
    find upper bound from (0 - x);
    find lower bound from (x - image.length-1);
    find left bound from (0 - y);
    find right bound from (y - image[0].length - 1);

     Time - O(mlogn + nlogm)
     Space - O(1)
     */
    public int minArea(char[][] image, int x, int y) {
        int m = image.length;
        int n = image[0].length;

        int l = 0;
        int r = y;
        while(l <= r){
            int mid = (l + r) >>> 1;
            boolean found = false;
            for(int i = 0; i < m; i++){
                if(image[i][mid] == '1'){
                    found = true;
                    break;
                }
            }

            if(found){
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        int left = l;

        l = y;
        r = n - 1;
        while(l <= r){
            int mid = (l + r) >>> 1;
            boolean found = false;
            for(int i = 0; i < m; i++){
                if(image[i][mid] == '1'){
                    found = true;
                    break;
                }
            }

            if(found){
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }
        int right = l - 1;

        int low = 0;
        int high = x;
        while(low <= high){
            int mid = (low + high) >>> 1;
            boolean found = false;
            for(int i = 0; i < n; i++){
                if(image[mid][i] == '1'){
                    found = true;
                    break;
                }
            }

            if(found){
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        int up = high + 1;

        low = x;
        high = m - 1;
        while(low <= high){
            int mid = (low + high) >>> 1;
            boolean found = false;
            for(int i = 0; i < n; i++){
                if(image[mid][i] == '1'){
                    found = true;
                    break;
                }
            }

            if(found){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        int bot = high;

        return (right - left + 1) * (bot - up + 1);
    }
}
