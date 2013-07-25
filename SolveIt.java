import java.util.*;
public class SolveIt {

	static final double EPS = 1e-9;
	static int p,q,r,s,t,u;
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			int p = in.nextInt();
			int q = in.nextInt();
			int r = in.nextInt();
			int s = in.nextInt();
			int t = in.nextInt();
			int u = in.nextInt();
			setCoefs(p,q,r,s,t,u);
			
			/* Do bisection. */
			double start = 0.0;
			double end = 1.0;
			while(eval((start+end)/2) > EPS){
		
			}
		}
	}
	
	static void setCoefs(int pin,int qin, int rin, int sin, int tin, int uin){
		p=pin;
		q=qin;
		r=rin;
		s=sin;
		t=tin;
		u=uin;
	}
	static double eval(double x){
		double val = (double)p*Math.exp(-x) + (double)q * Math.sin(x) + (double)r*Math.cos(x) + (double)s*Math.tan(x) + (double)t*(x*x) + (double)u;
		return val;
	}
}
