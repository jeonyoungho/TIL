## CountDiv - Compute number of integers divisible by k in range [a..b].
~~~
class Solution {
    public int solution(int A, int B, int K) {
        if(A%K==0) return B/K - A/K + 1;
        
        return B/K - A/K;
    }
}
~~~

## GenomicRangeQuery - Find the minimal nucleotide from a range of sequence DNA.
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

- a < b < c < d 일 때 a < (a+b)/2 < b 그리고 c < (c+d)/2 < d 이고 (a+b)/2 < (c+d)/2 이기에 결과적으로 (a+b)/2 < (a+b+c+d)/4 < (c+d)/2
- 3개인 경우도 생각해줘야하는게 (2,8,2)일 경우를 생각 했을 때, (2,8) = 5, (2,8,2) = 4 이므로 3개인 경우가 더 작아질 경우를 고려해야함
- 즉, 크기가 4 이상인 부분집합의 평균들은 크기가 2,3인 부분집합의 평균보다 클 수가 없기에 2, 3인 슬라이스만 비교해주면 됨

#### 출처 
- https://m.blog.naver.com/PostView.nhn?blogId=alwlren_00&logNo=221603639510&proxyReferer=https:%2F%2Fwww.google.com%2F
- https://cheolhojung.github.io/posts/algorithm/Codility-MinAvgTwoSlice.html

## PassingCars - Count the number of passing cars on the road.
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
- Loop를 돌면서 1이 나올때마다 그 전까지 0이 나왔던 인덱스의 개수를 더해줌으로써 문제를 해결 할 수 있음

