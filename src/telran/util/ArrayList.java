package telran.util;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<T> implements List<T> {
	private static final int DEFAULT_CAPACITY = 16;
	private int size;
	private T[] array;
	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		array = (T[]) new Object[capacity];
	}
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}
	private class ArrayListIterator implements Iterator<T> {

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	@Override
	/**
	 * adds object at end of array, that is, at index == size
	 */
	public boolean add(T obj) {
		if (size == array.length) {
			allocate();
		}
		array[size++] = obj;
		return true;
	}

	private void allocate() {
		array = Arrays.copyOf(array, array.length * 2);
		
	}
	@Override
	public boolean remove(T pattern) {
		int index = indexOf(pattern);
		boolean res = false;
		if (index > -1) {
			res = true;
			remove(index);
		}
		return res;
	}

	@Override
	public boolean contains(T pattern) {
		
		return indexOf(pattern) > -1;
	}

	@Override
	public int size() {
		
		return size;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(int index, T obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public T remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(T pattern) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lastIndexOf(T pattern) {
		// TODO Auto-generated method stub
		return 0;
	}

}
