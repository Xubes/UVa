import java.util.*;
public class HorrorDash {

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		
		int sets = in.nextInt();
		for(int i=1; i<=sets; i++){
			int runners = in.nextInt();
			int[] speeds = new int[runners];
			for(int j=0; j<runners; j++){
				speeds[j] = in.nextInt();
			}
			Arrays.sort(speeds);
			System.out.printf("Case %d: %d\n",i,speeds[runners-1]);
		}
	}
}
