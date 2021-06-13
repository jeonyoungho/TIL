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
- 개념적인 이해를 돕기 위해 아래의 customer테이블과 buy테이블 있다고 예시를 들어보자<br>
<img width="720" alt="1" src="https://user-images.githubusercontent.com/44339530/121804534-cf8e7780-cc81-11eb-8b7d-cd7ffb7a6246.png"><br>
<img width="660" alt="스크린샷 2021-06-13 오후 8 10 44" src="https://user-images.githubusercontent.com/44339530/121804869-70c9fd80-cc83-11eb-98bf-615c6d7b6259.png"><br>

### Left Outer Join
- <b>Left Outer Join은 왼쪽 테이블의 것은 조건에 부합하지 않더라도 모두 결합</b>되어야 한다는 의미이다.
- 즉, From 첫번째 테이블 LEFT OUTER JOIN 두번째 테이블이라면, 첫번쨰 테이블의 것은 모두 출력되어야 한다.
~~~
-- 전체 회원의 구매기록을 살펴보자.
-- 단, 구매 기록이 없는 회원도 출력되어야 한다.
-- LEFT OUTER JOIN이므로, customer테이블은 모두 출력된다.

SELECT *
FROM "customer" c
	LEFT OUTER JOIN "buy" b 
	ON c.USER_ID = b.USER_ID 
ORDER BY c.USER_ID 
~~~

- INNER JOIN시 INNER 키워드를 생략 가능했던 것처럼, LEFT OUTER JOIN 역시 LEFT JOIN만으로 작성해도 무방하다.
- 위 쿼리의 실행 결과는 아래와 같다.(모든 customer테이블의 행이 출력되고 buy테이블과 매칭되는 값이 없는 행은 NULL로 표시된다)<br>
<img width="1092" alt="스크린샷 2021-06-13 오후 8 11 27" src="https://user-images.githubusercontent.com/44339530/121804891-935c1680-cc83-11eb-8bdd-a87468f89cbe.png"><br>

### Right Outer Join
- <b>RIGHT OUTER JOIN은 오른쪽 테이블의 것은 조건에 부합하지 않더라도 모두 결합</b>되어야 한다는 의미이다.
- 즉, FROM 첫번째 테이블 RIGHT OUTER JOIN 두번째 테이블이라면, 두번째 테이블의 것은 모두 출력되어야 한다.
- LEFT OUTER JOIN의 예제와 동일한 결과를 얻을 수 있도록 예제를 작성해보자.

~~~
-- 전체 회원의 구매기록을 살펴보자.
-- 단, 구매 기록이 없는 회원도 출력되어야 한다.
 
-- RIGHT OUTER JOIN이므로, cusotmer 모두 출력된다

SELECT b.*, c.*
FROM "buy" b
	RIGHT OUTER JOIN "customer" c
	ON c.USER_ID = b.USER_ID 
ORDER BY c.USER_ID
~~~

- 역시 RIGHT OUTER JOIN은 RIGHT JOIN만으로도 작성이 가능하다.
- 실행 결과는 아래와 같으며 customer테이블의 모든 행이 출력된 것을 볼 수 있다. user5는 구매한 상품이 없기에 NULL로 표시된 것을 확인할 수 있다.<br>
<img width="1101" alt="스크린샷 2021-06-13 오후 8 18 00" src="https://user-images.githubusercontent.com/44339530/121805077-7f64e480-cc84-11eb-9c59-fc57db5b4878.png">

### FULL OUTER JOIN
- 전체 조인 또는 전체 외부 조인이라고 한다.
- FULL OUTER JOIN은 LEFT OUTER JOIN과 RIGHT OUTER JOIN을 합친 것이라고 생각하면 된다.
- 즉, 한쪽을 기준으로 조건과 일치하지 않는 것을 출력하는 것이 아니라, 양쪽 모두에 조건이 일치하지 않는 것들까지 모두 결합하는 개념이다.
- 따라서, 테이블들의 모든 행이 조건에 관계없이 결합된다.

~~~
SELECT b.*, c.*
FROM "buy" b
	FULL OUTER JOIN "customer" c
	ON c.USER_ID = b.USER_ID 
ORDER BY c.USER_ID 
~~~

- 실행 결과는 아래의 그림처럼 customer테이블과 user테이블을 모두 결합시키되 매칭되는게 없으면 NULl로 표시된다.<br>
<img width="1037" alt="스크린샷 2021-06-13 오후 8 20 29" src="https://user-images.githubusercontent.com/44339530/121805161-e5ea0280-cc84-11eb-9b90-fa1d52de3838.png"><br>

### 세 개 이상의 테이블 조인
- 아래의 그림처럼 delivery테이블이 추가되었다고 가정해보자.<br>
<img width="627" alt="스크린샷 2021-06-13 오후 8 28 38" src="https://user-images.githubusercontent.com/44339530/121805354-f0f16280-cc85-11eb-97ef-a02b4a894f9d.png"><br>

- 만약 customer, buy, delivery테이블을 모두 조인하여 모든 유저의 구매현황과 배송 현황을 조회하고자 할때 쿼리는 아래와 같다.
~~~
SELECT *
FROM "customer" c
	LEFT OUTER JOIN "buy" b 
	ON c.USER_ID = b.USER_ID
		LEFT OUTER JOIN "delivery" d
		ON b.BUY_ID = d.BUY_ID 
ORDER BY c.USER_ID 
~~~

- 실행 결과는 아래와 같다. customer테이블과 buy테이블을 Left Outer Join한 결과에 또 한 Left Outer Join을 적용하게 된다.<br>
<img width="1270" alt="스크린샷 2021-06-13 오후 8 32 58" src="https://user-images.githubusercontent.com/44339530/121805535-a6241a80-cc86-11eb-842e-996ee904eb67.png"><br>

#### 출처
- http://sweeper.egloos.com/3002220