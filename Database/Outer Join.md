# Outer Join
- Inner Join이 Join조건에 부합하는 행만 Join이 발생하는 것이라면, Outer Join은 조건에 부합하지 않는 행까지도 포함시켜 결합하는 것을 의미한다.(합집합의 영역)
- 자주는 아니지만, 가끔 유용하게 사용될 수 있으므로 꼭 알아둘 필요는 있다.

### Outer Join 구조
~~~
SELECT <열 목록>
FROM <첫번째 테이블 (LEFT 테이블)>
    <LEFT | RIGHT | FULL> OUTER JOIN <두번째 테이블 (RIGHT 테이블)>
                          ON <조인될 조건>
[WHERE 검색 조건]
~~~
- Inner Join과 유사해보이지만 Left, Right, Full의 새로운 키워드들이 보인다.

### Left Outer Join


#### 출처
- http://sweeper.egloos.com/3002220