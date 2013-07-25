import java.util.*;
public class LumberjackSequencing {

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		
		int sets = in.nextInt();
		System.out.println("Lumberjacks:");
		for(int i=1; i<=sets; i++){
			int[] bl = new int[10];
			for(int j=0; j<10; j++){
				bl[j] = in.nextInt();
			}
			if(checkSort(bl)){
				System.out.println("Ordered");
			}
			else{
				System.out.println("Unordered");
			}
		}
	}
	
	static boolean checkSort(int[] arr){
		boolean ordered = true;
		for(int i=1; i<arr.length; i++){
			ordered = ordered && arr[i]<=arr[i-1];
		}
		if(ordered) return ordered;
		ordered = true;
		for(int i=1; i<arr.length; i++){
			ordered = ordered && arr[i]>=arr[i-1];
		}
		return ordered;
	}
}
