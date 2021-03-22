## CountFactors - Count factors of given number n.
A positive integer D is a factor of a positive integer N if there exists an integer M such that N = D * M.

For example, 6 is a factor of 24, because M = 4 satisfies the above condition (24 = 6 * 4).

Write a function:

class Solution { public int solution(int N); }

that, given a positive integer N, returns the number of its factors.

For example, given N = 24, the function should return 8, because 24 has 8 factors, namely 1, 2, 3, 4, 6, 8, 12, 24. There are no other factors of 24.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [1..2,147,483,647].
Copyright 2009–2021 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.

### 해답
~~~
class Solution {
    public int solution(int N) {
        int cnt=0;
        int sqrt = (int)Math.sqrt(N);
        for(int i=1;i<=sqrt;i++) {
            if(N%i == 0) {
                cnt++;
            }
        }

        cnt*=2;        
        if(sqrt * sqrt == N){ 
            cnt--; 
        }

        return cnt;
    }
}
~~~

### 설명
- 약수의 갯수를 구하는 중요한 사실이 있다. "만약 √(N)이 정수라면 √(N)은 N의 약수이며, 나머지 약수들은 √(N)를 기준으로 앞 뒤로 짝을 이루고 있다."는 것이다.
    - 100의 약수는 1, 2, 4, 5, 10, 20, 25, 50, 100이다. 
    - √(100)은 10으로 정수이다.
    - 즉 100의 약수는 10을 기준으로 앞 뒤로 짝을 이루고 있다는 것이다. 
    - (1, 100), (2, 50), (4, 25), (5, 20)으로 4개의 짝을 이루고 있으므로
    - 2 * 4 + 1 = 9개가 된다.

#### 출처
- https://myung6024.tistory.com/66
- https://lipcoder.tistory.com/m/entry/CountFactors-Codility?category=843241