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