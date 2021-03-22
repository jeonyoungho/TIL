## CountDiv - Compute number of integers divisible by k in range [a..b].
Write a function:

class Solution { public int solution(int A, int B, int K); }

that, given three integers A, B and K, returns the number of integers within the range [A..B] that are divisible by K, i.e.:

{ i : A ≤ i ≤ B, i mod K = 0 }

For example, for A = 6, B = 11 and K = 2, your function should return 3, because there are three numbers divisible by 2 within the range [6..11], namely 6, 8 and 10.

Write an efficient algorithm for the following assumptions:

A and B are integers within the range [0..2,000,000,000];
K is an integer within the range [1..2,000,000,000];
A ≤ B.

### 해답
~~~
class Solution {
    public int solution(int A, int B, int K) {
        if(A%K==0) return B/K - A/K + 1;
        
        return B/K - A/K;
    }
}
~~~

## GenomicRangeQuery - Find the minimal nucleotide from a range of sequence DNA.
A DNA sequence can be represented as a string consisting of the letters A, C, G and T, which correspond to the types of successive nucleotides in the sequence. Each nucleotide has an impact factor, which is an integer. Nucleotides of types A, C, G and T have impact factors of 1, 2, 3 and 4, respectively. You are going to answer several queries of the form: What is the minimal impact factor of nucleotides contained in a particular part of the given DNA sequence?

The DNA sequence is given as a non-empty string S = S[0]S[1]...S[N-1] consisting of N characters. There are M queries, which are given in non-empty arrays P and Q, each consisting of M integers. The K-th query (0 ≤ K < M) requires you to find the minimal impact factor of nucleotides contained in the DNA sequence between positions P[K] and Q[K] (inclusive).

For example, consider string S = CAGCCTA and arrays P, Q such that:

    P[0] = 2    Q[0] = 4
    P[1] = 5    Q[1] = 5
    P[2] = 0    Q[2] = 6
The answers to these M = 3 queries are as follows:

The part of the DNA between positions 2 and 4 contains nucleotides G and C (twice), whose impact factors are 3 and 2 respectively, so the answer is 2.
The part between positions 5 and 5 contains a single nucleotide T, whose impact factor is 4, so the answer is 4.
The part between positions 0 and 6 (the whole string) contains all nucleotides, in particular nucleotide A whose impact factor is 1, so the answer is 1.
Write a function:

class Solution { public int[] solution(String S, int[] P, int[] Q); }

that, given a non-empty string S consisting of N characters and two non-empty arrays P and Q consisting of M integers, returns an array consisting of M integers specifying the consecutive answers to all queries.

Result array should be returned as an array of integers.

For example, given the string S = CAGCCTA and arrays P, Q such that:

    P[0] = 2    Q[0] = 4
    P[1] = 5    Q[1] = 5
    P[2] = 0    Q[2] = 6
the function should return the values [2, 4, 1], as explained above.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [1..100,000];
M is an integer within the range [1..50,000];
each element of arrays P, Q is an integer within the range [0..N − 1];
P[K] ≤ Q[K], where 0 ≤ K < M;
string S consists only of upper-case English letters A, C, G, T.

### 해답
~~~
class Solution {
    public int[] solution(String S, int[] P, int[] Q) {
        int[] A = new int[S.length()+1];
        int[] C = new int[S.length()+1];
        int[] G = new int[S.length()+1];
        
        char[] chars = S.toCharArray();
        for(int i=0;i<chars.length;i++) {
            switch(chars[i]) {
                case 'A':
                    A[i+1]++;
                    break;
                case 'C':
                    C[i+1]++;
                case 'G':
                    G[i+1]++;
                    break;
                default:
                    break;
            }

            A[i + 1] += A[i];
            C[i + 1] += C[i];
            G[i + 1] += G[i];
        }

        int[] result = new int[P.length];

        for(int i=0;i<result.length;i++) {
            int startIndex = P[i];
            int endIndex = Q[i];

            if(startIndex == endIndex) {
                char c = S.charAt(startIndex);

                switch(c) {
                    case 'A':
                        result[i] = 1;
                        break;
                    case 'C':
                        result[i] = 2;
                        break;
                    case 'G':
                        result[i] = 3;
                        break;
                    case 'T':
                        result[i] = 4;
                        break;
                }
            } else if (A[startIndex] != A[endIndex + 1]) {
                result[i] = 1;
            } else if (C[startIndex] != C[endIndex + 1]) {
                result[i] = 2;
            } else if (G[startIndex] != G[endIndex + 1]) {
                result[i] = 3;
            } else {
                result[i] = 4;
            }
        }

        return result;
    }
}
~~~

