import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;


public class IsBiggerSmarter {

	public static void main(String[] args){
		ArrayList<Elephant> eles = new ArrayList<Elephant>();
		Scanner in = new Scanner(System.in);
	
		int idx=1;
		while(in.hasNextInt()){
			eles.add(new Elephant(in.nextInt(),in.nextInt(),idx++));
		}
		Collections.sort(eles);
		
		/*for(Elephant e : eles)
			System.err.println(e);*/
		
		Elephant[] eleArr = {};
		eleArr = eles.toArray(eleArr);
		
		int[] table = new int[eleArr.length];
		int[] prev = new int[eleArr.length];
		int endpt = 0;
		
		for(int i=0; i<eleArr.length; i++){
			table[i] = 1;
			prev[i] = -1;
			for(int j=i-1; j>0; j--){
				int iiq = eleArr[i].iq;
				int iw = eleArr[i].weight;
				if(eleArr[j].iq > iiq && eleArr[j].weight<iw){
					if(table[j]+1 > table[i]){
						table[i] = table[j]+1;
						prev[i] = j;
					}
				}
			}
			if(table[i] > table[endpt]){
				endpt = i;
			}
		}
		//System.err.println(Arrays.toString(table));
		int maxSub = table[endpt];
		System.out.println(maxSub);
		String listStr = "";
		for(; maxSub>0; maxSub--){
			listStr = eleArr[endpt].id + "\n" + listStr;
			endpt = prev[endpt];	
		}
		System.out.println(listStr);
	}
}

class Elephant implements Comparable<Elephant>{
	int weight;
	int iq;
	int id;
	
	public Elephant(int w, int iq, int id){
		weight = w;
		this.iq = iq;
		this.id = id;
	}
	
	public int compareTo(Elephant e){
		if(weight>e.weight)
			return 1;
		else if(weight<e.weight)
			return -1;
		else if(iq>e.iq)
			return -1;
		else if(iq<e.iq)
			return 1;
		return 0;
	}
	
	public String toString(){
		return weight+","+iq+","+id;
	}
}
