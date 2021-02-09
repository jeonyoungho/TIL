## FrogRiverOne - Find the earliest time when a frog can jump to the other side of a river.
~~~
import java.util.*;
class Solution {
    public int solution(int X, int[] A) {
        
        int[] tmp = new int[X];
        int cnt=0;

        for(int i=0;i<A.length;i++) {

            if(tmp[A[i]-1] == 0) {
                tmp[A[i]-1] = A[i];
                ++cnt;
            }

            if(cnt == X) return i;
        }

        return -1; 
    }
}
~~~

## MaxCounters - Calculate the values of counters after applying all alternating operations: increase counter by 1; set value of all counters to current maximum.
~~~
class Solution {
    public int[] solution(int N, int[] A) {
        int currentMax = 0;
        int lastCalledMax = 0;
        int[] counters = new int[N];

        for (int i = 0; i < A.length; i++) {
            if (A[i] == N + 1) {
                lastCalledMax = currentMax;
            } else {
                int counter = A[i] - 1;
                if (counters[counter] < lastCalledMax) {
                    counters[counter] = lastCalledMax + 1;
                } else {
                    counters[counter]++;
                }

                if (counters[counter] > currentMax) {
                    currentMax = counters[counter];
                }
            }
        }

        for (int i = 0; i < N; i++) {
            if (counters[i] < lastCalledMax) {
                counters[i] = lastCalledMax;
            }
        }

        return counters;
    }
}
~~~
#### 출처 
- https://cheolhojung.github.io/posts/algorithm/Codility-MaxCounters.html

