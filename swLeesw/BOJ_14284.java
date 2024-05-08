import java.util.*;
import java.io.*;


public class Main {
    
	static class Edge {
		int next, weight;

		public Edge(int next, int weight) {
			super();
			this.next = next;
			this.weight = weight;
		}
		
	}
	
	static class Node {
		int num, curWeight;

		public Node(int num, int curWeight) {
			super();
			this.num = num;
			this.curWeight = curWeight;
		}
		
	}
	
	
	static int n, m, s, t, dist[];
	static List<Edge> graph[];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int u, v, w;
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        graph = new List[n + 1];
        
        for (int i = 1; i <= n; i++) {
        	graph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < m; i++) {
        	st = new StringTokenizer(br.readLine());
        	u = Integer.parseInt(st.nextToken());
        	v = Integer.parseInt(st.nextToken());
        	w = Integer.parseInt(st.nextToken());
        	
        	graph[u].add(new Edge(v, w));
        	graph[v].add(new Edge(u, w));
        }
        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        System.out.println(dijkstra(s));
        
    }

   static int dijkstra(int start) {
	   dist[start] = 0; //시작점 0 초기화
	   PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
		   return o1.curWeight - o2.curWeight;
	   });
	   pq.offer(new Node(start, 0));
	   
	   while (!pq.isEmpty()) {
		   Node cur = pq.poll();
		   
		   for (Edge edge : graph[cur.num]) {
			   int nextWeight = cur.curWeight + edge.weight;
			   
			   if (dist[edge.next] > nextWeight) {
				   dist[edge.next] = nextWeight;
				   pq.offer(new Node(edge.next, nextWeight));
				   
			   }
			   
		   }
		   
	   }
	   
	   
	   return dist[t];
   }
    
}
