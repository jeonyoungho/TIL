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