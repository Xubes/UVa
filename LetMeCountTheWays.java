import java.util.*;
public class LetMeCountTheWays {

	//columns are the denominations from greatest to smallest
	static long memo[][] = new long[30001][5];
	static int denoms[] = {50,25,10,5,1};
	
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		while(in.hasNextInt()){
			int change = in.nextInt();
			long ways = makeChange(change,0);
			if(ways>1){
				System.out.printf("There are %d ways to produce %d cents change.\n",ways,change);
			}
			else
				System.out.printf("There is only 1 way to produce %d cents change.\n", change);
		}
	}
	
	static long makeChange(int amt, int denom){
		if(amt<0 || denom>=denoms.length)
			return 0;
		else if(amt==0)
			return 1;
		if(memo[amt][denom]==0){
			int remaining = amt-denoms[denom];
			long paths = makeChange(remaining,denom) + makeChange(amt,denom+1);
			memo[amt][denom]=paths;
		}
		return memo[amt][denom];
	}
}
