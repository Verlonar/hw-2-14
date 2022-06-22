import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.verlonar.Exception.ElementIsNotExistException;
import ru.verlonar.IntegerList;
import ru.verlonar.IntegerListImpl;


public class IntegerListImplTest {
    static IntegerList out = new IntegerListImpl(10);

    @BeforeAll
    static void completeTheList() {
        out.add(8);
        out.add(6);
        out.add(5);
        out.add(7);
        out.add(3);
        out.add(4);
        out.add(2);
    }

    @Test
    public void shouldAddNewItem() {
        Integer itemToAdd = 7;
        Integer result = out.add(itemToAdd);
        Assertions.assertEquals(itemToAdd, result);
    }

    @Test
    public void shouldAddNewItemToIndex() {
        Integer itemToAdd = 12;
        Integer result = out.add(3, itemToAdd);
        Assertions.assertEquals(itemToAdd, result);
    }

    @Test
    public void shouldThrowIndexOfBoundExceptionWhenAddNewItemToIndex() {
        Assertions.assertThrows(IndexOutOfBoundsException.class,() -> out.add(11, 15));
    }

    @Test
    public void shouldSetItemToIndex() {
        Integer itemToSet = 25;
        Integer result = out.set(1, itemToSet);
        Assertions.assertEquals(itemToSet, result);
    }

    @Test
    public void shouldThrowIndexOfBoundExceptionWhenSetItemToIndex() {
        Assertions.assertThrows(IndexOutOfBoundsException.class,() -> out.set(11, 28));
    }

    @Test
    public void shouldRemoveItem() {
        Integer itemToRemove = 8;
        Integer result = out.remove(itemToRemove);
        Assertions.assertEquals(itemToRemove, result);
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenRemoveItem() {
        Assertions.assertThrows(NullPointerException.class,() -> out.remove(null));
    }

    @Test
    public void shouldThrowElementIsNotExistExceptionWhenRemoveItem() {
        Assertions.assertThrows(ElementIsNotExistException.class,() -> out.remove(Integer.valueOf(8)));
    }

    @Test
    public void shouldRemoveItemByIndex() {
        Integer itemToRemove = 12;
        Integer result = out.remove(3);
        Assertions.assertEquals(itemToRemove, result);
    }

    @Test
    public void shouldThrowIndexOutOfBoundsExceptionWhenRemoveItemByIndex() {
        Assertions.assertThrows(IndexOutOfBoundsException.class,() -> out.remove(8));
    }

    @Test
    public void shouldReturnTrueFromContainsMethod() {
        Assertions.assertTrue(out.contains(3));
    }

    @Test
    public void shouldReturnFalseFromContainsMethod() {
        Assertions.assertFalse(out.contains(90));
    }

    @Test
    public void shouldReturnTrueIndexOfItem() {
        Assertions.assertEquals(1, out.indexOf(5));
    }

    @Test
    public void shouldReturnFalseIndexOfItem() {
        Assertions.assertEquals(-1, out.indexOf(90));
    }

    @Test
    public void shouldReturnTrueLastIndexOfItem() {
        Assertions.assertEquals(6, out.lastIndexOf(7));
    }

    @Test
    public void shouldReturnFalseLastIndexOfItem() {
        Assertions.assertEquals(-1, out.lastIndexOf(90));
    }

    @Test
    public void shouldGetItemByIndex() {
        Integer itemToGet = 6;
        Integer result = out.get(1);
        Assertions.assertEquals(itemToGet, result);
    }

    @Test
    public void shouldThrowIndexOfBoundExceptionWhenGetItemByIndex() {
        Assertions.assertThrows(IndexOutOfBoundsException.class,() -> out.get(11));
    }

    @Test
    public void shouldReturnTrueWhenEquals() {
        IntegerList listToEquals = out;
        Assertions.assertTrue(out.equals(listToEquals));
    }

    @Test
    public void shouldReturnFalseWhenEqualsBecauseSize() {
        IntegerList listToEquals = new IntegerListImpl(10);
        listToEquals.add(90);
        listToEquals.add(110);
        listToEquals.add(1111);
        Assertions.assertFalse(out.equals(listToEquals));
    }

    @Test
    public void shouldReturnFalseWhenEquals() {
        IntegerList listToEquals = new IntegerListImpl(10);
        listToEquals.add(90);
        Assertions.assertFalse(out.equals(listToEquals));
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenEquals() {
        Assertions.assertThrows(NullPointerException.class, () -> out.equals(null));
    }

    @Test
    public void shouldReturnTrueSize() {
        Assertions.assertEquals(8, out.size());
    }

    @Test
    public void shouldReturnFalseIsEmpty() {
        Assertions.assertFalse(out.isEmpty());
    }

    @Test
    public void shouldReturnTrueIsEmpty() {
        out.clear();
        Assertions.assertTrue(out.isEmpty());
    }
}
