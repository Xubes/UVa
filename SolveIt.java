import java.util.*;
public class SolveIt {

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			int p = in.nextInt();
			int q = in.nextInt();
			int r = in.nextInt();
			int s = in.nextInt();
			int t = in.nextInt();
			int u = in.nextInt();
			
			double start = 0.0;
			double end = 1.0;
			
			while(eval((start+end)/2))
		}
	}
	
	public double eval(double x,int p, int q, int r, int s, int t, int u){
		double val = (double)p*Math.exp(-x) + (double)q * Math.sin(x) + (double)r*Math.cos(x) + (double)s*Math.tan(x) + (double)t*(x*x) + (double)u;
		return val;
	}
}
