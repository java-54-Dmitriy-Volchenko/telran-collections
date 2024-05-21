package telran.util;

public interface List<T> extends Collection<T> {
	/**
	 * 
	 * @param index
	 * @return reference to an object at a given index
	 * throws IndexOutOfBoundsException for either index < 0 or index >= size()
	 */
	T get(int index);
	/******************************/
	/**
	 * 
	 * @param index
	 * @param obj
	 * adds obj at a given index
	 * throws exception for index < 0 or index > size()
	 */
	void add(int index, T obj);
	/****************************************/
	/**
	 * 
	 * @param index
	 * @return reference to a removed object
	 * throws IndexOutOfBoundsException for either index < 0 or index >= size()
	 */
	T remove(int index);
	/******************************************************/
	/**
	 * 
	 * @param pattern
	 * @return index for first occurrence of an object equaled to a given pattern
	 * otherwise -1
	 */
	int indexOf(T pattern) ;
	/******************************************************/
	/**
	 * 
	 * @param pattern
	 * @return index for last occurrence of an object equaled to a given pattern
	 * otherwise -1
	 */
	int lastIndexOf(T pattern) ;
}
