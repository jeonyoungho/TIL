# 유니온파인드(Union-Find) 개요

## 유니온파인드란?
- 여러 노드가 존재할 때, 두 개의 노드를 선택해서, 현재 두 노드가 서로 같은 그래프에 속하는지 판별하는 알고리즘
- 그래프 알고리즘의 일종
- 2가지 연산이 존재
    - Find : x가 어떤 집합에 포함되어 있는지 찾는 연산
    - Union : x와 y가 포함되어 있는 집합을 합치는 연산

## 실습 코드
~~~
public class TestMain {

	private final static int NODE_NUM = 6;
	
	public static void main(String[] args) {
		
		int[] parent = new int[NODE_NUM];
		
		for(int i=0;i<NODE_NUM;i++) {
			parent[i] = i; // 자기 자신으로 루트 노드를 초기화
		}
		
		union(parent, 1, 2);
		union(parent, 2, 3);
		
		for(int i=0;i<NODE_NUM;i++) {
			System.out.println("[" + i + "] root node -> " + find(parent, i));
		}
		
		System.out.println("2와 3은 연결되있나요? -> " + isSameParent(parent, 2, 3));
	}
	
	// 특정 노드의 부모노드를 찾아내는 메소드
	private static int find(int[] parent, int v) {
		if(parent[v] == v)
			return parent[v];
		
		return parent[v] = find(parent, parent[v]);
	}
	
	// 두 노드가 속한 트리들을 합치는 메소드
	private static void union(int[] parent, int v, int w) {
		int r1 = find(parent, v);
		int r2 = find(parent, w);
		
		if(r1 == r2) return;
		
		if(r1 < r2) {
			parent[r2] = r1;
		} else {
			parent[r1] = r2;
		}
		
	}
	
	private static boolean isSameParent(int[] parent, int v, int w) {
		int r1 = find(parent, v);
		int r2 = find(parent, w);
		
		if(r1 == r2)
			return true;
		else
			return false;
	}

}
~~~

#### 출처
- https://www.crocus.co.kr/683
- https://brenden.tistory.com/33