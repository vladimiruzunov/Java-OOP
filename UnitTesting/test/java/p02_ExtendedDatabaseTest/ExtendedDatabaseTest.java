package p02_ExtendedDatabaseTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import p02_ExtendedDatabase.Database;
import p02_ExtendedDatabase.Person;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.assertEquals;

public class ExtendedDatabaseTest {
    private static final Person[] PEOPLE = new Person[]{
            new Person(1, "A"),
            new Person(2, "B"),
            new Person(3, "C"),
    };
    private p02_ExtendedDatabase.Database database;

    @Before
    public void prepareDatabase() throws OperationNotSupportedException {
        database = new Database(PEOPLE);
    }

    @Test
    public void when_correctElementsArePassed_then_createDatabaseInstance() throws OperationNotSupportedException {
        assertEquals(PEOPLE.length, database.getElements().length);
        Assert.assertArrayEquals(PEOPLE, database.getElements());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void when_elementsMoreThan16PassedToCtor_then_exceptionThrown() throws OperationNotSupportedException {
        new Database(new Person[17]);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void whenElementsLessThanPassedToCtor_then_exceptionIsThrown() throws OperationNotSupportedException {
        new Database();
    }

    @Test
    public void when_validElementPassedToAdd_then_elementIsAddedOnLastPosition() throws OperationNotSupportedException {
        Person expectedPerson = new Person(4, "D");
        database.add(expectedPerson);
        Person[] databaseElements = database.getElements();
        assertEquals(PEOPLE.length + 1, database.getElements().length);
        Person actualPerson = databaseElements[databaseElements.length - 1];
        assertEquals(expectedPerson.getId(), actualPerson.getId());
        assertEquals(expectedPerson.getUsername(), actualPerson.getUsername());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void when_invalidElementPassedToAdd_then_exceptionIsThrown() throws OperationNotSupportedException {
        database.add(null);
    }

    @Test
    public void when_remove_then_lastElementIsRemoved() throws OperationNotSupportedException {
        database.remove();
        Person[] databaseElements = database.getElements();
        assertEquals(PEOPLE.length - 1, database.getElements().length);
        Person expectedPerson = PEOPLE[PEOPLE.length - 2];
        Person actualPerson = databaseElements[databaseElements.length - 1];
        assertEquals(expectedPerson.getId(), actualPerson.getId());
        assertEquals(expectedPerson.getUsername(), actualPerson.getUsername());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void when_elementsIsEmptyAndRemove_then_exceptionIsThrown() throws OperationNotSupportedException {
        database = new Database(new Person(4, "G"));
        database.remove();
        database.remove();
    }

    @Test(expected = OperationNotSupportedException.class)
    public void when_usernameNullPassedToFindByUsername_then_exceptionIsThrown() throws OperationNotSupportedException {
        database.findByUsername(null);
    }

    @Test
    public void when_validUsernamePassedToFindUsername_then_returnPerson() throws OperationNotSupportedException {
        Person actualPerson = database.findByUsername("B");
        assertEquals(PEOPLE[1].getId(), actualPerson.getId());
        assertEquals(PEOPLE[1].getUsername(), actualPerson.getUsername());
    }
    @Test(expected = OperationNotSupportedException.class)
    public void when_findByUsernameOnEmptyArray_then_exceptionIsThrown() throws OperationNotSupportedException {
        database.remove();
        database.remove();
        database.remove();
        database.findByUsername("A");
    }
    @Test(expected = OperationNotSupportedException.class)
    public void when_findPersonByIdOnEmptyArrays_then_exceptionIsThrown() throws OperationNotSupportedException {
        database.remove();
        database.remove();
        database.remove();
        database.findById(2);
    }
    @Test
    public void when_validIdPassed_then_returnPerson() throws OperationNotSupportedException {
        Person actualPerson = database.findById(2);
        assertEquals(PEOPLE[1].getId(),actualPerson.getId());
        assertEquals(PEOPLE[1].getUsername(),actualPerson.getUsername());
    }

}
