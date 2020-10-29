package stack;

import java.util.ArrayList;

public class StackUsingAL{ //ArrayList를 친구한테 부탁하는 디자인
	
	private ArrayList<Integer> al = new ArrayList<Integer>();
	
	public void push(int x) {
		al.add(x);
	}
	
	public int pop() {
		return al.remove(al.size()-1);
	}

}
