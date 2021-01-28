## FrogJmp - Count minimal number of jumps from position X to Y.
~~~
class Solution {
    public int solution(int X, int Y, int D) {
        // write your code in Java SE 8
        int resultCnt = 0;
        int remail = Y - X;

        resultCnt += remail/D;
        resultCnt += remail%D > 0 ? 1 : 0;

        return resultCnt;
    }
}
~~~

## PermMissingElem - Find the missing element in a given permutation.
~~~
class Solution {
    public int solution(int[] A) {
        int maxElement = A.length + 1;
        int sum = 0;
        int totalAmt = 0;

        int j=0;
        while(j<=A.length+1) {
            sum += j;
            j++;
        }

        int i=0;
        while(i<A.length) {
            totalAmt += A[i];
            i++;
        }

        int result = sum - totalAmt;
        return result;
    }
}
~~~

## TapeEquilibrium - Minimize the value |(A[0] + ... + A[P-1]) - (A[P] + ... + A[N-1])|.
~~~
import java.util.*;
class Solution {
    public int solution(int[] A) {
        List<Integer> list = new ArrayList<Integer>();

        int sum = 0;
        for(int i=0; i<A.length;i++) {
            sum += A[i];
        }

        int left=0;
        int right=0;
        for(int i=1; i<A.length;i++) {
            left += A[i-1];
            right = sum - left;
            list.add(Math.abs(left-right));
        }

        return Collections.min(list);
    }
}
~~~