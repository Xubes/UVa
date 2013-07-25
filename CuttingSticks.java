import java.util.*;
public class CuttingSticks {

	static int[][] cutCosts = new int[52][52]; //memo table
	static int[] cutLocs = new int[52];
	
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		
		while(in.hasNext()){
			int length = in.nextInt();
			if(length==0)
				break;
			for(int[] arr : cutCosts){
				Arrays.fill(arr, -1); //reset memo table
			}
			
			//cutLocs = new int[length];
			Arrays.fill(cutLocs,0); // we use zero as "first cut point"
			int cuts = in.nextInt();
			for(int i=1; i<=cuts; i++){ // loads cuts into range 1:cuts
				cutLocs[i] = in.nextInt();
			}
			cutLocs[cuts+1]=length;//end point is "last cut point"
			
			int cost = solve(0,cuts+1);
			System.out.printf("The minimum cutting is %d.\n", cost);
			
		}
	}
	
	static int solve(int begin, int end){
		/* Need base case.*/
		if(begin+1==end)
			return 0;
		int left = cutLocs[begin];
		int right = cutLocs[end];
		int bsize = right-left;
		// Compute minimum cost if not found in memoization table
		if(cutCosts[begin][end]==-1){
			int minCost = Integer.MAX_VALUE/2;
			for(int j=begin+1; j<end; j++){
				minCost = Math.min(minCost, bsize+solve(begin,j)+solve(j,end));
			}
			cutCosts[begin][end] = minCost;
		}
		return cutCosts[begin][end];
	}
}
