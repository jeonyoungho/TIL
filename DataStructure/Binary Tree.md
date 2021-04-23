# Binary Tree(이진트리)

### 이진트리(Binary Tree)
- 이진트리는 트리를 구성하는 노드들의 최대 차수(degree)가 2인 노드들로 구성되는 트리이다.
    - 이진트리의 레벨i에서 가질 수 있는 최대 노드의 2^i이다.(i>=0)
    - 깊이가 k인 이진트리가 가질 수 있는 최대 노드의 수는 2^k-1이다.(k>=1)

- 이진트리는 <b>완전 이진 트리(Completable Binary Tree)와 포화 이진 트리(Perfect Binary Tree), 전 이진 트리(Full Binary Tree)</b>라고 하는 특별한 트리 구조를 정의할 수 있다.<br>
<img width="493" alt="스크린샷 2021-04-22 오후 3 50 46" src="https://user-images.githubusercontent.com/44339530/115668804-82c5a900-a382-11eb-9b47-1ed7a8901b74.png"><br>

#### 완전 이진 트리(Completable Binary Tree)
<img width="520" alt="스크린샷 2021-04-22 오후 3 55 54" src="https://user-images.githubusercontent.com/44339530/115669440-39298e00-a383-11eb-81ba-f0931297788c.png"><br>

- <b>트리의 모든 높이에서 노드가 꽉 차 있는 이진 트리. 즉, 마지막 레벨을 제외하고 모든 레벨이 완전히 채워져 있다.</b>
- <b>마지막 레벨은 꽉 차 있지 않아도 되자만 노드가 왼쪽에서 오른쪽으로 채워져야 한다.</b>
- 마지막 레벨 h에서 (1 ~ 2h-1)개의 노드를 가질 수 있다
- 왼쪽에서 오른쪽으로 채워지는 이진트리(heap)
- 또 다른 정의는 가장 오른쪽의 잎 노드가 (아마도 모두) 제거된 포화 이진 트리다.
- 완전 이진 트리는 배열을 사용해 효율적으로 표현 가능하다.

#### 전 이진 트리(Full Binary Tree)
<img width="470" alt="스크린샷 2021-04-22 오후 3 56 04" src="https://user-images.githubusercontent.com/44339530/115669462-3fb80580-a383-11eb-9184-dc24782aaa55.png"><br>

- <b>모든 노드가 0개 또는 2개의 자식 노드를 갖는 트리.</b>

#### 포화 이진 트리(Perfect Binary Tree)
<img width="271" alt="스크린샷 2021-04-22 오후 3 56 12" src="https://user-images.githubusercontent.com/44339530/115669480-43e42300-a383-11eb-9403-84dab5ad1310.png"><br>

- 전 이진 트리이면서 완전 이진 트리인 경우
- <b>모든 말단 노드는 같은 높이에 있어야 하며, 마지막 단계에서 노드의 개수가 최대가 되어야 한다.</b>
- 모든 내부 노드가 두 개의 자식 노드를 가진다.
- 모든 말단 노드가 동일한 깊이 또는 레벨을 갖는다.
- 노드의 개수가 정확히 2^(k-1)개여야 한다. (k:트리의 높이)

#### 출처
- https://velog.io/@adam2/TREE
- https://gmlwjd9405.github.io/2018/08/12/data-structure-tree.html