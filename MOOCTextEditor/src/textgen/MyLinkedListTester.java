/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;
import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		try {			
			list1.remove(-1);			
			fail("Check the lower bounds.");
		} catch (IndexOutOfBoundsException e) {
		}
		try {
			list1.remove(50);
			fail("check the upper bounds");
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
		}
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// TODO: Add more tests here
		assertEquals("Check if prev pointer is allocated ", list1.head.next.data, list1.get(0));
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		try {
			list1.add(null);
		} catch (NullPointerException e) {
			// TODO: handle exception
		}
		int sizeBefore = list1.size;
		list1.add(120);
		int sizeAfter = list1.size;
		assertEquals("Test added is at the end ", list1.tail.prev.data, (Integer) 120);
		assertEquals("Test size has increased by one.", sizeBefore, sizeAfter - 1);
		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		assertEquals("Test size(): ", list1.size, list1.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		try {			
			list1.add(-1, 126);			
			fail("Check the lower bounds.");
		} catch (IndexOutOfBoundsException e) {
		}
		try {
			list1.add(10, 178);
			fail("check the upper bounds");
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
		}
		try {
			list1.add(0, null);
			fail("check null pointer");
		} catch (NullPointerException e) {
			// TODO: handle exception
		}
		int sizeBefore = list1.size;
		list1.add(1, 567);
		int sizeAfter = list1.size;
		assertEquals("Test size increases by 1 ", sizeBefore + 1, sizeAfter);
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		try {
			list1.set(-3, 1024);
			fail("check lower bound");
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
		}
		try {
			list1.set(56, 1024);
			fail("check upper bound");
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
		}
		try {
			list1.set(0, null);
		} catch (NullPointerException e) {
			// TODO: handle exception
		}
		list1.set(1, 1024);	
		assertEquals("Test 1024 is added at correct position ", list1.get(1), (Integer)1024);
	    
	}
	
	
	// TODO: Optionally add more test methods.
	
}
