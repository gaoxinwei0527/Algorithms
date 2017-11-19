/*
Insert from end of Array A.
Complexity: O(m+n)
*/

public class Solution_1 {
    public void merge(int A[], int m, int B[], int n) {
        int i1=m-1,i2=n-1,i3=m+n-1;
        while(i1>=0 && i2>=0 && i3>=0){
            if(A[i1]>=B[i2]){
                A[i3--]=A[i1--];
            }else{
                A[i3--]=B[i2--];
            }
        }
        while(i2>=0){
            A[i3--]=B[i2--];
        }
    }
}
