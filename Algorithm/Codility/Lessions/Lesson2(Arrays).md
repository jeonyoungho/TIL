## CyclickRotation - Rotate an array to the right by a given number of steps.
~~~
class Solution {
    public int[] solution(int[] A, int K) {
        
        if(A.length > 0) {
           for(int i=0; i<K; i++){
                int tmp = A[A.length-1];
                for(int j=A.length-1;j>0;j--) {
                    A[j] = A[j-1];      
                }
                A[0] = tmp;
            }
        }       
    
        return A;
    }
}
~~~

## OddOccurrencesInArray - Find value that occurs in odd number of elements.
~~~
import java.util.*;

class Solution {
    public int solution(int[] A) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i=0; i<A.length; i++) {
            if(map.containsKey(A[i])){
                int cnt = map.get(A[i]);
                cnt++;
                map.put(A[i], cnt);
            } else {
                map.put(A[i], 1);
            }
        }

        int result = 0;
        for(Integer key:map.keySet()) {
            if(map.get(key)%2 == 1) {
                result = key;
            }
        }
        return result;
    }
}
~~~