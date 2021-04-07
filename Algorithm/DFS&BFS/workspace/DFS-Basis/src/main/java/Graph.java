import java.util.LinkedList;

public class Graph {
	
	private int nodeCount = 0;
	// 인접한 노드들을 담고 있는 연결 리스트 배열 생성
	private LinkedList<Integer>[] adj;
	
	public Graph() {
		this(10);
	}
	
	@SuppressWarnings("unchecked")
	public Graph(int nodeCount) {
		this.nodeCount = nodeCount;
		adj = new LinkedList[nodeCount];
		
		for(int i=0;i<adj.length;i++) {
			adj[i] = new LinkedList<Integer>();
		}
	}
	
	// 노드v -> 노드w edge추가
	public void addEdge(int v, int w) {
		adj[v].add(w);
	}
	
	private void search(int v, boolean[] isVisited) {
		
		isVisited[v] = true;
		System.out.print(v + " ");
		
		LinkedList<Integer> nodeList = adj[v];
		
		for(Integer node:nodeList) {
			if(isVisited[node] == false) {
				search(node, isVisited);
			}
		}
	}
	
	// 노드v로 부터 DFS시작
	public void startDFSWithNodeNumber(int v) {
		System.out.println("========== Adjacent node list ==========");
		for(int i=0;i<adj.length;i++) {
			System.out.print("[node" + i + "] ");
			for(Integer node:adj[i]) {
				System.out.print(node + " ");
			}
			System.out.println();
		}
		
		// 노드의 방문 여부 판단 (디폴트 값: false)
		boolean[] isVisited = new boolean[nodeCount];
		System.out.print("=> ");
		search(v, isVisited);
		System.out.println();
	}
	
	public void startDFS() {
	  // 노드의 방문 여부 판단 (초깃값: false)
	  boolean isVisited[] = new boolean[nodeCount];
	
	  // 비연결형 그래프의 경우, 모든 정점을 하나씩 방문
	  for (int i=0; i<nodeCount; ++i) {
	      if (isVisited[i] == false)
	    	  search(i, isVisited);
	  }
	}
	
	public int getNodeCount() {
		return this.nodeCount;
	}
	
}
