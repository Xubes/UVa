import java.math.BigInteger;
import java.util.*;

public class AlmostPrimeNumbers {

	static BigInteger[] apn;

	public static void main(String[] args){
		init();
		System.err.println("init finished");
		Scanner in = new Scanner(System.in);
		int sets = in.nextInt();
		for(int i=1; i<=sets; i++){
			int min = in.nextInt();
			int max = in.nextInt();
			
			int minIdx = Arrays.binarySearch(apn, new BigInteger(Integer.toString(min)));
			int maxIdx = Arrays.binarySearch(apn, new BigInteger(Integer.toString(max)));
			if(minIdx<0)
				minIdx = -(minIdx+1);
			if(maxIdx<0)
				maxIdx = -(maxIdx+1) - 1;
			
			int ans = maxIdx-minIdx;
			System.out.println(ans);
			
			
		}
	}
	
	static void init(){

	}
	
}
