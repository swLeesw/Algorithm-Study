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
    
    static class Node implements Comparable<Node>{
    	int num;
    	int curDistance;
    	
    	public Node(int num, int curDistance) {
    		this.num = num;
    		this.curDistance = curDistance;
    	}
    	
    	public int compareTo(Node o) {
    		return this.curDistance - o.curDistance;
    	}
    	
    }
    
    
    static List<Edge> graph[];
    static int dist[];
    static int n, d;
    
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        dist = new int[d + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        //길 생성
        graph = new ArrayList[d + 1];
        
        for (int i = 0; i < d + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        
        //모든 길 하나 넣기
        for (int i = 0; i < d; i++) {
            graph[i].add(new Edge(i + 1, 1));
        }
        
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            if (v > d) continue;
            
            graph[u].add(new Edge(v, w));
            
        }
        
        
        System.out.println(dijkstra(0));
        
    }

    static int dijkstra(int start) {
        dist[start] = 0; 
       PriorityQueue<Node> pq = new PriorityQueue<>();
       pq.offer(new Node(start, 0));
       
       while (!pq.isEmpty()) {
    	   Node cur = pq.poll();
    	   
    	   for (int i = 0; i < graph[cur.num].size(); i++) {
    		   Edge edge = graph[cur.num].get(i);
    		   int nextDist = cur.curDistance + edge.weight;
    		   if (dist[edge.next] > nextDist) {
    			   dist[edge.next] = nextDist;
    			   pq.offer(new Node(edge.next, nextDist));
    		   }
    		   
    	   }
    	  
    	   
       }
       
        
        
        return dist[d];
    }
    
}
