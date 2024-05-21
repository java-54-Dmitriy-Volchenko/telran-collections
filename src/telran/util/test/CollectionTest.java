package telran.util.test;

import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Collection;

public abstract class CollectionTest {
	protected Collection<Integer> collection;
	Integer[] numbers = {-20, 10, 1, 100, -5};
	
	@BeforeEach
	void setUp() {
		for(Integer num: numbers) {
			collection.add(num);
		}
	}
	@Test
	void iteratorTest() {
		runTest(numbers);
	}
	protected void runTest(Integer[] expected) {
		Integer [] actual = collection.stream().toArray(Integer[]::new);
		assertArrayEquals(expected, actual);
	};
	//TODO tests of all methods for interface Collection (see the interface Collection)
}