import java.util.*;
public class MotherBear {

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		
		while(in.hasNextLine()){
			String nl = in.nextLine();
			if(nl.equalsIgnoreCase("DONE"))
				break;
			nl = nl.replaceAll("[ .,!?]", "").trim().toLowerCase();
			
			String firstHalf = nl.substring(0,nl.length()/2);
			String secondHalf = nl.substring((nl.length()+1)/2, nl.length());
			StringBuilder sb = new StringBuilder(secondHalf);
			String secondHalfReverse = sb.reverse().toString();

			boolean isPal = firstHalf.compareTo(secondHalfReverse)==0;
			
			System.out.println((isPal)? "You won't be eaten!" : "Uh oh..");
		}
	}
}
