import java.io.*;
import java.util.*;
public class MonoSlick {

	//lsb->msb : 2bits for dir, 2 bits for color, 5 bits for row and col each
	static final int DIR_MASK = 0x3,
						CLR_MASK = 0x3<<2,
						COL_MASK = 0x1F<<4,
						ROW_MASK = 0x1F<<9;
	static final int north = 0, east = 1, south = 2, west = 3, dirs = 4;
	static final int green = 0, colors=5;
	static final int[][] dvecs = {{-1,0},{0,1},{1,0},{0,-1}}; // the direction vectors for our 4 directions
	static char[][] grid;
	static int rows, columns;
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int set=1;
		String nl;
		while((nl=in.readLine())!=null){
			String[] toks = nl.trim().split(" ");
			rows = Integer.parseInt(toks[0]);
			columns = Integer.parseInt(toks[1]);
			
			int start=0, end=0; //stores the start and end states
			/* Init grid and parse int the input. */
			grid = new char[rows][columns];
			for(int i=0; i<rows; i++){
				grid[i] = in.readLine().trim().toCharArray();
				for(int j=0; j<columns; j++){
					if(grid[i][j] == 'S'){
						start = encode(i,j,green,north);
					}
					else if(grid[i][j] == 'T'){
						end = encode(i,j,green,north); // note the direction is irrelevant for target
					}
				}
			}
			
			int solution = bfs(start,end);
			System.out.printf("Case #%d:\n", set++);
			if(solution!=-1){
				System.out.printf("minimum time = %d sec\n", solution);
			}
			else{
				System.out.println("destination not reachable");
			}
		}
	}
	
	/* BFS on an implicit graph.
	 * For every node in our queue, we find the left, right, and forward nodes.
	 * If any of these new nodes were never traversed (does not appear in the visited list) then
	 * we add it to the second queue (q2).  After we have processed all the nodes, we add all of the nodes
	 * from q2 into q and repeat.
	 * 
	 * Each iteration increases the cost by 1 second.  If we reach the destination row,col,clr state,
	 * 	return the cost (number of iterations).
	 * Otherwise, return -1;
	 */
	public static int bfs(int s, int t){
		TreeSet<Integer> visited = new TreeSet<Integer>(); // set of visited states so we don't double back
		LinkedList<Integer> q = new LinkedList<Integer>(); // our bfs queue
		q.add(s);
		
		// decode the target state:
		int tr = getRow(t), tc = getCol(t), tclr = getClr(t);
		
		int dist = 0;
		while(!q.isEmpty()){
			LinkedList<Integer> q2 = new LinkedList<Integer>();

			for(int node : q){
				// decode the state
				int r = getRow(node);
				int c = getCol(node);
				int clr = getClr(node);
				int dir = getDir(node);
				
				if(r==tr && c==tc && clr==tclr)		return dist;
				
				int leftTurn = encode(r,c,clr,(dir+3)%dirs);	// node for a left turn
				if(!visited.contains(leftTurn)){
					q2.add(leftTurn);
					visited.add(leftTurn);
				}
				
				int rightTurn = encode(r,c,clr,(dir+1)%dirs);	//node for a right turn
				if(!visited.contains(rightTurn)){
					q2.add(rightTurn);
					visited.add(rightTurn);
				}
				
				int rf = r+dvecs[dir][0], cf = c+dvecs[dir][1]; //new row,col position if we move forward in the current direction
				// Only add forward move if the grid location is passable
				if(rf>=0 && rf<rows && cf>=0 && cf<columns && grid[rf][cf]!='#'){
					int forward = encode(rf,cf,(clr+1)%colors,dir);
					if(!visited.contains(forward)){
						q2.add(forward);
						visited.add(forward);
					}
				}
			}
			q = q2;
			dist++;
		}
		return -1;
	}
	
	// returns the state properties into encoded into an int
	public static int encode(int row, int col, int color, int dir){
		return (row<<9) | (col<<4) | (color<<2) | (dir);
	}
	
	// get the row of an encoded state
	public static int getRow(int state){
		return (state&ROW_MASK)>>>9;
	}
	
	// get the column of an encoded state
	public static int getCol(int state){
		return (state&COL_MASK)>>>4;
	}
	
	// get the color of an encoded state
	public static int getClr(int state){
		return (state&CLR_MASK)>>>2;
	}
	
	// get the direction of an encoded state
	public static int getDir(int state){
		return state&DIR_MASK;
	}
}
