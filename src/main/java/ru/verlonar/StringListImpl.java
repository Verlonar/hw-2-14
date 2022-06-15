package ru.verlonar;

import ru.verlonar.Exception.ElementIsNotExistException;

import java.util.Arrays;

public class StringListImpl implements StringList {

    private String[] stringList;
    int currentSize;

    public StringListImpl(int length) {
        this.stringList = new String[length];
        currentSize = 0;
    }

    @Override
    public String add(String item) {
        isFull();
        stringList[currentSize++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        isItemExist(item);
        isIndexInBounds(index);
        shiftRightFromIndex(index);
        stringList[index] = item;
        currentSize++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        isItemExist(item);
        isIndexInBounds(index);
        stringList[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        isItemExist(item);
        int i = indexOf(item);
        if (i == -1) {
            throw new ElementIsNotExistException();
        }
        String itemToDelete = stringList[i];
        stringList[i] = null;
        shiftLeftFromIndex(i);
        currentSize--;
        return itemToDelete;
    }

    @Override
    public String remove(int index) {
        isIndexInBounds(index);
        String itemToDelete = stringList[index];
        stringList[index] = null;
        shiftLeftFromIndex(index);
        currentSize--;
        return itemToDelete;
    }

    @Override
    public boolean contains(String item) {
        isItemExist(item);
        for (int i = 0; i < currentSize; i++) {
            if (stringList[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        isItemExist(item);
        for (int i = 0; i < currentSize; i++) {
            if (stringList[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        isItemExist(item);
        for (int i = currentSize - 1; i >= 0; i--) {
            if (stringList[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        isIndexInBounds(index);
        return stringList[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        isItemExist(otherList);
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public void clear() {
        stringList = new String[10];
        currentSize = 0;
    }

    @Override
    public String[] toArray() {
        return stringList;
    }

    private void expandList() {
        String[] newStrings = new String[stringList.length * 2 + 1];
        if (currentSize >= 0) System.arraycopy(stringList, 0, newStrings, 0, currentSize);
        stringList = newStrings;
    }

    private void isIndexInBounds(int index) {
        if (index >= currentSize) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void isItemExist(Object item) {
        if (item == null) {
            throw new NullPointerException();
        }
    }

    private void shiftLeftFromIndex(int index) {
        if (currentSize - index >= 0) System.arraycopy(stringList, index + 1, stringList, index, currentSize - index);
    }

    private void shiftRightFromIndex(int index) {
        isFull();
        if (currentSize - index >= 0) System.arraycopy(stringList, index, stringList, index + 1, currentSize - index);
    }

    private void isFull() {
        if (currentSize >= stringList.length) {
            expandList();
        }
    }
}