## MinAvgTwoSlice - Find the minimal average of any slice containing at least two elements.
A non-empty array A consisting of N integers is given. A pair of integers (P, Q), such that 0 ≤ P < Q < N, is called a slice of array A (notice that the slice contains at least two elements). The average of a slice (P, Q) is the sum of A[P] + A[P + 1] + ... + A[Q] divided by the length of the slice. To be precise, the average equals (A[P] + A[P + 1] + ... + A[Q]) / (Q − P + 1).

For example, array A such that:

    A[0] = 4
    A[1] = 2
    A[2] = 2
    A[3] = 5
    A[4] = 1
    A[5] = 5
    A[6] = 8
contains the following example slices:

slice (1, 2), whose average is (2 + 2) / 2 = 2;
slice (3, 4), whose average is (5 + 1) / 2 = 3;
slice (1, 4), whose average is (2 + 2 + 5 + 1) / 4 = 2.5.
The goal is to find the starting position of a slice whose average is minimal.

Write a function:

class Solution { public int solution(int[] A); }

that, given a non-empty array A consisting of N integers, returns the starting position of the slice with the minimal average. If there is more than one slice with a minimal average, you should return the smallest starting position of such a slice.

For example, given array A such that:

    A[0] = 4
    A[1] = 2
    A[2] = 2
    A[3] = 5
    A[4] = 1
    A[5] = 5
    A[6] = 8
the function should return 1, as explained above.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [2..100,000];
each element of array A is an integer within the range [−10,000..10,000].

### 해답
~~~
class Solution {
    public int solution(int[] A) {
        float minAvg = (A[0]+A[1])/2f;

        int result = 0;
        for(int i=2;i<A.length;i++) { 
            float avg = (A[i-1] + A[i])/2f;
            if (avg<minAvg) {
                minAvg = avg;
                result = i-1;
            }

            avg = (A[i-2] + A[i-1] + A[i])/3f;
            if (avg<minAvg) {
                minAvg = avg;
                result = i-2;
            }

        }

        return result;
    }
}
~~~

### 설명
- a < b < c < d 일 때 a < (a+b)/2 < b 그리고 c < (c+d)/2 < d 이고 (a+b)/2 < (c+d)/2 이기에 결과적으로 (a+b)/2 < (a+b+c+d)/4 < (c+d)/2
- 3개인 경우도 생각해줘야하는게 (2,8,2)일 경우를 생각 했을 때, (2,8) = 5, (2,8,2) = 4 이므로 3개인 경우가 더 작아질 경우를 고려해야함
- 즉, 크기가 4 이상인 부분집합의 평균들은 크기가 2,3인 부분집합의 평균보다 클 수가 없기에 2, 3인 슬라이스만 비교해주면 됨

#### 출처 
- https://m.blog.naver.com/PostView.nhn?blogId=alwlren_00&logNo=221603639510&proxyReferer=https:%2F%2Fwww.google.com%2F
- https://cheolhojung.github.io/posts/algorithm/Codility-MinAvgTwoSlice.html

## PassingCars - Count the number of passing cars on the road.
A non-empty array A consisting of N integers is given. The consecutive elements of array A represent consecutive cars on a road.

Array A contains only 0s and/or 1s:

0 represents a car traveling east,
1 represents a car traveling west.
The goal is to count passing cars. We say that a pair of cars (P, Q), where 0 ≤ P < Q < N, is passing when P is traveling to the east and Q is traveling to the west.

For example, consider array A such that:

  A[0] = 0
  A[1] = 1
  A[2] = 0
  A[3] = 1
  A[4] = 1
We have five pairs of passing cars: (0, 1), (0, 3), (0, 4), (2, 3), (2, 4).

Write a function:

class Solution { public int solution(int[] A); }

that, given a non-empty array A of N integers, returns the number of pairs of passing cars.

The function should return −1 if the number of pairs of passing cars exceeds 1,000,000,000.

For example, given:

  A[0] = 0
  A[1] = 1
  A[2] = 0
  A[3] = 1
  A[4] = 1
the function should return 5, as explained above.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [1..100,000];
each element of array A is an integer that can have one of the following values: 0, 1.

### 해답
~~~
class Solution {
    public int solution(int[] A) {
        int sum=0;
        int cnt=0;
        for(int i=0;i<A.length;i++) {
            if (A[i]==0) {
                cnt++;
            } else {
                sum += cnt;
                if(sum > 1000000000) return -1;
            }
        }

        return sum;
    }
}
~~~

### 설명
- Loop를 돌면서 1이 나올때마다 그 전까지 0이 나왔던 인덱스의 개수를 더해줌으로써 문제를 해결 할 수 있음

