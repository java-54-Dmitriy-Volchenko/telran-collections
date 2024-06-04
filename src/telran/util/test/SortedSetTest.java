package telran.util.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import telran.util.SortedSet;


class SortedSetTest extends SetTest {

	SortedSet<Integer> set;
	@Override
	void setUp() {
		super.setUp();
		set = (SortedSet<Integer>)collection;
	}
	@Override
	protected void runTest(Integer[] expected) {
		//since iterating of SortedSet is done at sorted order (according to a Comparator)
		//expected array should be sorted
		Integer[]actual = collection.stream().toArray(Integer[]::new);
		Arrays.sort(expected);
		assertArrayEquals(expected, actual);
	}
	@Test
	void firstTest() {
		assertEquals(-20, set.first());
	}
	@Test
	void lastTest() {
		assertEquals(100, set.last());
	}

}
