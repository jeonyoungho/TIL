# Tree

## 트리란?
- 계층적인 자료를 표현하기 위한 자료구조
- 데이터 요소들의 단순한 나열이 아닌 부모-자식 관계의 계층적 구조로 표현
- 트리는 그래프의 한 종류이며 사이클이 없다.
- 실제 나무를 거꾸로 한 것과 같은 모양을 하고 있기 때문에 트리라고 부른다.<br>

![2](https://user-images.githubusercontent.com/44339530/115667554-efd83f00-a380-11eb-9c30-319d5b0e7a40.png)
<br>

- node: 트리를 구성하고 있는 각 요소
- Edge(간선): 트리를 구성하기 위해 노드와 노드를 연결하는 선
- RootNode: 최상위 계층에 존재하는 노드
- level: 트리의 특정 깊이를 가지는 노드의 집합
- degree:(차수): 하위 트리 개수 / 간선 수(degree) = 각 노드가 가진 가지의 수
- Terminal Node ( = leaf Node, 단말 노드) : 하위에 다른 노드가 연결되어 있지 않은 노드
- Internal Node (내부노드, 비단말 노드) : 단말 노드를 제외한 모든 노드로 루트 노드를 포함한다.

## 트리의 특징
- 그래프의 한 종류이며 '최소 연결 트리' 라고도 불린다.
- 트리는 계층 모델이다.
- 트리는 DAG(Directed Acyclic Graphs, 방향성이 있는 비순환 그래프)의 한 종류이다.
    - loop나 circuit이 없다. 당연히 self-loop도 없다.
    - 즉, 사이클이 없다.

- 즉 노드가 N개인 트리는 항상 N-1개의 간선(edge)을 가진다.
    - 즉, 간선은 항상 (정점의 개수 - 1) 만큼을 가진다.
- 루트에서 어떤 노드로 가는 경로는 유일하다.
    - 임의의 두 노드 간의 경로도 유일하다. 즉, 두 개의 정점 사이에 반드시 1개의 경로만을 가진다.
- 한 개의 루트 노드만이 존재하며 모든 자식 노드는 한개의 부모 노드만을 가진다.
    - 부모-자식 관계이므로 흐름은 top-bottom 아니면 bottom-top으로 이루어진다.
- 순회는 Pre-order(전위순회), In-order(중위순회)아니면 Post-order(후위순회)로 이루어진다. 이 3가지 모두 DBF/BFS안에 있다.
- 트리는 이진트리, 이진 탐색 트리, 균형트리(AVL 트리, red-black 트리), 이진 힙(최대힙, 최소힙) 등이 있다.

## 트리의 종류

### [1. 이진 트리]()
### [2. 이진 탐색 트리]()
### [3. 균형트리(AVL트리, red-black 트리)]()
### [4. 이진 힙(최대 힙, 최소힙)]()



## 트리의 구현 방법
- 기본적으로 트리는 그래프의 한 종류이므로 그래프의 구현 방법(리스트 또는 배열)으로 구현할 수 있다.

### 배열 이용
- 1)1차원 배열에 자신의 부모 노드만 저장하는 방법
    - 트리는 부모 노드를 0개 또는 1개를 가지기 때문
    - 루트노드는 부모 노드가 자기 자신
- 2)1차원 배열에 트리구조상의 위치에 해당하는 값을 저장하는 방법
    - <img width="826" alt="스크린샷 2021-04-22 오후 4 03 38" src="https://user-images.githubusercontent.com/44339530/115670364-4dba5600-a384-11eb-975c-79e1a5efd9a1.png"><br>
- 3)이진 트리의 경우, 2차원 배열에 자식 노드를 저장하는 방법
    - 이진 트리는 각 노드가 최대 두 개의 자식을 갖는 트리이기 때문
    - Ex) A[i][0]: 왼쪽 자식 노드, A[i][1]: 오른쪽 자식 노드

#### 배열의 단점
- 빈 노드에 대해서는 계속 사용하지 않기 때문에 메모리 낭비
- 데이터의 삽입, 삭제 연산시 노드의 레벨 변경으로 인한 데이터의 이동 발생

### 연결리스트를 이용
- 각 노드는 data필드와 왼쪽 서브 트리를 가리키는 필드, 오른쪽 서브 트리를 가리키는 필드로 구성

## 이진 트리의 순회
<img width="259" alt="스크린샷 2021-04-22 오후 4 10 05" src="https://user-images.githubusercontent.com/44339530/115671173-3465d980-a385-11eb-8fcc-5b3558ba7927.png"><br>

- 중위 순회(inorder traversal)
    - 왼쪽 서브트리 -> 루트 -> 오른쪽 서브트리
    - 루트 노드가 가운데에 온다.
    - 4-2-5-1-3
    ~~~
    void inOrderTraversal(TreeNode node) {
        if(node != null) {
            inOrderTraversal(node.left);
            visit(node);
            inOrderTraversal(node.right);
        }
    }
    ~~~

- 후위 순회(postorder traversal)
    - 왼쪽 서브트리 -> 오른쪽 서브트리 -> 루트
    - 루트 노드가 후위에 온다.
    - 4-5-2-3-1
    ~~~
    void postOrderTraversal(TreeNode node) {
        if(node != null) {
            postOrderTraversal(node.left);
            postOrderTraversal(node.right);
            visit(node);
        }
    }
    ~~~

- 전위 순회(preorder traversal)
    - 루트 -> 왼쪽 -> 오른쪽
    - 루트 노드가 맨 앞에 온다.
    - 1-2-4-5-3
    ~~~
    void preOrderTraversal(TreeNode node) {
        if(node != null) {
            visit(node);
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }
    ~~~

#### 출처
- https://velog.io/@adam2/TREE
- https://gmlwjd9405.github.io/2018/08/12/data-structure-tree.html