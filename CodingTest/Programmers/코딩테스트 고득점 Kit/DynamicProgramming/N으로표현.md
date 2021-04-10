# N으로표현
- https://programmers.co.kr/learn/courses/30/lessons/42895?language=java

## 풀이

## 제출 코드
~~~
import java.util.*;

class Solution {
    
    private HashSet<Integer>[] dp = new HashSet[9];
    
    public int solution(int N, int number) {
        
        if(N == number)
            return 1;
        
        String str = String.valueOf(N);
        
        for(int i=1;i<=8;i++) {
            dp[i] = new HashSet<Integer>();
            if(i == 1) {
                dp[i].add(Integer.valueOf(str));
                continue;
            }
                
            str += String.valueOf(N);
            dp[i].add(Integer.valueOf(str));

            for(int j=1;j<i;j++) {
                calc(j, i);

                if(dp[i].contains(number)) {
                     return i;
                }
            }
        }
        
        return -1;
    }
    
    // a개의 N과 b개의 이루어진 N으로 조합된 
    private void calc(int a, int b) {
        
        Iterator<Integer> beforeIt = dp[a].iterator();
        Iterator<Integer> afterIt = dp[b-a].iterator();
        
        while(beforeIt.hasNext()) {   
            int first = beforeIt.next();
            
            while(afterIt.hasNext()) {
                int second = afterIt.next();
                dp[b].add(first + second);
                dp[b].add(first - second);
                dp[b].add(first * second);
                if(second != 0 )
                    dp[b].add(first / second);
            }
            afterIt = dp[b-a].iterator();
        }
    }
}
~~~