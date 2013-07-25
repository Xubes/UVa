import java.util.*;
public class SplittingNumbers {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		while(in.hasNextInt()){
			int n = in.nextInt();
			if(n==0)
				break;
			int aMask = 0;
			boolean odd = true;
			int mask = 1;
			while(mask!=0){
				if((n&mask) > 0){
					if(odd){
						aMask = aMask|mask;
					}
					odd=!odd;
				}
				mask<<=1;
			}
			int a = n&aMask;
			int b = n&(~aMask);
			System.out.printf("%d %d\n", a,b);
			
		}
	}
}
