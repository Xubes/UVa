import java.util.*;
class ListOfConquests {

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		in.useDelimiter("\n");
		TreeMap<String,Integer> table = new TreeMap<String,Integer>();
		
		int sets = in.nextInt();
		for(int i=1; i<=sets; i++){
			String nc = in.next().split(" ")[0].trim();
			if(table.containsKey((nc))){
				int lastAmt = table.get(nc);
				table.put(nc,++lastAmt);
			}
			else{
				table.put(nc, 1);
			}
		}
		
		for(Map.Entry<String, Integer> ent : table.entrySet()){
			System.out.printf("%s %d\n", ent.getKey(),ent.getValue());
		}
		
	}
}
