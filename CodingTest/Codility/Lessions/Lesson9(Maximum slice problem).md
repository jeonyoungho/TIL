## MaxDoubleSliceSum - Find the maximal sum of any double slice.
A non-empty array A consisting of N integers is given.

A triplet (X, Y, Z), such that 0 ≤ X < Y < Z < N, is called a double slice.

The sum of double slice (X, Y, Z) is the total of A[X + 1] + A[X + 2] + ... + A[Y − 1] + A[Y + 1] + A[Y + 2] + ... + A[Z − 1].

For example, array A such that:

    A[0] = 3
    A[1] = 2
    A[2] = 6
    A[3] = -1
    A[4] = 4
    A[5] = 5
    A[6] = -1
    A[7] = 2
contains the following example double slices:

double slice (0, 3, 6), sum is 2 + 6 + 4 + 5 = 17,
double slice (0, 3, 7), sum is 2 + 6 + 4 + 5 − 1 = 16,
double slice (3, 4, 5), sum is 0.
The goal is to find the maximal sum of any double slice.

Write a function:

class Solution { public int solution(int[] A); }

that, given a non-empty array A consisting of N integers, returns the maximal sum of any double slice.

For example, given:

    A[0] = 3
    A[1] = 2
    A[2] = 6
    A[3] = -1
    A[4] = 4
    A[5] = 5
    A[6] = -1
    A[7] = 2
the function should return 17, because no double slice of array A has a sum of greater than 17.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [3..100,000];
each element of array A is an integer within the range [−10,000..10,000].
Copyright 2009–2021 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
### 해답
~~~
class Solution {

    class Info { 
        int sum_from_front = 0; 
        int sum_from_back = 0; 
    } 

    public int solution(int[] A) { 
        if (A == null || A.length == 3) { 
            return 0; 
        } 
        
        Info[] infos = new Info[A.length]; 
        for(int i = 0; i < infos.length; ++i) { 
            infos[i] = new Info(); 
        } 
        
        infos[1].sum_from_front = A[1]; 
        infos[A.length - 2].sum_from_back = A[A.length - 2]; 
        for (int i = 2; i < infos.length - 2; ++i) { 
            infos[i].sum_from_front = Math.max(A[i], 
                    infos[i - 1].sum_from_front + A[i]); 
            int backIndex = A.length - i - 1; 
            infos[backIndex].sum_from_back = Math.max(A[backIndex], 
                    infos[backIndex + 1].sum_from_back + A[backIndex]); 
            } 
            
            int maxSlice = 0; 
            for(int i = 0 ; i < A.length - 2; ++i) { 
                int partSumFromFront = Math.max(0, infos[i].sum_from_front); 
                int partSumFromBack = Math.max(0, infos[i + 2].sum_from_back);               
                int sum = partSumFromFront + partSumFromBack; 
                
                maxSlice = Math.max(maxSlice, sum); 
            } 
            
            return maxSlice; 
    } 
}
~~~
#### 출처
- https://lipcoder.tistory.com/entry/MaxDoubleSliceSum-Codility

## MaxProfit - Given a log of stock prices compute the maximum possible earning.
An array A consisting of N integers is given. It contains daily prices of a stock share for a period of N consecutive days. If a single share was bought on day P and sold on day Q, where 0 ≤ P ≤ Q < N, then the profit of such transaction is equal to A[Q] − A[P], provided that A[Q] ≥ A[P]. Otherwise, the transaction brings loss of A[P] − A[Q].

For example, consider the following array A consisting of six elements such that:

  A[0] = 23171
  A[1] = 21011
  A[2] = 21123
  A[3] = 21366
  A[4] = 21013
  A[5] = 21367
If a share was bought on day 0 and sold on day 2, a loss of 2048 would occur because A[2] − A[0] = 21123 − 23171 = −2048. If a share was bought on day 4 and sold on day 5, a profit of 354 would occur because A[5] − A[4] = 21367 − 21013 = 354. Maximum possible profit was 356. It would occur if a share was bought on day 1 and sold on day 5.

Write a function,

class Solution { public int solution(int[] A); }

that, given an array A consisting of N integers containing daily prices of a stock share for a period of N consecutive days, returns the maximum possible profit from one transaction during this period. The function should return 0 if it was impossible to gain any profit.

For example, given array A consisting of six elements such that:

  A[0] = 23171
  A[1] = 21011
  A[2] = 21123
  A[3] = 21366
  A[4] = 21013
  A[5] = 21367
the function should return 356, as explained above.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [0..400,000];
each element of array A is an integer within the range [0..200,000].
Copyright 2009–2021 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
~~~
class Solution {
    public int solution(int[] A) {
        int min = (A.length > 0) ? A[0] : 0; 
        int result = 0; 
        
        for(int num : A) { 
            if(num <= min) { 
                min = num; 
            } 
            
            if(result < num - min) { 
                result = num - min; 
            }
        } 
        
        return result;

    }
}
~~~

### 설명
- 루프를 한 번씩만 돌면서 최소값을 발견하면 갱신해주고 기존의 손익보다 큰지 비교해서 갱신해주면 되는 문제

#### 출처
- https://lipcoder.tistory.com/entry/MaxProfit-Codility

## MaxSliceSum - Find a maximum sum of a compact subsequence of array elements.
A non-empty array A consisting of N integers is given. A pair of integers (P, Q), such that 0 ≤ P ≤ Q < N, is called a slice of array A. The sum of a slice (P, Q) is the total of A[P] + A[P+1] + ... + A[Q].

Write a function:

class Solution { public int solution(int[] A); }

that, given an array A consisting of N integers, returns the maximum sum of any slice of A.

For example, given array A such that:

A[0] = 3  A[1] = 2  A[2] = -6
A[3] = 4  A[4] = 0
the function should return 5 because:

(3, 4) is a slice of A that has sum 4,
(2, 2) is a slice of A that has sum −6,
(0, 1) is a slice of A that has sum 5,
no other slice of A has sum greater than (0, 1).
Write an efficient algorithm for the following assumptions:

N is an integer within the range [1..1,000,000];
each element of array A is an integer within the range [−1,000,000..1,000,000];
the result will be an integer within the range [−2,147,483,648..2,147,483,647].
Copyright 2009–2021 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
~~~
class Solution {
    public int solution(int[] A) {
    if(A.length == 1) return A[0]; 
    
    int localMaxSum = A[0]; 
    int globalMaxSum = A[0]; 
    
    for(int i = 1 ; i < A.length; i++) { 
        localMaxSum = Math.max(A[i], localMaxSum + A[i]); 
        globalMaxSum = Math.max(globalMaxSum, localMaxSum);
    } 
    
    return globalMaxSum;
    }
}
~~~

### 설명
- 최대 합을 보관하는 변수를 두 개 만들고 하나는 result용 최종 최대 합을 보관하는 변수로 사용하고 하나는 임시 최대 합을 보관하는 용도로 사용하는게 문제 풀이의 핵심!

#### 출처
- https://lipcoder.tistory.com/entry/MaxProfit-Codility