## 문제 설명
- H-Index는 과학자의 생산성과 영향력을 나타내는 지표입니다. 어느 과학자의 H-Index를 나타내는 값인 h를 구하려고 합니다. 위키백과1에 따르면, H-Index는 다음과 같이 구합니다.

- 어떤 과학자가 발표한 논문 n편 중, h번 이상 인용된 논문이 h편 이상이고 나머지 논문이 h번 이하 인용되었다면 h의 최댓값이 이 과학자의 H-Index입니다.

- 어떤 과학자가 발표한 논문의 인용 횟수를 담은 배열 citations가 매개변수로 주어질 때, 이 과학자의 H-Index를 return 하도록 solution 함수를 작성해주세요.


#### 제한 사항
- 과학자가 발표한 논문의 수는 1편 이상 1,000편 이하입니다.
- 논문별 인용 횟수는 0회 이상 10,000회 이하입니다.

#### 입출력 예
<img width="186" alt="스크린샷 2021-04-05 오전 11 06 49" src="https://user-images.githubusercontent.com/44339530/113529680-05b6d780-95ff-11eb-934e-5d201fc65d6f.png">

#### 입출력 예 설명
- 이 과학자가 발표한 논문의 수는 5편이고, 그중 3편의 논문은 3회 이상 인용되었습니다. 그리고 나머지 2편의 논문은 3회 이하 인용되었기 때문에 이 과학자의 H-Index는 3입니다.

#### 제출 코드
~~~
import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = citations.length;
        
        Arrays.sort(citations);
        
        for(int i=citations.length-1;i>=0;i--) {
            
            int remain = citations.length-i;

            // 0 1 2 5 6 7                 
            if(citations[i] <= remain) {
                answer = citations[i] == remain ? citations[i] : remain - 1;
                break;
            }
        }
        
        return answer;
    }
}
~~~

#### 다른 사람의 코드
~~~
import java.util.*;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);

        int max = 0;
        for(int i = citations.length-1; i > -1; i--){
            int min = (int)Math.min(citations[i], citations.length - i);
            if(max < min) max = min;
        }

        return max;
    }
}
~~~

#### 출처
- https://programmers.co.kr/learn/courses/30/lessons/42747
