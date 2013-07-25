import java.util.*;
public class CamelTrading {

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		
		int sets = Integer.parseInt(in.nextLine());
		for(int i=1; i<=sets; i++){			
			String exp = in.nextLine();
			String[] operands = exp.split("[+*]");
			String[] operators = exp.replaceAll("[^+*]+"," ").trim().split(" ");
			
			//assert(operands.length == operators.length+1);
			
			Stack<Long> minStack = new Stack<Long>();
			Stack<Long> maxStack = new Stack<Long>();
			int operandPtr = 0;
			long val = Long.parseLong(operands[operandPtr]);
			minStack.add(val);
			maxStack.add(val);
			for(String str : operators){
				operandPtr++;
				val = Long.parseLong(operands[operandPtr]);
				switch(str.charAt(0)){
				case '+':
					maxStack.push(val+maxStack.pop());
					minStack.push(val);
					break;
				case '*':
					minStack.push(val*minStack.pop());
					maxStack.push(val);
					break;
				}
			}
		
			long min = minStack.pop();
			for(long x : minStack)
				min+=x;
			long max = maxStack.pop();
			for(long x : maxStack)
				max*=x;
			System.out.printf("The maximum and minimum are %d and %d.\n", max,min);
		}
	}
}
