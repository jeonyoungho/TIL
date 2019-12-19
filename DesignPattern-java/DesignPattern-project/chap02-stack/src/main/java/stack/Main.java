package stack;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StackUsingAL st = new StackUsingAL();

		st.push(1);
		st.push(2);
		st.push(3);

		System.out.println(st.pop());
		
		//st.set(1, 20);
		
		System.out.println(st.pop());

	}

}
