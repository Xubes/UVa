import java.util.*;
public class CuttingSticks {

	static int[][] cutCosts = new int[52][52]; //memo table
	static int[] cutLocs;
	public static void Main(String[] args){
		Scanner in = new Scanner(System.in);
		
		while(in.hasNext()){
			int length = in.nextInt();
			if(length==0)
				break;
			Arrays.fill(cutCosts, -1); //reset memo table
	
			
			cutLocs = new int[length];
			int cuts = in.nextInt();
			for(int i=1; i<=cuts; i++){ // loads cuts into range 1:cuts
				cutLocs[i] = in.nextInt();
			}
			int cost = solve(0,length);
			System.out.println(cost);
			
		}
	}
	
	static int solve(int begin, int end){
		/* Need base case.*/
		if(begin>=end)
			return 0;
		int left = cutLocs[begin];
		int right = cutLocs[end];
		int bsize = right-left;
		// Compute minimum cost if not found in memoization table
		if(cutCosts[begin][end]==-1){
			int minCost = Integer.MAX_VALUE/2;
			for(int j=begin; j<end-1; j++){
				minCost = Math.min(minCost, bsize+solve(begin,j)+solve(j+1,end));
			}
		}
		return cutCosts[begin][end];
	}
}
