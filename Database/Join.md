# Join

### Join(조인)이란?
- 여러 테이블에 흩어져 있는 정보 중 사용자가 필요한 정보만 가져와 가상의 테이블을 만들어 보여주는 것으로 2개의 테이블을 조합하여 하나의 열로 표현하는 것이다.

### Join의 종류
- 1)INNER JOIN
- 2)CROSS JOIN
- 3)OUTER JOIN
- 4)SELF JOIN

### 1. INNER JOIN
- INNER JOIN은 키 값이 있는 테이블의 컬럼 값을 비교 후 조건에 맞는 값을 가져오는 것이다. 즉, 서로 연관된 내용만 검색하는 조인 방법이다.<br>
![1](https://user-images.githubusercontent.com/44339530/120775030-cd2f6d80-c55d-11eb-8df5-58c43efa0eb7.png)<br>

#### 사용법
- SQL은 명시적 조인 표현과 암시적 조인 표현으로 구분을 지정
- <b>1)명시적 조인 표현</b>
    - 명시적 조인표현에서는 테이블에 조인을 하라는 것을 지정하기 위해 <b>'JOIN'</b>키워드를 사용하고 ON의 키워드를 조인에 대한 구문을 지저하는데 사용한다.
    ~~~
    SELECT *
    FROM EMPLOYEE INNER JOIN DEPARTMENT
    ON EMPLOYEE.DEPARTMENTID =DEPARTMENT.DEPARTMENT.DepartmentID;
    ~~~

- <b>2)암시적 조인 표현</b>
    - 암시적 조인표현은 SELCT 구문의 FROM절에서 콤마(,)를 사용하여 단순히 조인을 위한 여러 테이블을 나열하기만 하면 됨
    ~~~
    SELECT *
    FROM EMPLOYEE, DEPARTMENT
    WHERE EMPLOYEE.DepartmentID = DEPARTMENT.DepartmentID;
    ~~~
- Inner join결과<br>
![2](https://user-images.githubusercontent.com/44339530/120775647-5f377600-c55e-11eb-85fa-c7b036b0dfc9.jpeg)<br>

####  EQUI JOIN(동등 조인)
- EUQAL 연산자(=)를 사용해서 EQUI JOIN이라고 한다.
- WHERE 절에 기술되는 JOIN조건을 검사해서 양쪽 테이블에 같은 조건의 값이 존재할 경우 해당 데이터를 가져오는 조인 방법이다.
~~~
SELECT *
FROM EMPLOYEE, DEPARTMENT
WHERE EMPLOYEE.DepartmentID = DEPARTMENT.DepartmentID;

SELECT *
FROM EMPLOYEE JOIN DEPARTMENT
ON EMPLOYEE.DEPARTMENTID = DEPARTMENT.DEPARTMENTID;
~~~

### 2. CROSS JOIN(교차조인)
- CROSS JOIN은 Cartesian Product(카티션 곱)이라고도 하며 조인되는 두 테이블에서 곱집합을 반환한다.
- 즉, 두번째 테이블로부터 각 열과 첫번째 테이블에서 각 열이 한번씩 결합된 열을 만들 것이다. 예를 들어 m열을 가진 테이블과 n열을 가진 테이블이 교차 조인되면 m x n 개의 열을 생성한다. 그래서 테이블의 각 값을 연결하여 테이블 행의 수를 모두 곱한 값만큼 만들어진다.<br>
![3](https://user-images.githubusercontent.com/44339530/120776302-0d432000-c55f-11eb-9e95-e86381956c8a.jpeg)<br>

#### 사용법
~~~
SELECT *
FROM EMPLOYEE CROSS JOIN DEPARTMENT;
~~~

- employee 테이블의 튜플이 6개이고 department 테이블의 튜플이 4개면 6 x 4 = 24개의 결과가 나오게 된다.<br>
![4](https://user-images.githubusercontent.com/44339530/120776446-39f73780-c55f-11eb-87bc-747e10e2cadb.jpeg)<br>

### 3. OUTER JOIN
- Outer Join은 조인하는 여러 테이블에서 한 쪽에는 데이터가 있고 한 쪽에는 데이터가 없는 경우, 데이터가 있는 쪽 테이블의 내용을 전부 출력하는 방법이다. 즉, 조인 조건에 만족하지 않아도 해당 행을 출력하고 싶을 때 사용한다.
- Outer Join에는 LEFT OUTER JOIN, RIGHT OUTER JOIN, FULL OUTER JOIN이 있다.

#### 3-1. LEFT OUTER JOIN
- LEFT OUTER JOIN은 조인문의 왼쪽에 있는 테이블의 모든 결과를 가져온 후 오른쪽 테이블의 데이터를 매칭하고, 매칭되는 데이터가 없는 경우 NULL을 표시한다.<br>
![image](https://user-images.githubusercontent.com/44339530/120776868-a40fdc80-c55f-11eb-91da-f1f57748a45c.png)<br>
~~~
SELECT *
FROM EMPLOYEE E LEFT OUTER JOIN DEPARTMENT D
ON E.DEPARTMENTID = D.DEPARTMENTID;

<ORACLE>

SELECT *
FROM EMPLOYEE E, DEPARTMENT D
WHERE E.DepartmentID = D.DepartmentID(+);
~~~

#### 3-2. RIGHT OUTER JOIN
- RIGHT OUTER JOIN은 조인문의 오른쪽에 있는 테이블의 모든 결과를 가져온 후 왼쪽의 테이블의 데이터를 매칭하고, 매칭되는 데이터가 없는 경우 NULL을 표시한다.<br>
![6](https://user-images.githubusercontent.com/44339530/120777150-f5b86700-c55f-11eb-9458-ccd810ffa725.png)<br>
~~~
SELECT *
FROM EMPLOYEE E RIGHT OUTER JOIN DEPARTMENT D
ON E.DepartmentID = D.DepartmentID;

<ORACLE>
SELECT *
FROM EMPLOYEE E , DEPARTMENT D
WHERE E.DepartmentID(+) = D.DepartmentID;
~~~

#### 3-3. FULL OUTER JOIN
- Full Outer Join은 LEFT OUTER JOIN과 RIGHT OUTER JOIN을 합친 것이다.
- 양쪽 모두 조건이 일치하지 않는 것들까지 모두 결합하여 출력한다.<br>
![image](https://user-images.githubusercontent.com/44339530/120777482-48921e80-c560-11eb-89b6-df8916fbae43.png)<br>
~~~
SELECT *
FROM EMPLOYEE E FULL OUTER JOIN DEPARTMENT D
ON E.DepartmentID = D.DepartmentID;
~~~

- <b>MySQL에서는 Full Outer Join 키워드가 안되므로 아래와 같이 적용할 수 있다.(물론 방법은 다양함)</b>
~~~
SELECT *
FROM EMPLOYEE E LEFT OUTER JOIN DEPARTMENT D
ON E.DepartmentID = D.DepartmentID
UNION
SELECT *
FROM EMPLOYEE E RIGHT OUTER JOIN DEPARTMENT D
ON E.DEPARTMENTID = D.DEPARTMENTID;
~~~

### 4. SELF JOIN
- SELF JOIN은 테이블에서 자기자신을 조인 시키는 것이다.
~~~
SELECT F.empno, F.ename, S.empno, S.ename, S.job
FROM emp F inner join EMP s
on F.job = S.job
WHERE F.empno < S.empno order by F.empno, S.empno;
~~~

#### 출처
- https://clairdelunes.tistory.com/22





