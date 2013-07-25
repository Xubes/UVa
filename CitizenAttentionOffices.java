import java.util.*;
public class CitizenAttentionOffices {

	static int[] city = new int[25];
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		
		int cases = in.nextInt();
		for(int i=1; i<=cases; i++){
			Arrays.fill(city, 0);
			int lines = in.nextInt();
			for(int j=1; j<=lines; j++){
				int r = in.nextInt();
				int c = in.nextInt();
				int p = in.nextInt();
				int idx = r*5 + c;
				city[idx] = p;
			}
			
			int[] sols = new int[5];
			int min = Integer.MAX_VALUE;
			for(int a=0; a<21; a++){
				for(int b=a+1; b<22; b++){
					for(int c=b+1; c<23; c++){
						for(int d=c+1; d<24; d++){
							for(int e=d+1; e<25; e++){
								int dist = calcDist(a,b,c,d,e);
								if(dist<min){
									min = dist;
									sols[0] = a;
									sols[1] = b;
									sols[2] = c;
									sols[3] = d;
									sols[4] = e;
									//System.err.println(dist);
								}
								if(dist==0){
									a=25;
									b=25;
									c=25;
									d=25;
									e=25;
									break;
								}
							}
						}
					}
				}
			}
			
			System.out.printf("%d %d %d %d %d\n", sols[0],sols[1],sols[2],sols[3],sols[4]);
			
		}
	}
	static int calcDist(int a, int b, int c, int d, int e){
		int totDist = 0;
		for(int i=0; i<25; i++){
			int da = getDist(i,a);
			int db = getDist(i,b);
			int dc = getDist(i,c);
			int dd = getDist(i,d);
			int de = getDist(i,e);
			
			//totDist+= p*(da+db+dc+dd+de);
			totDist+= city[i]*Math.min(da, Math.min(db,Math.min(dc, Math.min(dd,de))));
		}
		return totDist;
	}
	
	static int getDist(int a, int b){
		int ra = a/5;
		int ca = a%5;
		int rb = b/5;
		int cb = b%5;
		return Math.abs(ra-rb)+Math.abs(ca-cb);
	}
}
