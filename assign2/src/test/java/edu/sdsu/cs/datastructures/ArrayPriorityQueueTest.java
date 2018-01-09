package edu.sdsu.cs.datastructures;

import edu.sdsu.cs.util.IValueGenerator;
import edu.sdsu.cs.util.NameGenerator;
import junit.framework.TestCase;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Example unit tests: We will flesh these out in class ...
 */
public class ArrayPriorityQueueTest extends TestCase {

    private static final int DEFAULT_TEST_SIZE = 8086;
    private static final int DEFAULT_NUM_EPOCH = 4;
    private static final List<String> TEST_NAMES = generateTestValues
            (DEFAULT_TEST_SIZE << DEFAULT_NUM_EPOCH, new NameGenerator());

    private Queue<String> sut;

    private static List<String> generateTestValues(int count,
                                                   IValueGenerator<String>
                                                           generator) {
        List<String> testVals = new java.util.ArrayList<>();
        for (int num = 0; num < count; num++) {
            testVals.add(generator.generate());
        }
        return testVals;
    }

    public void setUp() throws Exception {
        super.setUp();
        sut = new ArrayPriorityQueue<>();
    }

    public void test_constructorDefault_correctInitialValues() {
        assertThat(sut.size(), is(0));
        assertNull(sut.peek());
    }

    public void test_constructorCollection_correctInitialValues() {
        sut = new ArrayPriorityQueue<>(TEST_NAMES.subList(0,
                DEFAULT_TEST_SIZE));
        assertThat(sut.size(), is(DEFAULT_TEST_SIZE));
    }

    public void test_pollFromInitiallyEmpty_exception() {
        try {
            sut.poll();
            fail("NoSuchElementException expected.");
        } catch (NoSuchElementException e) {
            assertTrue(true);
        }
    }

    public void test_pollPastEmpty_exception() {
        sut.offer("INVALID"); // Size now 1
        sut.poll();             // Size back to 0

        try {
            sut.poll();
            fail("NoSuchElementException expected.");
        } catch (NoSuchElementException e) {
            assertTrue(true);
        }
    }

    public void test_elementFromInitiallyEmpty_exception() {
        try {
            sut.element();
            fail("NoSuchElementException expected.");
        } catch (NoSuchElementException e) {
            assertTrue(true);
        }
    }

    public void test_offer_containsAllItems() {

        for (String item : TEST_NAMES.subList(0, DEFAULT_TEST_SIZE)) {
            sut.offer(item);
        }

        assertThat(sut.size(), is(DEFAULT_TEST_SIZE));
        assertTrue(sut.containsAll(TEST_NAMES.subList(0, DEFAULT_TEST_SIZE)));
    }
}