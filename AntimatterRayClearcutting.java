import java.util.*;
public class AntimatterRayClearcutting {

	static int[] memo = new int[(1<<16)]; // # holds number of cuts needed to clear trees.
	static Point[] pts;
	static int state, n, m;
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		Arrays.fill(memo, -1);
		for(int i=0; i<16; i++){
			memo[1<<i] = 1;
		}
		memo[0] = 0;
		
		int sets = in.nextInt();
		for(int i=1; i<=sets; i++){
			n = in.nextInt();
			m = in.nextInt();
			
			state = (1<<n)-1;
			
			pts = new Point[n];
			for(int j=0; j<n; j++){
				pts[j] = new Point(in.nextDouble(),in.nextDouble());
			}
			
			findCut(m);
			System.out.println(memo[0]);
		}
	}
	
	/* Returns the minimum cuts needed to get chop down the specified number of trees. */
	static int findCut(int toCut){
		if(toCut<=0)
			return 0;
		if(memo[state]==-1){
			
			int t1,t2;
			int minCuts = Integer.MAX_VALUE/4;
			for(t1=0; t1<n; t1++){
				int bitoff = state&(~(1<<t1));
				if( bitoff >= state) continue;
				for(t2=t1+1; t2<n; t2++){
					bitoff = state&(~(1<<t2));
					if( bitoff >=state) continue;
					else{
						int diff = linearCut(t1,t2);
						minCuts = Math.min(minCuts, 1+findCut(toCut-diff));
					}
				}
					
			}
			memo[state] = minCuts;
		}
		
		return memo[state];
		
	}
	
	// find all the colinear pts to the given two trees and chop them down from state bitmask
	static int linearCut(int ptr1, int ptr2){
		Point p1 = pts[ptr1];
		Point p2 = pts[ptr2];
		//define the slope as a vector to avoid division by zero when using slope-intercept formula
		int trees = Integer.bitCount(state);
		double slope = (p2.y-p1.y)/(p2.x-p1.x);
		int mask = ~( (1<<ptr1) | (1<<ptr2) );
		state &= mask;
		//int treesCut = 2;
		for(int idx=0; idx<pts.length; idx++){
			Point p3 = pts[idx];
			double nslope = (p2.y-p3.y)/(p2.x-p3.x);
			boolean vertical = (slope==Double.NEGATIVE_INFINITY || slope==Double.POSITIVE_INFINITY) &&
								(nslope==Double.NEGATIVE_INFINITY || nslope==Double.POSITIVE_INFINITY);
			
			if( vertical || slope==nslope ){ // check if new point conforms to slope
				mask = ~(1<<idx);
				state &= mask;
				//treesCut++;
			}
		}
		return trees-Integer.bitCount(state);
	}
	
	/*static double gcd(double a, double b){
		if(b>a){
			double tmp = a;
			a = b;
			b = tmp;
		}
		while(b!=0){
			double r = a%b;
			a = b;
			b = r;
		}
		return a;
	}*/
}

class Point{
	double x,y;
	
	public Point(double _x, double _y){
		x = _x;
		y = _y;
	}
	
	public String toString(){
		return x+","+y;
	}
}
