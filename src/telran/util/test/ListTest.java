package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import telran.util.List;

public abstract class ListTest extends CollectionTest {
	List<Integer> list;

	@BeforeEach
	@Override
	void setUp() {
		super.setUp();
		list = (List<Integer>) collection;
	}

	@Test
	void getTest() {
		assertEquals(numbers[0], list.get(0));
		testIndexExceptions(() -> list.get(numbers.length));
		testIndexExceptions(() -> list.get(-1));
	}

	@Test
	void addIndexTest() {
		int num0 = 0, num3 = 43, numLast = 500;
		Integer[] expected0 = { 0, -20, 10, 1, 100, -5 };
		Integer[] expected0_3 = { num0, -20, 10, num3, 1, 100, -5 };
		Integer[] expected0_3_last = { num0, -20, 10, num3, 1, 100, -5, numLast };
		list.add(0, num0);
		runTest(expected0);
		list.add(3, num3);
		runTest(expected0_3);
		list.add(list.size(), numLast);
		runTest(expected0_3_last);
		testIndexExceptions(() -> list.add(100000, newNumber));
		testIndexExceptions(() -> list.add(-1, newNumber));
	}

	@Test
	void removeIndexTest() {
		Integer num0 = -20, num2 = 100, numLast = -5;

		Integer[] expected0 = { 10, 1, 100, -5 };
		Integer[] expected0_2 = { 10, 1, -5 };
		Integer[] expected0_2_last = { 10, 1 };
		assertEquals(num0, list.remove(0));
		runTest(expected0);
		assertEquals(num2, list.remove(2));
		runTest(expected0_2);
		assertEquals(numLast, list.remove(list.size() - 1));
		runTest(expected0_2_last);
		testIndexExceptions(() -> list.remove(numbers.length));
		testIndexExceptions(() -> list.remove(-1));
	}

	void testIndexExceptions(Executable executable) {
		assertThrowsExactly(IndexOutOfBoundsException.class, executable);
	}
	@Test
	void indexOfTest() {
		list.add(numbers[0]);
		assertEquals(0, list.indexOf(numbers[0]));
		assertEquals(-1, list.lastIndexOf(newNumber));
	}
	@Test
	void lastIndexOfTest() {
		list.add(numbers[0]);
		assertEquals(numbers.length, list.lastIndexOf(numbers[0]));
		assertEquals(-1, list.lastIndexOf(newNumber));
	}
	@Test
	void nullElementTest() {
		list.add(null);
		list.add(0, null);
		assertEquals(0, list.indexOf(null));
		assertEquals(list.size() - 1, list.lastIndexOf(null));
		
	}
}