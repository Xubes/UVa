import java.util.*;
public class DateBugs {

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		
		int pn=1;
		int numLines = in.nextInt();
		while(numLines!=0){
			int maxa = 0;
			HashSet[] setList = new HashSet[numLines];
			for(int i=0; i<numLines; i++){
				int y = in.nextInt();
				int a = in.nextInt();
				int b = in.nextInt();
				if(a>maxa) maxa=a;
				int skip = (Math.abs(b-a)==136)? 136:100;
				setList[i] = new HashSet<Integer>(10000);
				for(int j=y; j<10000; j+=skip){
					setList[i].add(j);
				}
			}
			System.out.printf("Case #%d:\n", pn++);
			if(true){
				System.out.println("The actual year is "+".");
			}
			else{
				System.out.println("Unknown bugs detected.");
			}
		}
	}
}
