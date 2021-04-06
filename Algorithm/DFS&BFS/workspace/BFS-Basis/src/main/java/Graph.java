import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {

	private int nodeCount;
	private LinkedList<Integer>[] adj;

	public Graph() {
		this(10);
	}

	@SuppressWarnings("unchecked")
	public Graph(int nodeCount) {
		this.nodeCount = nodeCount;
		adj = new LinkedList[nodeCount];
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new LinkedList<Integer>();
		}
	}

	public void addEdge(int v, int w) {
		adj[v].add(w);
	}

	private void BFS(int v, boolean[] isVisited) {

		// 방문한 노드들을 보관하기 위한 큐
		Queue<Integer> queue = new LinkedList<Integer>();

		isVisited[v] = true;
		queue.offer(v);

		while (queue.size() > 0) {
			// 큐에서 노드를 꺼낸 후 방문
			int node = queue.poll();
			System.out.print(node + " ");

			LinkedList<Integer> list = adj[node];
			for (Integer n : list) {
				if(isVisited[n] == false) {
					isVisited[n] = true;
					queue.offer(n);
				}
			}
		}

	}

	public void startBFS(int v) {
		System.out.println("========== Adjacent node list ==========");
		for(int i=0;i<adj.length;i++) {
			System.out.print("[node" + i + "] ");
			for(Integer node:adj[i]) {
				System.out.print(node + " ");
			}
			System.out.println();
		}
		
		boolean[] isVisited = new boolean[nodeCount];
		System.out.print("=> ");
		BFS(v, isVisited);
		System.out.println();
	}

	public int getNodeCount() {
		return this.nodeCount;
	}

}
