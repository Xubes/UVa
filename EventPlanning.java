import java.util.*;
public class EventPlanning {

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		
		while(in.hasNextInt()){
			int n=in.nextInt();
			int b=in.nextInt();
			int h=in.nextInt();
			int w=in.nextInt();
			
			int minCost = Integer.MAX_VALUE;
			for(int i=1; i<=h; i++){
				int price = in.nextInt();
				int totCost = n*price;
				if(totCost < minCost){
					for(int j=1; j<=w; j++){
						int avail = in.nextInt();
						if(avail>=n){
							minCost = totCost;
							break;
						}
					}
					in.nextLine();
				}
				else{
					in.nextLine();
				}
			}
			System.out.println((minCost<=b)? minCost : "stay home");
		}
	}
}
