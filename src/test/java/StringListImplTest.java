import org.junit.jupiter.api.*;
import ru.verlonar.Exception.ElementIsNotExistException;
import ru.verlonar.StringList;
import ru.verlonar.StringListImpl;

public class StringListImplTest {

    static StringList out = new StringListImpl(10);

    @BeforeAll
    static void completeTheList() {
        out.add("Item at index 0");
        out.add("Item at index 1");
        out.add("Item at index 2");
        out.add("Item at index 3");
        out.add("Item at index 4");
        out.add("Item at index 5");
    }

    @Test
    public void shouldAddNewItem() {
        String itemToAdd = "New item";
        String result = out.add(itemToAdd);
        Assertions.assertEquals(itemToAdd, result);
    }

    @Test
    public void shouldAddNewItemToIndex() {
        String itemToAdd = "New item at index 3";
        String result = out.add(3, itemToAdd);
        Assertions.assertEquals(itemToAdd, result);
    }

    @Test
    public void shouldThrowIndexOfBoundExceptionWhenAddNewItemToIndex() {
        Assertions.assertThrows(IndexOutOfBoundsException.class,() -> out.add(11, ""));
    }

    @Test
    public void shouldSetItemToIndex() {
        String itemToSet = "New item at index 1";
        String result = out.set(1, itemToSet);
        Assertions.assertEquals(itemToSet, result);
    }

    @Test
    public void shouldThrowIndexOfBoundExceptionWhenSetItemToIndex() {
        Assertions.assertThrows(IndexOutOfBoundsException.class,() -> out.set(11, ""));
    }

    @Test
    public void shouldRemoveItem() {
        String itemToRemove = "Item at index 3";
        String result = out.remove(itemToRemove);
        Assertions.assertEquals(itemToRemove, result);
    }
    @Test
    public void shouldThrowNullPointerExceptionWhenRemoveItem() {
        Assertions.assertThrows(NullPointerException.class,() -> out.remove(null));
    }

    @Test
    public void shouldThrowElementIsNotExistExceptionWhenRemoveItem() {
        Assertions.assertThrows(ElementIsNotExistException.class,() -> out.remove("Item at index 3"));
    }

    @Test
    public void shouldRemoveItemByIndex() {
        String itemToRemove = "New item at index 3";
        String result = out.remove(3);
        Assertions.assertEquals(itemToRemove, result);
    }

    @Test
    public void shouldThrowIndexOutOfBoundsExceptionWhenRemoveItemByIndex() {
        Assertions.assertThrows(IndexOutOfBoundsException.class,() -> out.remove(8));
    }

    @Test
    public void shouldReturnTrueFromContainsMethod() {
        Assertions.assertTrue(out.contains("New item at index 1"));
    }

    @Test
    public void shouldReturnFalseFromContainsMethod() {
        Assertions.assertFalse(out.contains("False item"));
    }

    @Test
    public void shouldReturnTrueIndexOfItem() {
        Assertions.assertEquals(1, out.indexOf("New item at index 1"));
    }

    @Test
    public void shouldReturnFalseIndexOfItem() {
        Assertions.assertEquals(-1, out.indexOf("False item"));
    }

    @Test
    public void shouldReturnTrueLastIndexOfItem() {
        out.add("Item at index 0");
        Assertions.assertEquals(6, out.lastIndexOf("Item at index 0"));
    }

    @Test
    public void shouldReturnFalseLastIndexOfItem() {
        Assertions.assertEquals(-1, out.lastIndexOf("False item"));
    }

    @Test
    public void shouldGetItemByIndex() {
        String itemToGet = "Item at index 1";
        String result = out.get(1);
        Assertions.assertEquals(itemToGet, result);
    }

    @Test
    public void shouldThrowIndexOfBoundExceptionWhenGetItemByIndex() {
        Assertions.assertThrows(IndexOutOfBoundsException.class,() -> out.get(11));
    }

    @Test
    public void shouldReturnTrueWhenEquals() {
        StringList listToEquals = out;
        Assertions.assertTrue(out.equals(listToEquals));
    }

    @Test
    public void shouldReturnFalseWhenEqualsBecauseSize() {
        StringList listToEquals = new StringListImpl(10);
        listToEquals.add("New item");
        Assertions.assertFalse(out.equals(listToEquals));
    }

    @Test
    public void shouldReturnFalseWhenEquals() {
        StringList listToEquals = new StringListImpl(10);
        listToEquals.add("New item");
        Assertions.assertFalse(out.equals(listToEquals));
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenEquals() {
        Assertions.assertThrows(NullPointerException.class, () -> out.equals(null));
    }

    @Test
    public void shouldReturnTrueSize() {
        Assertions.assertEquals(7, out.size());
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
