package stack;

import java.util.ArrayList;

public class StackUsingAL{ //ArrayList�� ģ������ ��Ź�ϴ� ������
	
	private ArrayList<Integer> al = new ArrayList<Integer>();
	
	public void push(int x) {
		al.add(x);
	}
	
	public int pop() {
		return al.remove(al.size()-1);
	}

}
