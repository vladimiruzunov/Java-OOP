package p03_IteratorTest;

import org.junit.Assert;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class ListIteratorTest{
    @Test(expected = OperationNotSupportedException.class)
    public void when_nullElementsPassedToConstructor_thenExceptionIsThrown() throws OperationNotSupportedException {
        new ListIterator(null);
    }
    @Test
    public void when_validElementsPassedToConstructor_then_moveReturnsCorrectBool() throws OperationNotSupportedException {
        ListIterator listIterator = new ListIterator("wood", "river", "gold");
        Assert.assertTrue(listIterator.move());
        Assert.assertTrue(listIterator.move());
    }
    @Test(expected = IllegalStateException.class)
    public void when_emptyListIterator_then_exceptionIsThrown() throws OperationNotSupportedException {
        new ListIterator().print();
    }
    @Test
    public void when_elementsInListIterator_then_print() throws OperationNotSupportedException {
        String[] elements = new String[]{"wood", "river", "gold"};
        ListIterator listIterator = new ListIterator();
        for (int i = 0; listIterator.hasNext();listIterator.move(),i++){
            Assert.assertEquals(elements[i],listIterator.print());
        }
    }
}