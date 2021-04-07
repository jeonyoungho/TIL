
public class Main {

	public static void main(String[] args) {
		/* 주어진 노드를 시작 노드로 DFS 탐색1 */
		Graph g1 = new Graph(4);

	    g1.addEdge(0, 1);
	    g1.addEdge(0, 2);
	    g1.addEdge(1, 2);
	    g1.addEdge(2, 0);
	    g1.addEdge(2, 3);
	    g1.addEdge(3, 3);
	    
	    System.out.println("Start DFS with " + g1.getNodeCount() + " node");
	    // 기대 결과: 2 -> 0 -> 1 -> 3
	    g1.startDFSWithNodeNumber(2);
	    
	    
	    
	    /* 주어진 노드를 시작 노드로 DFS 탐색2 */
	    Graph g2 = new Graph(11);

	    g2.addEdge(1, 2);
	    g2.addEdge(1, 5);
	    g2.addEdge(1, 9);
	    g2.addEdge(2, 3);
	    g2.addEdge(5, 6);
	    g2.addEdge(5, 8);
	    g2.addEdge(9, 10);
	    g2.addEdge(3, 4);
	    g2.addEdge(6, 7);
	    
	    System.out.println("Start DFS with " + g2.getNodeCount() + " node");
	    // 기대 결과: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10
	    g2.startDFSWithNodeNumber(1);
	}

}
