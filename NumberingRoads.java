import java.util.*;
import java.io.*;
public class NumberingRoads {

	public static void main(String[] args) throws IOException{
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//Scanner in = new Scanner(System.in);
		int set = 1;
		int r,n;
		String[] line = in.readLine().trim().split(" ");
		r = Integer.parseInt(line[0]);
		n = Integer.parseInt(line[1]);
		//r = in.nextInt();
		//n = in.nextInt();
		while(!(r==0 && n==0)){
			int ans = ((r-n)/n) + 1;
			if(ans <= 26){
				System.out.printf("Case %d: %d\n", set++,ans);
			}
			else{
				System.out.printf("Case %d: impossible\n", set++);
			}
			line = in.readLine().trim().split(" ");
			r = Integer.parseInt(line[0]);
			n = Integer.parseInt(line[1]);
			//r = in.nextInt();
			//n = in.nextInt();
		}
	}
}
