package search;

/**
 * Find the median of the two sorted arrays.
 *
 * e.g.
 * nums1 = [1, 3]
 * nums2 = [2]
 * The median is 2.0
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * The median is (2 + 3)/2 = 2.5
 */
public class SolutionFindMedianSortedArrays {
    private static double findMedianSortedArrays(int[] A, int[] B) {
        int lenA, lenB;
        if (A == null && B == null) {
            return 0d;
        }
        if (A == null)
            lenA =0;
        else
            lenA = A.length;
        if (B == null)
            lenB = 0;
        else lenB = B.length;
        int len = lenA + lenB;
        if (len % 2 == 0)
            return (findK(A,0,B,0,len/2) + findK(A,0,B,0,len/2+1)) / 2; // even , sum of mid and mid +1 devided by 2

        return findK(A,0,B,0,len/2 + 1); // odd , sort a and b , select middle one

    }

    public static double findK(int A[], int startA, int B[], int startB, int k){
        if (startA >= A.length)
            return B[startB+k-1];
        if (startB >= B.length)
            return A[startA+k-1];
        if (k==1)
            return Math.min(A[startA],B[startB]);
        // we just discard small key, if out of bound we just set it as max value . wont' affect result
        int keyA = (startA + k/2 -1) < A.length?A[startA + k/2 -1]:Integer.MAX_VALUE;
        int keyB = (startB + k/2 -1) < B.length?B[startB + k/2 -1]:Integer.MAX_VALUE;
        if (keyA < keyB)
            // remember k also become k - k/2 whatever happend
            return findK(A,startA+k/2,B,startB,k-k/2);
        else
            return findK(A,startA,B,startB+k/2, k- k/2);

    }

    public static void main(String[] args) {
        // corner case k == 1
        // ArrayIndexOutOfBounds
        int[] A = {1, 3};
        int[] B = {2};
        double median = findMedianSortedArrays(A, B);
        System.out.println(median);

        //corner case aMid = Integer.MAX_VALUE;
        //expect 2.5
        int[] C = {1,2};
        int[] D = {3, 4};
        median = findMedianSortedArrays(C, D);
        System.out.println(median);
    }

}
