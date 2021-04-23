# red-black 트리 & AVL 트리

## red-black 트리와 AVL트리가 생겨난 배경
<img width="411" alt="스크린샷 2021-04-22 오후 5 03 52" src="https://user-images.githubusercontent.com/44339530/115678674-b86f8f80-a38c-11eb-86f3-e61c7913992e.png"><br>

- 이진 탐색 트리는 평균적으로 O(logN)의 삽입, 삭제, 검색 연산속도를 가진다.
- 이진 검색 트리의 구성 조건은 left < root < right이다.
- 만약 순차정렬된 데이터가 들어온다면 이진 검색트리는 위의 그림과 같이 편향트리가 될 것이다.
- 트리의 속도는 트리의 깊이에 따라 결정 되기에 편향 트리의 시간복잡도는 O(N)까지 늘어난다.
- 이러한 일을 방지하기 위해 균형 이진 트리가 발생하였고 이의 <b>종류로는 red-black트리와 AVL트리</b>가 있다.<br>
<img width="554" alt="스크린샷 2021-04-22 오후 5 06 35" src="https://user-images.githubusercontent.com/44339530/115679022-19976300-a38d-11eb-90c9-9c8d2753084a.png"><br>

- 이렇게 트리의 깊이가 균형이 맞게 된다면 이진 탐색 알고리즘의 속도와 비슷해져서 아주 효율적으로 사용할 수 있다.
- <br>자바에서 treeSet, treeMap이 Red-Black트리를 구현하고 있다.

## red-black트리란?
<img width="554" alt="스크린샷 2021-04-22 오후 5 06 35" src="https://user-images.githubusercontent.com/44339530/115679022-19976300-a38d-11eb-90c9-9c8d2753084a.png"><br>

### red-black트리의 조건
    - 1)트리의 모든 노드는 레드 or 블랙
    - 2)루트 노드는 무조건 블랙(루트 13은 블랙)
    - 3)모든 단말노드는 블랙(Sentinel Node가 블랙인거에 유의)
    - 4)루트 노드에서 단말 노드까지 블랙의 갯수는 항상 같다.
        - 그림 상 있는 NIL까지 도달할 때 보더라도
        - 단말 노드 6은 블랙 노드 1을 만난다.
        - 단말 노드 22는 블랙 노드 25를 만난다.
        - 단말 노드 27은 블랙 노드 25를 만난다.
    - 5)레드 노드의 자식은 모두 블랙, 블랙 노드의 자식은 상관없음

### red-black트리의 동작
-  red-black트리도 이진 탐색 트리기 때문에 탐색, 삽입, 삭제 연산으로 동작한다.
- 탐색 연산은 변경할 필요가 없으나 <b>삽입, 삭제 연산의 경우 균형을 맞춰주는 동작이 필요하다.</b>

