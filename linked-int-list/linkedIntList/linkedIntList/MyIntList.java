package linkedIntList;

public class MyIntList implements MyIntSuper {
	int[] myIntList;
	
	public MyIntList(int length) {
		myIntList = new int[length];
	}
	
	public void set(int index, int value) {
		myIntList[index] = value;
	}
}
