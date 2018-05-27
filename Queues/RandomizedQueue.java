import java.util.Iterator;

// import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] items;
	private int size;

	public RandomizedQueue() {
		items = (Item[]) new Object[2];
		size = 0;
	}
	
	private void resize(int capacity) {
		Item[] temp = (Item[]) new Object[capacity];
		for (int i = 0; i < size; i++) {
			temp[i] = items[i];
		}
		items = temp;
		
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	public void enqueue(Item item) {
		if (item == null) throw new java.lang.IllegalArgumentException();
		if (size == items.length) resize(2*size);
		items[size++] = item;
	}
	
	public Item dequeue() {
		if (isEmpty()) throw new java.util.NoSuchElementException();
		int randomIndex = StdRandom.uniform(0, size);
		Item temp = items[randomIndex];
		
		items[randomIndex] = items[size-1];
		items[size-1] = null;
		size--;
		
		if (size > 0 && size == items.length/4) resize(items.length/2);
		return temp;
	}
	
	public Item sample() {
		if (isEmpty()) throw new java.util.NoSuchElementException();
		int randomIndex = StdRandom.uniform(size);
		return items[randomIndex];
	}
	
	public Iterator<Item> iterator() {
		return new RandomIterator();
	}
	
	private class RandomIterator implements Iterator<Item> {
		private int i = 0;
		private Item[] iter;
		
		public RandomIterator() {
			iter = (Item[]) new Object[items.length];
			for (int i = 0; i < items.length; i++) {
				iter[i] = items[i];
			}
			StdRandom.shuffle(iter);
		}
		public boolean hasNext() {
			return i < size;
		}
		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}
		public Item next() {
			if (!hasNext()) throw new java.util.NoSuchElementException();
			return iter[i++];
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
		rq.enqueue(2);
		rq.enqueue(4);
		rq.enqueue(6);
		rq.enqueue(5);
		for (Integer i : rq) {
			StdOut.print(" " + i + " ");
		}
		StdOut.println();
		int n = rq.size();
		for (int i = 0; i < n; i++) {
			StdOut.print(rq.dequeue());
		}
		
	}

}
