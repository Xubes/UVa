import java.io.*;
import java.util.*;
public class GeorgeLucasAnd1138 {

	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String nl;
		while((nl=in.readLine().trim())!="0"){
			char[] chardig = nl.toCharArray();
			int[] digits = new int[chardig.length];
			for(int i=0; i<chardig.length; i++){
				digits[i] = 9-chardig[i];
			}
		}
	}
}
