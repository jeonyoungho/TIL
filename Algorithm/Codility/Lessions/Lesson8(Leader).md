## Dominator - Find an index of an array such that its value occurs at more than half of indices in the array.

An array A consisting of N integers is given. The dominator of array A is the value that occurs in more than half of the elements of A.

For example, consider array A such that

 A[0] = 3    A[1] = 4    A[2] =  3
 A[3] = 2    A[4] = 3    A[5] = -1
 A[6] = 3    A[7] = 3
The dominator of A is 3 because it occurs in 5 out of 8 elements of A (namely in those with indices 0, 2, 4, 6 and 7) and 5 is more than a half of 8.

Write a function

class Solution { public int solution(int[] A); }

that, given an array A consisting of N integers, returns index of any element of array A in which the dominator of A occurs. The function should return −1 if array A does not have a dominator.

For example, given array A such that

 A[0] = 3    A[1] = 4    A[2] =  3
 A[3] = 2    A[4] = 3    A[5] = -1
 A[6] = 3    A[7] = 3
the function may return 0, 2, 4, 6 or 7, as explained above.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [0..100,000];
each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
Copyright 2009–2021 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.

### 해답
~~~
import java.util.*;
class Solution {
    public int solution(int[] A) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i=0;i<A.length;i++) {
            int cnt = map.getOrDefault(A[i], 0) + 1;
            map.put(A[i], cnt);
        }

        int max=0;
        int maxKey=0;
        for(Integer key:map.keySet()) {
            int elementCnt = map.get(key);
            if(elementCnt > max) {
                max = elementCnt;
                maxKey = key;
            }
        }

        if(max <= A.length/2)
            return -1;

        for(int i=0; i<A.length; i++) {
            if(A[i] == maxKey) return i;
        }

        return -1;
    }
}
~~~
#### 출처 
- https://rooted.tistory.com/47

## EquiLeader - Find the index S such that the leaders of the sequences A[0], A[1], ..., A[S] and A[S + 1], A[S + 2], ..., A[N - 1] are the same.

A non-empty array A consisting of N integers is given.

The leader of this array is the value that occurs in more than half of the elements of A.

An equi leader is an index S such that 0 ≤ S < N − 1 and two sequences A[0], A[1], ..., A[S] and A[S + 1], A[S + 2], ..., A[N − 1] have leaders of the same value.

For example, given array A such that:

    A[0] = 4
    A[1] = 3
    A[2] = 4
    A[3] = 4
    A[4] = 4
    A[5] = 2
we can find two equi leaders:

0, because sequences: (4) and (3, 4, 4, 4, 2) have the same leader, whose value is 4.
2, because sequences: (4, 3, 4) and (4, 4, 2) have the same leader, whose value is 4.
The goal is to count the number of equi leaders.

Write a function:

class Solution { public int solution(int[] A); }

that, given a non-empty array A consisting of N integers, returns the number of equi leaders.

For example, given:

    A[0] = 4
    A[1] = 3
    A[2] = 4
    A[3] = 4
    A[4] = 4
    A[5] = 2
the function should return 2, as explained above.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [1..100,000];
each element of array A is an integer within the range [−1,000,000,000..1,000,000,000].
Copyright 2009–2021 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.

### 해답
~~~
import java.util.*;
class Solution {
    public int solution(int[] A) {
        int result=0;
        int leader=0, leaderCount=0;
        // 숫자 빈도 카운트 값 기록을 위해 해시맵 컨테이너를 선언한다.
        HashMap<Integer, Integer> map = new HashMap<>();
        // 카운트 값을 기록한다
        for(int i=0;i<A.length;i++) {
            int cnt = map.getOrDefault(A[i], 0) + 1;
            map.put(A[i], cnt);

            // 가장 많이 나온 수 (Leader)를 기록한다.
            if(leaderCount<cnt) {
                leaderCount = cnt;
                leader = A[i];
            }
        }

        // Leader 숫자를 구했으니 Leader 숫자가 각 인덱스에서 몇 개 정도 나왔는지 기록할 벡터 컨테이너를 만든다.
        Vector<Integer> record = new Vector<Integer>();
        int currentCount = 0;
        for(int i=0;i<A.length;i++) {
            if(A[i] == leader) {
                currentCount++;
            }
            record.add(currentCount);
        }

        for(int i=0;i<A.length-1;i++) {
            int leftCount = record.elementAt(i); // 둘로 쪼갰을때 왼쪽 부분 Leader 빈도수
            int rightCount = record.lastElement() - leftCount; // 오른쪽 부분 Leader 빈도수

            int limitEquiLeft = ((i + 1) / 2) + 1; 
            int limitEquiRight = ((A.length - (i + 1)) / 2) + 1; 
            
            // EquiLeader 조건을 왼쪽과 오른쪽 부분 모두 만족한다면 갯수를 증가 시킨다. 
            if((leftCount >= limitEquiLeft) && (rightCount >= limitEquiRight)) {
                 ++result; 
            }
        }

        return result;
    }
}
~~~
#### 출처
- https://lipcoder.tistory.com/entry/EquiLeader-Codility