package p01_Database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.assertEquals;

public class DatabaseTest {
    private static final Integer[] ELEMENTS = new Integer[]{4,6,19,32,5};
    private Database database;
    @Before
    public void prepareDatabase() throws OperationNotSupportedException {
      database = new Database(ELEMENTS);
    }

    @Test
    public void when_correctElementsArePassed_then_createDatabaseInstance() throws OperationNotSupportedException {
        assertEquals(ELEMENTS.length, database.getElements().length);
        Assert.assertArrayEquals(ELEMENTS, database.getElements());
    }
    @Test(expected = OperationNotSupportedException.class)
    public void when_elementsMoreThan16PassedToCtor_then_exceptionThrowen() throws OperationNotSupportedException {
        new Database(new Integer[17]);
    }
    @Test(expected = OperationNotSupportedException.class)
    public void whenElementsLessThanPassedToCtor_then_exceptionIsThrown() throws OperationNotSupportedException {
        new Database();
    }
    @Test
    public void when_validElementPassedToAdd_then_elementIsAddedOnLastPosition() throws OperationNotSupportedException {
        int element = 15;
        database.add(element);
        Integer[] databaseElements = database.getElements();
        assertEquals(ELEMENTS.length + 1,database.getElements().length);
        assertEquals(Integer.valueOf(element), databaseElements[databaseElements.length - 1]);
    }
    @Test (expected = OperationNotSupportedException.class)
    public void when_invalidElementPassedToAdd_then_exceptionIsThrown() throws OperationNotSupportedException {
        database.add(null);
    }
    @Test
    public void when_remove_then_lastElementIsRemoved() throws OperationNotSupportedException {
        database.remove();
        Integer[] databaseElements = database.getElements();
        assertEquals(ELEMENTS.length - 1, database.getElements().length);
        assertEquals(ELEMENTS[ELEMENTS.length - 2],databaseElements[databaseElements.length - 1]);
    }
    @Test(expected = OperationNotSupportedException.class)
    public void when_elementsIsEmptyAndRemove_then_exceptionIsThrown() throws OperationNotSupportedException {
        database = new Database(new Integer[1]);
        database.remove();
        database.remove();
    }



}
