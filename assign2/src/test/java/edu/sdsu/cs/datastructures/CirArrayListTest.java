package edu.sdsu.cs.datastructures;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Unit test for simple App.
 */
public class CirArrayListTest extends TestCase {

    private static final int testSize = 2048;
    private static final int valueInvalid = -1;
    private static final int valueValid = 310;

    private List<Integer> sut;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public CirArrayListTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(CirArrayListTest.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        sut = new CirArrayList<>();
    }

    public void test_constructorDefault_initializedCorrectly() {
        assertThat(sut.isEmpty(), is(equalTo(true)));
        assertThat(sut.size(), is(equalTo(0)));
    }

    public void test_constructorCollection_initializedCorrectly() {

        final Integer[] values = getSequentialIntArray(testSize);
        sut = new CirArrayList<>(Arrays.asList(values));

        assertThat(sut.isEmpty(), is(equalTo(false)));
        assertThat(sut.size(), is(equalTo(values.length)));
        assertThat(sut.containsAll(Arrays.asList(values)), is(equalTo(true)));
    }

    private Integer[] getSequentialIntArray(int size) {
        Integer[] values = new Integer[size];
        for (int i = 0; i < size; i++)
            values[i] = i;

        return values;
    }

    public void test_add_startEmptyEndPosition_contentsCorrect() {
        Integer[] values = getSequentialIntArray(testSize);
        for (int position = 0; position < values.length; position++) {
            sut.add(position, values[position]);
        }

        assertThat(sut.size(), is(equalTo(values.length)));
        for (int position = 0; position < values.length; position++) {
            assertThat(sut.get(position), is(equalTo(values[position])));
        }
    }

    public void test_add_startEmptyFrontPosition_contentsCorrect() {
        Integer[] values = getSequentialIntArray(testSize);
        for (int position = 0; position < values.length; position++) {
            sut.add(0, values[position]);
        }

        assertThat(sut.size(), is(equalTo(values.length)));
        Arrays.sort(values, Comparator.reverseOrder());
        for (int position = 0; position < values.length; position++) {
            assertThat(sut.get(position), is(equalTo(values[position])));
        }
    }

    public void test_add_existingCollectionFrontPosition_contentsCorrect() {
        Integer[] values = getSequentialIntArray(testSize);
        sut = new CirArrayList(Arrays.asList(values));
        for (int value = testSize - 1; value >= 0; value--) {
            sut.add(0, value);
        }

        assertThat(sut.size(), is(equalTo(testSize << 1)));
        for (int position = 0; position < testSize; position++) {
            assertThat(sut.get(position), is(equalTo(position)));
            assertThat(sut.get(position + testSize), is(equalTo(position)));
        }
    }

    public void test_add_manySingleItems_correctContents() {

        final Integer inv = valueInvalid;

        sut.add(valueInvalid);
        for (int i = 0; i < Integer.MAX_VALUE >> 6; i++) {
            sut.add(inv);
            sut.remove(0);
        }
        sut.remove(0);
        sut.add(valueValid);

        assertThat(sut.size(), is(equalTo(1)));
        assertThat(sut.contains(valueValid), is(equalTo(true)));
        assertThat(sut.contains(valueInvalid), is(equalTo(false)));
    }

    public void test_remove_addTwoBackRemoveOneFront_contentsCorrect() {
        int counter = 0;
        for (int cycle = 0; cycle < testSize; cycle++) {
            sut.add(counter++);
            sut.add(counter++);
            sut.remove(0);
        }

        assertThat(sut.size(), is(equalTo(testSize)));
        for (int i = 0; i < testSize; i++) {
            assertThat(sut.contains(i), is(equalTo(false)));
            assertThat(sut.get(i), is(equalTo(testSize + i)));
        }
    }

    public void test_remove_allContents_correctRemovedElementAndSize() {
        sut = new CirArrayList<>(Arrays.asList(getSequentialIntArray
                (testSize)));

        for (int count = 0; count < testSize; count++) {
            assertThat(sut.remove(0), is(equalTo(count)));
            assertThat(sut.contains(count), is(equalTo(false)));
            assertThat(sut.size(), is(equalTo(testSize - count - 1)));
        }
    }

    public void test_set_sequentialValues_contentsCorrect() {
        sut = new CirArrayList<>(Arrays.asList(getInvalidInitializedIntArray
                (testSize)));
        for (int count = 0; count < testSize; count++) {
            sut.set(count, count);
        }

        assertThat(sut.size(), is(equalTo(testSize)));
        for (int count = 0; count < testSize; count++) {
            assertThat(sut.get(count), is(equalTo(count)));
        }
    }

    private Integer[] getInvalidInitializedIntArray(int size) {
        Integer[] values = new Integer[size];
        for (int i = 0; i < size; i++)
            values[i] = valueInvalid;

        return values;
    }

    public void test_remove_oddValuesFromList_nonePresent() {
        sut = new CirArrayList<>(Arrays.asList(getSequentialIntArray
                (testSize)));

        sut.removeIf(integer -> integer % 2 == 1);

        for (int i = 0; i < testSize; i += 2) {
            assertThat(sut.contains(i), is(equalTo(true)));
            assertThat(sut.contains(i + 1), is(equalTo(false)));
        }
    }

    public void test_clear_middleLeavingEnds_onlyValidItems() {
        sut = new CirArrayList<>(Arrays.asList(getInvalidInitializedIntArray
                (testSize)));
        sut.set(0, valueValid);
        sut.set(sut.size() - 1, valueValid);

        assertThat(sut.contains(valueInvalid), is(equalTo(true)));
        sut.subList(1, sut.size() - 1).clear();
        assertThat(sut.contains(valueInvalid), is(equalTo(false)));
        assertThat(sut.size(), is(equalTo(2)));
    }
}
