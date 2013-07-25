import java.util.*;
public class CuttingSticks {

	static int[] cutLocs;
	public static void Main(String[] args){
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			int length = in.nextInt();
			int cuts = in.nextInt();
			cutLocs = new int[cuts];
			for(int i=0; i<cuts; i++){
				cutLocs[i] = in.nextInt();
			}
			
			for(int i: cutLocs){
				solve(0,length);
		}
	}
	
	static int minCost(int start, int end, locs){
		if(locs.size()==0)
			return 0;
		int cost = 0;
		
	}
}
