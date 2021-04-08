
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
