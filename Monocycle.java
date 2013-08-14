import java.util.*;
import java.io.*;
public class Monocycle {

	final static long NA = Integer.MAX_VALUE;
	final static int green=0, black=1, red=2, blue=3, white=4, colors = 5;
	static final int north=0, east=1, south=2, west=3, dirs = 4;
	static final int[][] dirOffsets = {{-1,0},{0,1},{1,0},{0,-1}};
	
	static int rows=25,cols=25;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int set = 0;
		
		String[] szdata = in.readLine().trim().split(" ");
		rows = Integer.parseInt(szdata[0]);
		cols = Integer.parseInt(szdata[1]);
		while(!(rows==0 && cols==0)){
			set++;
			//set proper spacing between problem sets.
			if(set>1){
				System.out.println("\n");
			}
			System.out.println("Case #" + set);
			
			// create a hashmap for the nodes we will use
			HashMap<String,Node> stateSpace = new HashMap<String,Node>(rows*cols*colors*dirs);
			// we will need the src and dest states for our shortest path algo
			Node src = null;
			ArrayList<Node> dests = new ArrayList<Node>();
			
			// process input
			for(int i=0; i<rows; i++){
				char[] nrow = in.readLine().toCharArray();
				for(int j=0; j<nrow.length; j++){
					char c = nrow[j];
					if(c!='#'){
						// generate and add the state space nodes

						for(int color=green; color<colors; color++){
							for(int direction=north; direction<dirs; direction++){
								Node state = new Node(i,j,color,direction);
								stateSpace.put(state.toString(),state); // toStrings() should generate unique keys
							}
						}
						if(c=='T'){
							String prefix = i+","+j+","+green+",";
							for(int dir=north; dir<=west; dir++){
								dests.add((stateSpace.get(prefix+dir))); // fetch destination state nodes
							}
						}
						if(c=='S') src = stateSpace.get(i+","+j+","+green+","+north); // fetch source state-node
					}
				}
			}
			
			/* We have all our nodes, now we need to make the edges by setting the adjacency lists.
			 */
			addEdges(stateSpace);
			
			// now call dijkstras sssp algorithm
			long sol = (src!=null)? dijk(stateSpace.values(),src,dests) : NA;

			if(sol>=0 && sol<NA){
				System.out.printf("minimum time = %d sec",sol);
			}
			else{
				System.out.print("destination not reachable");
			}
			szdata = in.readLine().trim().split(" ");
			rows = Integer.parseInt(szdata[0]);
			cols = Integer.parseInt(szdata[1]);
		}

	}
	
	// returns the minimum path length from src to one of the destination nodes
	static long dijk(Collection<Node> states, Node src, ArrayList<Node> dest){
		long minCost = NA;
		src.setCost(0);
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.add(src);
		
		while(!pq.isEmpty()){
			Node n = pq.poll();
			if(n.cost>=NA)
				continue;
			n.visited = true;
			for(Node neighbor : n.adj){
				if(neighbor.visited)
					continue;
				long ncost = n.cost+1; // all edges are 1 so the cost from n to its neighbor is 1.
				if(neighbor.cost>ncost){
					neighbor.setCost(ncost);
					if(dest.contains(neighbor) && neighbor.cost<minCost){
						minCost = neighbor.cost;
					}
				}

				pq.add(neighbor);
			}
		}
		return minCost;
	}
	
	 /* For each node, we want:
	  * 	- an edge to adjacent directions since we can rotate.
	  * 	- an edge to an adjacent node on the grid formed by (row,col). This node is determined by the current direction & color.
	  * We take advantage of our hashmapped nodes having keys which are the csv concatenations of each dimension.
	  */
	static void addEdges(HashMap<String,Node> all){
		Collection<Node> nodes = all.values();
		if(all.containsKey(" "));
		for(Node n : nodes){
			int r = n.row;
			int c = n.col;
			int clr = n.color;
			int d = n.dir;
			
			
			ArrayList<Node> neighs = new ArrayList<Node>();
			ArrayList<String> keys = new ArrayList<String>();
			
			// add adjacent directions
			keys.add(r+","+c+","+clr+","+(d-1)%dirs);
			keys.add(r+","+c+","+clr+","+(d+1)%dirs);
			
			// calculate r,c,clr for adj
			int ra = r+dirOffsets[d][0];
			int ca = c+dirOffsets[d][1];
			int clra = (clr+1)%colors;
			keys.add(ra+ "," + ca + "," + clra+ "," + d);
			for(String k : keys){
				if(all.containsKey(k)) neighs.add(all.get(k));
			}
			n.setAdj(neighs);
		}
	}
}

class Node implements Comparable<Node>{
	boolean visited = false;
	long cost; // build the sssp cost into the node so we don't have to keep track of another datastructure
	int row,col,color,dir;
	ArrayList<Node> adj; // list of neighbors
	public Node(int r, int c, int clr, int d){
		row = r;
		col = c;
		color = clr;
		dir = d;
		cost = Integer.MAX_VALUE; // init to NA
		adj = new ArrayList<Node>();
	}
	
	public int compareTo(Node n){
		long c = cost-n.cost;
		return Long.signum(c);
	}
	
	public void setCost(long nc){
		cost = nc;
	}
	//sets the given ArrayList of nodes as the adj
	public void setAdj(ArrayList<Node> nadj){
		adj = nadj;
	}
	// adds a link to neighbor node to the adj map
	public void addAdj(Node n){
		if(!adj.contains(n))
			adj.add(n);
	}
	
	public String toString(){
		return row+","+col+","+color+","+dir;
	}
	
}