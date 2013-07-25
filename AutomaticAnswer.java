import java.util.*;
class AutomaticAnswer {

	public static void main(String[] args){
		
		Scanner in = new Scanner(System.in);
		
		int sets = in.nextInt();
		for(int i=1; i<=sets; i++){
			long n = in.nextLong();
			long ans = (((((n*567)/9)+7492)*235)/47) - 498;
			System.out.println(Math.abs(ans/10)%10);
		}
	}
}
