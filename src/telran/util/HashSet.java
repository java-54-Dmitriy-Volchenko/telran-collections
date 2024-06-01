package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class HashSet<T> implements Set<T> {
	private static final int DEFAULT_HASH_TABLE_LENGTH = 16;
	private static final float DEFAULT_FACTOR = 0.75f;
	List<T> [] hashTable;
	int size;
	float factor;
	
	
	//constructor
		public HashSet(int hashTableLength, float factor) {
			hashTable = new List[hashTableLength];
			this.factor = factor;
		}
	//default constructor
		public HashSet() {
			this(DEFAULT_HASH_TABLE_LENGTH, DEFAULT_FACTOR);
		}
	
		//iterator
	private class HashSetIterator implements Iterator<T> {		
		private int index;
		private Iterator<T> listIterator;

		public HashSetIterator() {
			advanceIndex();// Initializing iterator with the FIRST not null and not empty linkedList
		}
		
		private void advanceIndex() {
			while (index < hashTable.length &&
				(hashTable[index] == null || hashTable[index].size()==0)) {
				index++;//while index not more then length and it is null or it not contains any element in it - move to next index
			}
			if (index < hashTable.length) {// if that index not more then length list iterator is iterator of linkedList in bucket with this index
				listIterator = hashTable[index].iterator();
			}
		}
		
		@Override
		public boolean hasNext() {
			boolean hasNext = true;
			if(listIterator == null || !listIterator.hasNext()) {//if in current linkedList not more elements
				index++; //...go to next element of array
				advanceIndex();// checks, if next element not null and assign to variable listIterator value of element under this index
				hasNext = listIterator != null && listIterator.hasNext();//if that list Iterator has next element - hasNext - true 
			}
			return hasNext;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T nextElement = listIterator.next();
			if (!listIterator.hasNext()) {//
				index++;
				advanceIndex();
			}
			return nextElement;
		}
		
	}
	
	@Override
	public Iterator<T> iterator() {
		
		return new HashSetIterator();
	}
//alternative variant of iterator 1
	private class HashSetIterator1 implements Iterator<T> {
		Iterator<T>[] iterators = getIterators();//using method to put iterators of all lists to array of iterators
		
		int iteratorIndex = findIteratorIndex(0);
		@Override
		public boolean hasNext() {
			
			return iteratorIndex < iterators.length;
		}

		private int findIteratorIndex(int index) {
			while(index < iterators.length &&
					(iterators[index] == null || !iterators[index].hasNext())) {
				index++;
			}
			return index;
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			T res = iterators[iteratorIndex].next();
			iteratorIndex = findIteratorIndex(iteratorIndex);
			return res;
		}
		private Iterator<T>[] getIterators() {
			return Arrays.stream(hashTable).map(l ->
			l == null ? null : l.iterator()).toArray(Iterator[]::new);
		}
		
	}
//alternative variant of iterator 2 - simplest one
	private class HashSetIterator2 implements Iterator<T> {
		private java.util.List<T> objects  = getObjects(hashTable);
		private int index;

		private java.util.List<T> getObjects(List<T> [] hashTable) {//making simple list from all objects in hashTable using stream API
			return (java.util.List<T>) Arrays.stream(hashTable)
					.filter(Objects::nonNull)
					.flatMap(List::stream)
					.collect(Collectors.toList());
		}

		@Override
		public boolean hasNext() { //simple hasNext
			return index < size;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return objects.get(index++); 
		}
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
		List<T> [] otherTable = new List[hashTable.length * 2];
		for(List<T> list: hashTable) {
			if(list != null) {
				for(T obj: list) {
					addObjInHashTable(obj, otherTable);
				}
			}
		}
		hashTable = otherTable;
		
	}
	private void addObjInHashTable(T obj, List<T> [] lists) {
		int index = getIndex(obj, lists);
		List<T> list = lists[index];
		if(list == null) {
			list = new LinkedList<>();
			lists[index] = list;
		}
		list.add(obj);
		
	}
	private int getIndex(T obj, List<T> [] lists) {
		int hashCode = obj.hashCode();
		int index = Math.abs(hashCode % lists.length);
		return index;
	}
	@Override
	public boolean remove(T pattern) {
		boolean res = contains(pattern);//checking, whether such element is in hashSet
		if(res) {//if it is - getting this element from bucket
			int index = getIndex(pattern, hashTable);
			hashTable[index].remove(pattern);//removes element with said pattern from list with said index
			size--;
		}
		return res;
	}

	@Override
	public boolean contains(T pattern) {
		int index = getIndex(pattern, hashTable);
		List<T> list = hashTable[index];
		return list != null && list.contains(pattern);
	}

	@Override
	public int size() {
		
		return size;
	}

	

	@Override
	public T get(T pattern) {//uses method get of LinkedList
		int index = getIndex(pattern, hashTable);
		T res = null;
		List<T> list = hashTable[index];
		if (list != null) {
			int lIndex = list.indexOf(pattern);
			if(lIndex > -1) {
				res = list.get(lIndex);
			}
		}
		return res;
	}
	
	// variant of method get using iterator
	public T get1(T pattern) {
		Iterator<T> it = iterator();
		T res = null;
		while(it.hasNext() && res == null) {
			T tmp = it.next();
			if(Objects.equals(tmp, pattern)) {
				res = tmp;
			}
		}
		return res;
	}

}
