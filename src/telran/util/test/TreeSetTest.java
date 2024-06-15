package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.TreeSet;


public class TreeSetTest extends SortedSetTest {
	TreeSet<Integer> treeSet;
	@Override
	@BeforeEach
	void setUp() {
		collection = new TreeSet<>(); 
		super.setUp();
		treeSet = (TreeSet<Integer>) collection;
	}
	@Test
	void displayRootChildrenTest() {
		treeSet.displayRootChildren();
	}
	@Test
	void treeInversionTest() {
		treeSet.treeInversion();
		Integer[] expected = {100, 10, 1, -5,  -20};
		Integer[] actual = new Integer[treeSet.size()];
		int index = 0;
		for(Integer num: treeSet) {
			actual[index++] = num;
		}
		assertArrayEquals(expected, actual);
		assertTrue(treeSet.contains(100));
	}
	@Test
	void displayTreeRotatedTest() {
		treeSet.setSpacesPerLevel(4);
		treeSet.displayTreeRotated();
	}
	@Test
	void widthTest() {
		assertEquals(2, treeSet.width());
		
	}
	@Test
	void heightTest() {
		assertEquals(4, treeSet.height());
	}
}