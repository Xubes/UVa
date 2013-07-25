import java.io.*;
import java.util.*;
public class Lotto {

	static int[] set;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int k= in.nextInt();
		while(k!=0){
			set = new int[k];
			for(int i=0; i<k; i++){
				set[i] = in.nextInt();
			}
			
			Arrays.sort(set);
			generate(k,6,"");

			/*nt maskLast = (1<<k)-1;
			int mask=Integer.parseInt("111111",2);
			while(mask<=maskLast){
				if(Integer.bitCount(mask)==6){
					printSet(mask);
				}
				mask++;
			}*/
			k=in.nextInt();
			if(k!=0)
				System.out.println();
		}
	}
	
	static void printSet(int mask){
		int idx = 0;
		while(mask>0){
			if(mask==1){
				System.out.println(set[idx]);
			}
			else if((mask&1)==1){
				System.out.print(set[idx] + " ");
			}
			idx++;
			mask>>>=1;
		}
	}
	
	static void generate(int n, int r, String s){
		if(n<r)
			return;
		if(n==r){
			while(r-- >0){
				s = "1"+s;
			}
			printSet(Integer.parseInt(s,2));
		}
		else if(r==0){
			while(n-- > 0){
				s ="0"+s;
			}
			printSet(Integer.parseInt(s,2));
		}
		else{
			generate(n-1,r-1,"1"+s);
			generate(n-1,r,"0"+s);			
		}
	}

}
