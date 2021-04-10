import java.io.IOException;


public class Main {

	public static void main(String[] args) throws IOException {
		char c1 = '1';
		char c2 = 'a';
		char c3 = 'A';
		
		System.out.println("1 is Upper case? -> " + Character.isUpperCase(c1));
		System.out.println("a is Upper case? -> " + Character.isUpperCase(c2));
		System.out.println("A is Upper case? -> " + Character.isUpperCase(c3));
		
		System.out.println();
		
		System.out.println("1 is Lower case? -> " + Character.isLowerCase(c1));
		System.out.println("a is Lower case? -> " + Character.isLowerCase(c2));
		System.out.println("A is Lower case? -> " + Character.isLowerCase(c3));
		
		System.out.println();
		
		System.out.println("1 convert Upper case -> " + Character.toUpperCase(c1));
		System.out.println("a convert Upper case -> " + Character.toUpperCase(c2));
		System.out.println("A convert Upper case -> " + Character.toUpperCase(c3));
		
		System.out.println();
		
		System.out.println("1 convert Lower case -> " + Character.toLowerCase(c1));
		System.out.println("a convert Lower case -> " + Character.toLowerCase(c2));
		System.out.println("A convert Lower case -> " + Character.toLowerCase(c3));
	}

}
