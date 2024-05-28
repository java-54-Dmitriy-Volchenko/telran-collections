package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")

public class HashSet<T> implements Set<T> {
	private static final int DEFAULT_HASH_TABLE_LENGTH = 16;
	private static final float DEFAULT_FACTOR = 0.75f;
	List<T> [] hashTable;
	int size;
	float factor;
	
		private class HashSetIterator implements Iterator<T> {
		    int currentIndex = 0;
		    Iterator<T> currentIterator = null;
		   
	
		    @Override
		    public boolean hasNext() {
		    
	
		        while (currentIndex < hashTable.length && (currentIterator==null||!currentIterator.hasNext())) {
		            List<T>list=hashTable[currentIndex++];
		            if(list!=null) {
		            	currentIterator=list.iterator();
		            }
		        }
	
		        return currentIterator!=null && currentIterator.hasNext();
		    }
	
		   
	
		    @Override
		    public T next() {
		        if (!hasNext()) {
		            throw new NoSuchElementException();
		        }
		        return currentIterator.next();
		    }
		}


	public HashSet(int hashTableLength, float factor) {
		hashTable = new List[hashTableLength];
		this.factor = factor;
	}
	public HashSet() {
		this(DEFAULT_HASH_TABLE_LENGTH, DEFAULT_FACTOR);
	}
	@Override
	public boolean add(T obj) {
		boolean res = false;
		if(!contains(obj)) {
			if((float)size / hashTable.length >= factor) {
				hashTableReallocation();
			}
			addObjInHashTable(obj, hashTable);
			size++;
			res = true;
		}
		return res;
	}

	private void hashTableReallocation() {
		List<T>[] tmp = new List[hashTable.length * 2];
		for(List<T> list: hashTable) {
			if(list != null) {
				for(T obj: list) {
					addObjInHashTable(obj, tmp);
				}
			}
		}
		hashTable = tmp;
	}

	private void addObjInHashTable(T obj, List<T>[] table) {
		int index = getIndex(obj);
		List<T> list = table[index];
		if (list == null) {
			list = new LinkedList<>();
			table[index] = list;
		}
		list.add(obj);
	}

	private int getIndex(T obj) {
		int hashCode = obj.hashCode();
		int index = Math.abs(hashCode % hashTable.length);
		return index;
	}

	@Override
	public boolean remove(T pattern) {
		int index = getIndex(pattern);
		boolean res = false;
		List<T> list = hashTable[index];
		if (list != null && list.remove(pattern)) {
			size--;
			res = true;
		}
		return res;
	}

	@Override
	public boolean contains(T pattern) {
		int index = getIndex(pattern);
		List<T> list = hashTable[index];
		return list != null && list.contains(pattern);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<T> iterator() {
		return new HashSetIterator();
	}

	@Override
	public T get(T pattern) {
		int index = getIndex(pattern);
		List<T> list = hashTable[index];
		if (list != null) {
			for (T element : list) {
				if (element.equals(pattern)) {
					return element;
				}
			}
		}
		return null;
	}
}