#### Restructuring, Recoloring 연산
- 자신(Z)과 부모 노드(V)가 레드라면
    - 삼촌 노드(W)가 블랙이거나 NULL일 때 Restructuring(Rotation)<br>
        - ![12](https://user-images.githubusercontent.com/44339530/115680392-7a736b00-a38e-11eb-8858-9f23374b55d3.png)<br>
        - ![13](https://user-images.githubusercontent.com/44339530/115680400-7c3d2e80-a38e-11eb-9a1e-ff6d46b089ce.png)<br>
        - 나(Z)와 내 부모(V), 내 부모의 부모(조상)을 오름차순으로 정렬한다.
        - 무조건 가운데 있는 값을 부모로 만들고 나머지 둘을 자식으로 만든다.
        - 올라간 가운데 있는 값을 블랙으로 만들고 그 두 자식들을 레드로 만든다
    - 삼촌 노드(W)가 레드일 때 Recoloring<br>
        - ![14](https://user-images.githubusercontent.com/44339530/115680583-a858af80-a38e-11eb-8e2a-394ad7f6846c.png)<br>
        - ![15](https://user-images.githubusercontent.com/44339530/115680590-aa227300-a38e-11eb-97e3-5833e2bde340.png)<br>
        - 삽입된 노드(Z)의 부모와 삼촌(W)을 블랙으로 색을 변경하고 부모의 부모(조상)노드를 레드로 만든다.
        - 내 부모의 부모(조상)노드가 root가 아니었을 시 더블 레드가 다시 발생할 수 있다.

### red-black 트리의 예제(첫번째 블로그가 더 이해하기 쉬움)
- https://nesoy.github.io/articles/2018-08/Algorithm-RedblackTree
- https://zeddios.tistory.com/237

### red-black 트리의 시간 복잡도
|연산|평균|최악|
|-----|-----|-----|
|공간|O(N)|O(N)|
|탐색|O(logN)|O(logN)|
|삽입|O(logN)|O(logN)|
|삭제|O(logN)|O(logN)|

### red-black 트리 정리
- <b>※red-black 트리는 이진 탐색 트리에서 삽입/삭제 연산에, 이런 균형을 잡는 Restructuring, Recoloring 연산을 전 노드에 대해 재귀적으로 수행하는 과정을 추가하는 트리이다.</b>

## AVL트리란?
- 이진 탐색 트리를 기본으로 하며 트리의 균형이 깨질 때 4가지 회전을 통해서, 스스로 균형을 유지하는 트리이다.

### AVL트리의 4가지 회전 연산
#### 1) LL회전
![16](https://user-images.githubusercontent.com/44339530/115683166-2322ca00-a391-11eb-89e2-f594e8009366.png)<br>
- LL회전 LL상태에서 발생한다. 위와 같은 트리가 존재할 때 루트 노드 기준으로 왼쪽의 높이는 2, 오른쪽의 높이는 0이다.<br>
- ![17](https://user-images.githubusercontent.com/44339530/115683443-6b41ec80-a391-11eb-93f5-21ee1fe7a452.png)<br>
- 또한, 루트 노드의 왼쪽 자식 노드 기준으로는 왼쪽 높이는 1 오른쪽 높이는 0
- 즉, <b>루트 노드 기준으로 왼쪽 높이 - 오른쪽 높이 > 1, 왼쪽 자신 노드 기준에서 또, 왼쪽 높이 > 오른쪽 높이인 상태를 LL상태</b>라고 한다.

- 이 상황에서 LL회전은 다음과 같이 일어난다. 먼저 루트 노드를 p, 왼쪽 자식 노드를 c, 그 노드의 왼쪽 자식 노드를 g라고 가정하자.<br>
- ![18](https://user-images.githubusercontent.com/44339530/115683844-ce338380-a391-11eb-8037-f7c1b7d339b1.png)<br>
- 먼저 부모 노드 p의 왼쪽 자식 노드를, 자식 노드 c의 오른쪽 자식 노드로 바꾼다.<br>
- ![19](https://user-images.githubusercontent.com/44339530/115684355-3edaa000-a392-11eb-8909-79d2f9ad3835.png)<br>
- 자식 노드였떤 c의 오른쪽 자식 노드를 부모 노드 p로 하면 균형이 잡혀지게 된다.<br>
- ![20](https://user-images.githubusercontent.com/44339530/115684497-5f0a5f00-a392-11eb-9193-377f049f31c3.png)<br>

#### 2) RR 회전
![21](https://user-images.githubusercontent.com/44339530/115684655-82350e80-a392-11eb-9505-90d80ecd1c6f.png)<br>
- RR회전은 RR상태에서 발생한다. 위와 같은 트리가 존재할 때 <b>루트 노드 기준, 오른쪽 높이 - 왼쪽 높이 > 1, 오른쪽 자식 노드 기준에서 또, 오른쪽 높이 > 왼쪽 높이인 상태를 RR상태</b>라고 한다.
- RR회전은 먼저 부모 노드 p의 오른쪽 자식 노드를, 자식 노드 c의 왼쪽 자식 노드로 바꾼다.<br>
- ![22](https://user-images.githubusercontent.com/44339530/115685146-ed7ee080-a392-11eb-867e-395c3180694d.png)<br>
- 자식 노드였던 c의 왼쪽 자식 노드를 부모 노드 p로 하면 균형이 잡히게 된다.<br>
![23](https://user-images.githubusercontent.com/44339530/115685516-40589800-a393-11eb-9bae-a3c9c1a1fac9.png)<br>

#### 3) LR 회전
![24](https://user-images.githubusercontent.com/44339530/115685687-6a11bf00-a393-11eb-9b00-39b6d5d968ce.png)<br>
- LR회전은 LR상태에서 발생한다. <b>루트 노드 기준, 왼쪽 높이 - 오른쪽 높이 > 1, 자식 노드 기준에서 또, 오른쪽 높이 > 왼쪽 높이인 상태를 LR상태</b>라고 한다.
- LR 회전은 위의 그림에서 자식 노드 c에 대해 RR회전을 수행한다.<br>
![25](https://user-images.githubusercontent.com/44339530/115686264-fd4af480-a393-11eb-8f48-50e8ad339e05.png)<br>

- 수행 후 아래의 그림과 같아진다.<br>
![26](https://user-images.githubusercontent.com/44339530/115686358-12c01e80-a394-11eb-8273-fc84849f4292.png)<br>

- 그 후, 부모 노드 p에 대해서 LL회전을 수행한다.
![27](https://user-images.githubusercontent.com/44339530/115686541-413df980-a394-11eb-855c-414b4f45a9df.png)<br>

- 결국, LR회전의 결과는 다음과 같아져 균형을 맞추게 된다.
![28](https://user-images.githubusercontent.com/44339530/115686543-4307bd00-a394-11eb-83b3-f79c578b7c1c.png)<br>

#### 4) RL 회전
![29](https://user-images.githubusercontent.com/44339530/115686624-5b77d780-a394-11eb-9518-89b779f9452e.png)<br>

- RL회전은 RL 상태에서 발생한다. <b>루트 노드 기준, 오른쪽 높이 - 왼쪽 높이 > 1, 오른쪽 자식 노드 기준에서 또, 왼쪽 높이 > 오른쪽 높이인 상태를 RL 상태</b>라고 합니다. 
-  RL 회전은 자식 노드 c에 대해서 LL회전을 수행한다.<br>
![30](https://user-images.githubusercontent.com/44339530/115686808-8cf0a300-a394-11eb-8ed4-8cdbae0a5613.png)<br>

- 수행 후 아래의 그림과 같아진다.<br>
![31](https://user-images.githubusercontent.com/44339530/115686873-9aa62880-a394-11eb-8311-4511c0ebd860.png)<br>

- 그 후, 부모 노드 p에 대해서 RR회전을 수행한다.<br>
![32](https://user-images.githubusercontent.com/44339530/115687017-b9a4ba80-a394-11eb-8324-a239796a1612.png)<br>

- 결국, RR회전의 결과는 다음과 같아져 균형을 맞추게 된다.
![33](https://user-images.githubusercontent.com/44339530/115687022-bad5e780-a394-11eb-9114-495bc699f266.png)<br>

### AVL 트리 예제
- https://m.blog.naver.com/PostView.nhn?blogId=dhdh6190&logNo=221062784111&proxyReferer=https:%2F%2Fwww.google.com%2F

### AVL트리의 시간 복잡도
|연산|평균|최악|
|-----|-----|-----|
|공간|O(N)|O(N)|
|탐색|O(logN)|O(logN)|
|삽입|O(logN)|O(logN)|
|삭제|O(logN)|O(logN)|
- <b>red-black트리와 AVL트리의 시간 복잡도는 동일</b>

### AVL트리 정리
- <b>※AVL 트리는 이진 탐색 트리에서 삽입/삭제 연산에, 이런 균형을 잡는 회전을 전 노드에 대해서 재귀적으로 수행하는 과정을 추가하는 트리이다.</b>

## Red-Black 트리 vs AVL 트리
- <b>AVL Tree가 red-black Tree보다 빠른 Search를 제공합니다.</b>
    - AVL트리는 더욱 엄격한 균형을 이루고 있기 때문이다.
- <b>red-black Tree은 AVL Tree보다 빠른 삽입과 삭제를 제공한다.</b>
    - red-black 트리는 상대적으로 느슨한 균형으로 인해 회전이 거의 이루어지지 않기 때문이다.

- red-black Tree는 AVL Tree보다 색깔을 저장하기 위해 더 많은 Space Complexity가 필요
- red-black Tree는 Java의 treeMap과 같이 대부분의 언어의 map, multimap, multiset에 사용되고 있다.
- AVL tree는 검색에 속도가 중요한 Database에 사용되고 있다.

## 출처
- https://velog.io/@agugu95/%EC%9D%B4%EC%A7%84-%ED%8A%B8%EB%A6%AC%EC%9D%98-%EA%B7%A0%ED%98%95-RED-BALCKAVL
- https://gurumee92.tistory.com/146
- https://velog.io/@agugu95/%EC%9D%B4%EC%A7%84-%ED%8A%B8%EB%A6%AC%EC%9D%98-%EA%B7%A0%ED%98%95-RED-BALCKAVL
- https://nesoy.github.io/articles/2018-08/Algorithm-RedblackTree