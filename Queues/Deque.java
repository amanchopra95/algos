import java.util.Iterator;


public class Deque<Item> implements Iterable<Item> {
	
	private int size;
	private final Node header, trailer;
	
	private class Node {
		public Item item;
		public Node next;
		public Node prev;
	};
	
	public Deque() {
		header = new Node();
		trailer = new Node();
		header.next = trailer;
		trailer.prev = header;
		trailer.next = null;
		header.prev = null;
		size = 0;
	}
	
	private void check(Item item) {
		if (item == null) {
			throw new java.lang.IllegalArgumentException();
		}
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	public void addFirst(Item item) {
		check(item);
		Node first = new Node();
		first.prev = header;
		first.item = item;
		if (isEmpty()) {
			first.next = trailer;
			trailer.prev = first;
		}
		
		else {
			first.next = header.next;
			first.next.prev = first;
		}
		
		header.next = first;
		size++;
	}
	
	public void addLast(Item item) {
		check(item);
		Node last = new Node();
		last.next = trailer;
		last.item = item;
		if (isEmpty()) {
			last.prev = header;
			header.next = last;
		}
		
		else {
			last.prev = trailer.prev;
			trailer.prev.next = last;
		}
		
		trailer.prev = last;
		size++;
	}
	

	public Item removeFirst() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		Node first = header.next;
		Item x = first.item;
		
		if (size == 1) {
			header.next = trailer;
			trailer.prev = header;
		}
		else {
			header.next = first.next;
			first.next.prev = header;
			first.prev = null;
			first.next = null;
		}
		size--;
		return x;
	}
	
	public Item removeLast() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		
		Item x = trailer.prev.item;
		Node last = trailer.prev;
		
		if (size == 1) {
			trailer.prev = header;
			header.next = trailer;
		}
		else {
			trailer.prev = last.prev;
			last.prev.next = trailer;
			last.next = null;
			last.prev = null;
		}
		size--;
		return x;
	}
	
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}
	
	private class DequeIterator implements Iterator<Item> {
		private Node current;
		public DequeIterator() {
			if (header == trailer) {
				current = null;
			}
			current = header.next;	
		}
		public boolean hasNext() {
			return current != null && current != trailer;
		}
		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}
		public Item next() {
			if (!hasNext()) {
				throw new java.util.NoSuchElementException();
			}
			Item item = current.item;
			current = current.next;
			return item;
		}
	};
	
	public static void main(String[] args) {
		Deque<Integer> deq = new Deque<Integer>();
		deq.addFirst(4);
		deq.addLast(10);
		deq.addFirst(2);
		for (Integer i:deq) {
			System.out.print(" "+ i);
		}
		System.out.println(" size "+deq.size());
		System.out.println("Removal First "+deq.removeFirst());
		System.out.println("Removal Last"+deq.removeLast());
		System.out.println("Size "+deq.size());
		for (Integer i:deq) {
			System.out.println(" "+i);
		}
	}
	
}
