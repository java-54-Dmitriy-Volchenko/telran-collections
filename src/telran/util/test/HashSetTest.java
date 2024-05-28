package telran.util.test;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;

import telran.util.HashSet;

public class HashSetTest extends SetTest {
@Override
@BeforeEach
void setUp() {
	collection = new HashSet<>(3, 0.75f); //for testing Hash Table re-creation
	super.setUp();
}
@Override
protected void runTest(Integer[] expected) {
	//since iterating of HashSet is done at not defined order
	//actual and expected arrays should be sorted
	Integer[]actual = collection.stream().sorted().toArray(Integer[]::new);
	Arrays.sort(expected);
	assertArrayEquals(expected, actual);
}
}