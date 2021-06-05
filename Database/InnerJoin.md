# Inner Join
- 대개의 업무에서 조인은 Inner Join을 주로 사용하며, 일반적으로 Join이라 하면, Innter Join을 지칭하는 것이다.

- 개념적인 이해를 돕기 위해 아래의 UserTable과 BuyTable이 있다고 예시를 들어보자<br>
![1](https://user-images.githubusercontent.com/44339530/120778568-6ca22f80-c561-11eb-9ea0-347a8cbbe496.jpeg)<br>

- BuyTable을 보면, 물건을 구매한 사용자의 아이디와 물건 등의 정보만 나타난다.
- 그런데 이 물건을 배송하기 위해서는 구매한 회원의 주소를 알아야한다.
- 이 회원의 주소를 알기 위해 주소 정보가 있는 UserTable과 결합하는 조인이 Inner Join이다.
~~~
SELECT <열 목록>
FROM <첫 번째 테이블>
    INNER JOIN <두 번째 테이블>
    ON <조인될 조건>
[WHERE 검색조건]
~~~

- 참고로, 위 형식에서 INNER JOIN을 JOIN이라고만 해도 INNER JOIN이라고 인식한다.
- 이제 BuyTable에서 'KJD'이라는 아이디를 가진 사람이 구매한 물건을 배송하기 위해서, 두 개의 테이블을 통째로 조인하려면 아래와 같이 하면 된다.
~~~
SELECT *
FROM BuyTable                         -- 첫번째 테이블
        INNER JOIN UserTable          -- 두번째 테이블
        ON BuyTable.ID = UserTable.ID -- JOIN 조건
WHERE BuyTable.ID = 'KJD'
~~~
- 위의 쿼리를 실행하면 결과는 아래의 사진과 같다<br>
![2](https://user-images.githubusercontent.com/44339530/120779297-e3d7c380-c561-11eb-8d01-66fb45ba3459.png)<br>

- <b>UserTable과 BuyTable은 모두 ID 컬럼이 존재하기에 반드시 테이블명.컬럼명 형태로 작성해야 한다.(별칭도 가능) 그렇지 않을 경우 오류가 발생한다.</b>

- 위의 결과를 생성하기 위해서 아래와 같은 과정을 거친다.
~~~
1. BuyTable의 'KJD' ID를 추출해낸다.

2. 'KJD'와 동일한 값을 UserTable에서 검색한다.

3.'KJD'라는 ID를 찾으면, BuyTable과 UserTable의 모든 칼럼을 결합(Join)한다.

* 만약, 위 예제에서 where문이 빠져 있다면, 모든 구매기록에 대해 BuyTable + UserTable의 결합이 발생하게 된다.
~~~

- <b>위의 예시를 토대로 INNER JOIN은 조인될 조건이 부합하는 행에 대해서만 Join이 발생하는 것임을 알 수 있다.<b>

#### 출처
- http://egloos.zum.com/sweeper/v/3002133
- https://dimdim.tistory.com/entry/SQL-JOIN-%EC%A0%95%EB%A6%AC-Inner-Join-Outer-Join