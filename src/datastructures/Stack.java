package datastructures;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item> { 

	private int N;          // size of the stack
    private Node first;     // beginning of stack

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
    }
    
	public Stack() {
		first = null;
		N = 0;		
	}	
		
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public int size() {
		return N;
	}
	
	public void push(Item item) {
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		
		N++;
	}
	
	public Item pop() {
		Item item = first.item;
		first = first.next;
		N--;		
		return item;
	}
	
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	public boolean contains(Item item) {
		for (Item it: this) {
			if (it.equals(item)) return true;
		}
		
		return false;
	}
	
    private class ListIterator implements Iterator<Item> {
    	private Node current = first;

    	public boolean hasNext() { return current != null;						}
    	public void remove() 	 { throw new UnsupportedOperationException();   }
		
		public Item next() { 
			if (!hasNext()) throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
    }
}
