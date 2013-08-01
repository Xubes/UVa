import java.util.*;
import java.io.*;
public class Vacation {

	static int[][] table;
	static char[] sc1,sc2;
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int number = 1;
		String s1 = in.readLine();
		while(!s1.equalsIgnoreCase("#")){
			String s2 = in.readLine();
			sc1 = s1.toCharArray();
			sc2 = s2.toCharArray();
			
			resetTable(s1.length(),s2.length());
			
			for(int i=1; i<=s1.length(); i++){
				for(int j=1; j<=s2.length(); j++){
					int maxsub = Math.max(table[i-1][j], table[i][j-1]);
					if(sc1[i-1]==sc2[j-1]) // minus 1 since we are 1-indexing i,j
						maxsub = Math.max(maxsub, table[i-1][j-1]+1);
					table[i][j] = maxsub;
				}
			}
			System.out.printf("Case #%d: you can visit at most %d cities.\n",number++,table[s1.length()][s2.length()]);
			s1 = in.readLine();
		}
	}
	
	static void resetTable(int s1, int s2){
		table = new int[s1+1][s2+1];
		for(int[] a : table){
			Arrays.fill(a,0);
		}
	}
}
