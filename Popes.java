import java.util.*;
public class Popes {

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int y = in.nextInt();
		int numPopes = in.nextInt();
		int[] years = new int[numPopes];
		for(int i=0; i<numPopes; i++){
			years[i] = in.nextInt();
		}
		int[] diffs = new int[numPopes-1];
		int maxPopes = 1, cmax=0;
		int isize = 0;
		int msy = years[0], mey=years[1];
		int csy = years[0];
		for(int i=0; i<numPopes-1; i++){
			diffs[i] = years[i+1]-years[i];
			isize+=diffs[i];
			cmax++;
			if(isize>=y){
				if(cmax>maxPopes){
					maxPopes=cmax;
					msy = csy;
					mey=years[i+1];
				}
				isize=diffs[i];
				csy = years[i+1];
				cmax=1;
			}
		}
		
		System.out.printf("%d %d %d\n", maxPopes,msy,mey);
		
	}
}
