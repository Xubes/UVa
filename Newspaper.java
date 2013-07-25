import java.util.*;
import java.io.*;
public class Newspaper {

	public static void main(String[] args) throws IOException{
		
		//Scanner in = new Scanner(new BufferedInputStream(System.in));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in),10000);
		
		int sets = Integer.parseInt(in.readLine());
		for(int i=1; i<= sets; i++){
			int maps = Integer.parseInt(in.readLine());
			HashMap<Character,Integer> valueMap = new HashMap<Character,Integer>(maps);
			HashMap<Character,Integer> countsMap = new HashMap<Character,Integer>(maps);
			for(int j=0; j<maps; j++){
				String[] toks = in.readLine().split(" ");
				char c = toks[0].charAt(0);
				int v = Integer.parseInt(toks[1]);
				valueMap.put(c, v);
				countsMap.put(c, 0);
			}
			
			int lines = Integer.parseInt(in.readLine());
			for(int j=0; j<lines; j++){
				String nl = in.readLine();
				for(char c : nl.toCharArray()){
					if(countsMap.containsKey(c)){
						countsMap.put(c, countsMap.get(c)+1);
					}
				}
			}
			
			double payout = 0.0;
			for(Map.Entry<Character, Integer> ent : valueMap.entrySet()){
				payout+= ((double)(ent.getValue() * countsMap.get(ent.getKey())) )/100.0;
			}
			System.out.printf("%.2f$\n", payout);
		}
	}
}
