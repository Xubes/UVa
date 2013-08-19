import java.util.*;
import java.io.*;
public class Monocycle {

	final static long NA = Integer.MAX_VALUE; // out "infinite"
	final static int green=0, black=1, red=2, blue=3, white=4, colors = 5; // associate colors to numbers
	static final int north=0, east=1, south=2, west=3, dirs = 4;	// associate directions to numbers
	static final int[][] dirOffsets = {{-1,0},{0,1},{1,0},{0,-1}};	// direction vectors giving the change in row and column when moving forward in the respective direction
	
	static int rows=25,cols=25;	// globals vars row and column which will be used to check out of bounds errors
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int set = 0;
		
		String nextIn;
		while((nextIn = in.readLine())!=null){
			String[] toks = nextIn.trim().split(" ");
			rows = Integer.parseInt(toks[0]);
			cols = Integer.parseInt(toks[1]);
			if(rows==0 && cols==0)
				break;
			// create a hashmap for the nodes we will use
			HashMap<String,Node> stateSpace = new HashMap<String,Node>(rows*cols*colors*dirs);
			// we will need the src and dest states for our shortest path algo
			ArrayList<Node> dests = new ArrayList<Node>();
			ArrayList<Node> srcs = new ArrayList<Node>();
			
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
							for(int dir=north; dir<dirs; dir++){
								dests.add((stateSpace.get(prefix+dir))); // fetch destination state nodes
							}
						}
						if(c=='S'){
							Node src = stateSpace.get(i+","+j+","+green+","+north); // fetch source state-node
							srcs.add(src);
						}
					}
				}
			}

			// add edges to our graph
			addEdges(stateSpace);
			// call bfs routine
			long sol = bfs(srcs,dests);

			set++;
			if(set>1) System.out.println();
			System.out.println("Case #" + set);
			if(sol>0 && sol<NA){
				System.out.printf("minimum time = %d sec\n",sol);
			}
			else{
				System.out.println("destination not reachable");
			}
		}

	}
	
	/* Returns the minimum path length from src to one of the destination nodes by performing a breadth-first search from src node.
	 */
	static long bfs(ArrayList<Node> srcs, ArrayList<Node> dest){
		long minCost = NA;
		
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		for(Node n : srcs){
			n.cost = 0;
			pq.add(n);
		}
		
		while(!pq.isEmpty()){
			Node n = pq.poll();
			for(Node nbor : n.adj){
				long ncost = n.cost+1; // all edges are 1 so the cost from n to its neighbor is 1.
				if(nbor.cost>ncost){
					nbor.cost = ncost;
					pq.add(nbor);
					if(dest.contains(nbor) && ncost<minCost){
						minCost = ncost;
					}
				}
			}
		}
		return minCost;
	}
	
	/* Adds the three edges or each node in the map. */
	static void addEdges(HashMap<String,Node> map){
		for(Node n : map.values()){
			Node[] neighbors = new Node[3];
			String lkey = n.row+","+n.col+","+n.color+","+ ((n.dir+dirs-1)%dirs);	//turn left
			String rkey = n.row+","+n.col+","+n.color+","+ ((n.dir+1)%dirs);	//turn right
			String fkey = (n.row+dirOffsets[n.dir][0]) + "," +			//advance forward
							((n.col+dirOffsets[n.dir][1])) + "," +
							((n.color+1+colors)%colors) + "," + n.dir;
			neighbors[0] = map.get(lkey);
			neighbors[1] = map.get(rkey);
			neighbors[2] = map.get(fkey);
			for(Node nbor : neighbors){
				if(nbor!=null){
					n.adj.add(nbor);
					if(n.equals(nbor)){
						System.err.println("ERROR");
					}
				}
				
			}
			
		}
	}
}


class Node implements Comparable<Node> {
	long cost; // build the sssp cost into the node so we don't have to keep track of another datastructure
	int row,col,color,dir;
	ArrayList<Node> adj;
	public Node(int r, int c, int clr, int d){
		row = r;
		col = c;
		color = clr;
		dir = d;
		adj = new ArrayList<Node>();
		cost = Integer.MAX_VALUE; // init to NA
	}
	
	public String toString(){
		return row+","+col+","+color+","+dir;
	}
	
	/* Compare by cost variable.  Return a zero only if all variables match. */
	public int compareTo(Node n){
		return Long.signum(cost-n.cost);
	}
	
}