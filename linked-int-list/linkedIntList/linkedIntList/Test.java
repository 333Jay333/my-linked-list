package linkedIntList;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedIntList list = new LinkedIntList();
		list.print();
		list.add(4);
		list.print();
		list.add(8);
		list.add(-1);
		list.print();
		System.out.println("Length: "+list.length());
		System.out.println(list.get(2));
		list.print();
		System.out.println(list.pop());
		list.print();
		System.out.println(list.pop());
		list.print();
		System.out.println(list.pop());
		list.print();
		System.out.println(list.pop());
		list.add(1);
		list.add(2);
		list.add(3);
		list.print();
		list.remove(2);
		list.print();
		list.add(4);
		list.add(6);
		list.print();
		list.add(5,3);
		list.print();
		list.add(0,0);
		list.print();
		list.add(3,3);
		list.print();
		list.add(7,7);
		list.print();
		list.set(42, 2);
		list.print();
		System.out.println(list.indexOf(7));
		list.clear();
		list.add(1);
		list.add(3);
		list.add(1);
		list.print();
		(list.allIndicesOf(1)).print();
	}

}
