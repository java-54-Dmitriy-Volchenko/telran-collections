package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

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
		int currentIndex = 0;
		@Override
		public boolean hasNext() {
			return currentIndex < size;
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			return array[currentIndex++];
		}
		
	}
	@Override
	/**
	 * adds object at end of array, that is, at index == size
	 */
	public boolean add(T obj) {
		allocateIfNeeded();
		array[size++] = obj;
		return true;
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
		return new ArrayListIterator();
	}

	@Override
	public T get(int index) {
		List.checkIndex(index, size, true);
		return array[index];
	}

	@Override
	public void add(int index, T obj) {
		List.checkIndex(index, size, false);
		allocateIfNeeded();
		System.arraycopy(array, index, array, index + 1, size - index);
		array[index] = obj;
		size++;
	}

	private void allocateIfNeeded() {
		if(size == array.length) {
			array = Arrays.copyOf(array, array.length * 2);
		}
		
	}
	@Override
	public T remove(int index) {
		List.checkIndex(index, size, true);
		T res = array[index];
		System.arraycopy(array, index + 1, array, index, size - index - 1);
		array[--size] = null;
		return res;
	}

	@Override
	public int indexOf(T pattern) {
		int index = 0;
		while(index < size && !Objects.equals(array[index], pattern)) {
			index++;
		}
		return index < size ? index : -1;
	}

	@Override
	public int lastIndexOf(T pattern) {
		int index = size - 1;
		while(index >= 0 && !Objects.equals(array[index], pattern)) {
			index--;
		}
		return index;
	}

}