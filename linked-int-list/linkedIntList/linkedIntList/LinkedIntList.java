package linkedIntList;

public class LinkedIntList {
	private ListNode first; //to point to the first node of the list
	private ListNode last; //to point to the last node of the list
	public int size = 0; //to save the length of the list
	
	public void add(int value) { //method to add node at the end
		if (first==null) { //no nodes yet
			first = new ListNode(value); //create new node
			last = first; //first node is also last node, next and previous are still null
		} else {
			ListNode temp = last; //temp is the old last node. The reason we need to save this node is to set the previous field of our new last node
			last = new ListNode(value); //create new last node
			last.previous = temp; //set previous for last node
			temp.next = last; //set next for second last node
		}
		size++; //increment size of list
	} //end add
	
	public void add(int index, int value) { //method to add node at given index
		if (index!=0 && index!=size && index<size) { //the node to be added won't be first or last
			ListNode current = first;
			for (int i=0; i<index-1; i++) {
				current = current.next;
			} //current is now the node before the new node
			ListNode temp = current.next; //save the node current points to so it doesn't get lost
			current.next = new ListNode(value); //create new node after current
			current.next.previous = current; //set previous of new node to current
			current.next.next = temp; //set next of new node to the node which was previously at this index
		} else if (index==0) { //if the new node is the new first
			ListNode temp = first; //save the old first
			first = new ListNode(value); //create new node as first
			first.next = temp; //set next of new node to old first
			temp.previous = first; //set previous of old first to new node
		} else if (index<0) { // fail save for IndexMismatchException
			size--; //decrement size so that nothing happens
		} else { //if the new node is the new last
			add(value); //use add method which we already defined to add new node at the end
		}
		size++; //increment size
	} //end add
	
	public void print() { //method to print our list
		if (first!=null) { //if the list isn't empty
			ListNode current = first; //use current to iterate over list without losing entries
			while (current.next != null) {
				System.out.print(current.value +", "); //print the value while you iterate over the list
				current = current.next;
			}
			System.out.println(current.value); //print the last value of the list
		} else {
			System.out.println("List is empty.");
		}
	} //end print
	
	public int get(int index) { //method to get value at given index
		if (index>=0 && index<size) { //check if valid index
			ListNode current = first;
			for (int i=0; i<index; i++) {
				current = current.next;
			} //current is now node with given index
			return current.value; //return value of current
		} else { //fail save
			return 0;
		}
	} //end get
	
	public int length() { //method to get length of list
		return size;
	} //end length
	
	public int pop() { //method to remove and return last node
		if (size!=0) { //if there are nodes to remove
			if (first.next == null) { //if there is only one node
				int ret = first.value; //save value which will be returned
				first = null; //reset first
				last = null; //reset last
				size--; //decrement size before return
				return ret;
			} else { //if there are more than one node
				int ret = last.value; 
				last = last.previous; //set last to be the second last node
				last.next = null; //lose the pointer to the old last node. If we omit this line, last.next will still point to the old last node.
				size--;
				return ret;
			}
		} else { //fail save
			return 0;
		}
	} //end pop
	
	public void remove(int index) { //method to remove node at given index
		if (index>=0 && index<size) { //check if valid index
			if (index == 0) { //if first should be removed
				first = first.next; //lose first
			} else {
				ListNode current = first;
				for (int i=0; i<index; i++) {
					current = current.next;
				} //current is now node with given index
				if (current != last) { //if there are following nodes
					current.next.previous = current.previous; //set previous pointer of the node after the node to be removed
					current.previous.next = current.next; //set next pointer of the node before the node to be removed
				} else { //same as pop but without return
					last = current.previous;
					last.next = null;
				}
			}
			size--; //decrement size
		}
	} //end remove
	
	public void set(int index, int value) { //method to set value at given index
		if (index>=0 && index<size) { //check if valid index
			ListNode current = first;
			for (int i=0; i<index; i++) {
				current = current.next;
			} //current is now node with given index
			current.value = value; //set new value
		}
	} //end set
	
	public void clear() { //method to clear all entries in the list
		while (first != null) {
			first = first.next; //iterate over list, lose nodes
		}
		last = null; //last still points to a node, so this is lost as well
	} //end clear
	
	public int indexOf(int value) { //method to get left-most index of value
		ListNode current = first; 
		boolean found = false; //boolean for checking if the value is in the list
		int index = 0; //index which will be returned
		if (size>0) { //if there are nodes
			while (current != null) { //go through all nodes until you find a match
				if (current.value == value) { //check if match
					found = true;
					break; //end while loop
				}
				current = current.next;
				index++; //increment index before you check the next node
			} 
			if (!found) { //if the value isn't in the list return -1
				return -1; 
			} else {
				return index;
			}
		} else { //fail save if the list is empty
			return -1;
		}
	} //end indexOf
	
	public LinkedIntList allIndicesOf(int value) { //a method to return all indices of a given value
		ListNode current = first;
		boolean found = false; //boolean for checking if the value is in the list
		LinkedIntList indices = new LinkedIntList(); //indices which will be returned as a LinkedIntList
		int index = 0; //counter
		if (size>0) { //if there are nodes
			while (current != null) { //go through all nodes
				if (current.value == value) { //check if match
					found = true;
					indices.add(index); //add to indices list
				}
				current = current.next;
				index++; //increment index before you check the next node
			} 
			if (!found) { //if the value isn't in the list
				indices.add(-1);
				return indices;
			} else {
				return indices;
			}
		} else { //fail save if list is empty
			indices.add(-1);
			return indices;
		}
	} //end allIndicesOf
	
//	public MyIntSuper allIndexOfInt(int value) { /*My goal was to create a method which can return an integer as well as an integer array.
//	As Java has no superclass for these 2 types, I created my own interface and integer as well as integer list which implement the interface. */
//		LinkedIntList results = allIndicesOf(value);
//		if (results.length()>1) {
//			MyIntList ret = new MyIntList(results.length());
//			for (int i=0; i<results.length(); i++) {
//				ret.set(i,results.get(i));
//			}
//			return ret;
//		} else if (results.length()==0) {
//			MyInt ret = new MyInt(-1);
//			return ret;
//		} else {
//			MyInt ret = new MyInt(results.get(0));
//			return ret;
//		}
//	} //end allIndexOfInt
} //end LinkedIntList