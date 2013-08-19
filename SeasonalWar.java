import java.util.*;
import java.io.*;
public class SeasonalWar {
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int set = 1;
		String next = null;
		while((next=in.readLine()) != null){
			int sz = Integer.parseInt(next);
			char[][] img = new char[sz+2][sz+2]; // pad it to avoid index out of bounds checks
			Arrays.fill(img[0], '0'); // fill first and last with 0s
			Arrays.fill(img[sz+1], '0');
			
			for(int i=1; i<=sz; i++){
				String row = "0" + in.readLine().trim() + "0";
				img[i] = row.toCharArray();
			}
			int eagles = 0;
			for(int i=1; i<=sz; i++){
				for(int j=1; j<=sz; j++){
					if(img[i][j] == '1'){
						if(img[i-1][j]!='1' && img[i-1][j-1]!='1' && img[i][j-1]!='1' && img[i+1][j-1]!='1')
							eagles++;
					}
				}
			}
			
			System.out.printf("Image number %d contains %d war eagles.\n", set++,eagles);
		}
	}
}
