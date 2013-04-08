package datastructures;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> { 

	private int N;          // size of the queue
    private Node first;     // beginning of queue
    private Node last;     //  end of queue

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
    }
    
	public Queue() {
		first = null;
		last = null;
		N = 0;		
	}	
	
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public int size() {
		return N;
	}
	
	public void enqueue(Item item) {
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		
        if (isEmpty()) first = last;        
        else oldlast.next = last;
        
		N++;
	}
	
	public Item dequeue() {
		Item item = first.item;
		first = first.next;
		N--;
		if (isEmpty()) last = null;
		return item;
	}
	
	public Iterator<Item> iterator() {
		return new ListIterator();
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
