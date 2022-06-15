package ru.verlonar;

import ru.verlonar.Exception.ElementIsNotExistException;

import java.util.Arrays;
import java.util.Objects;

public class IntegerListImpl implements IntegerList {

    private Integer[] integerList;
    int currentSize;

    public IntegerListImpl(int length) {
        this.integerList = new Integer[length];
        currentSize = 0;
    }

    @Override
    public Integer add(Integer item) {
        isFull();
        integerList[currentSize++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        isItemExist(item);
        isIndexInBounds(index);
        shiftRightFromIndex(index);
        integerList[index] = item;
        currentSize++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        isItemExist(item);
        isIndexInBounds(index);
        integerList[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        isItemExist(item);
        int i = indexOf(item);
        if (i == -1) {
            throw new ElementIsNotExistException();
        }
        Integer itemToDelete = integerList[i];
        integerList[i] = null;
        shiftLeftFromIndex(i);
        currentSize--;
        return itemToDelete;
    }

    @Override
    public Integer remove(int index) {
        isIndexInBounds(index);
        Integer itemToDelete = integerList[index];
        integerList[index] = null;
        shiftLeftFromIndex(index);
        currentSize--;
        return itemToDelete;
    }

    @Override
    public boolean contains(Integer item) {
        isItemExist(item);
        Integer[] listToSort = Arrays.copyOf(integerList, currentSize);
        sort(listToSort);
        return binarySearch(listToSort, item);
    }

    @Override
    public int indexOf(Integer item) {
        isItemExist(item);
        for (int i = 0; i < currentSize; i++) {
            if (integerList[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        isItemExist(item);
        for (int i = currentSize - 1; i >= 0; i--) {
            if (integerList[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        isIndexInBounds(index);
        return integerList[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
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
        integerList = new Integer[10];
        currentSize = 0;
    }

    @Override
    public Integer[] toArray() {
        return integerList;
    }

    private void expandList() {
        Integer[] newIntegers = new Integer[integerList.length * 2 + 1];
        if (currentSize >= 0) System.arraycopy(integerList, 0, newIntegers, 0, currentSize);
        integerList = newIntegers;
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
        if (currentSize - index >= 0) System.arraycopy(integerList, index + 1, integerList, index, currentSize - index);
    }

    private void shiftRightFromIndex(int index) {
        isFull();
        if (currentSize - index >= 0) System.arraycopy(integerList, index, integerList, index + 1, currentSize - index);
    }

    private void isFull() {
        if (currentSize >= integerList.length) {
            expandList();
        }
    }

    private void sort(Integer[] listToSort) {
        for (int i = 0; i < size() - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < size(); j++) {
                if (listToSort[j] < listToSort[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(listToSort, i, minElementIndex);
        }
    }

    private void swapElements(Integer[] arr, int indexA, int indexB) {
        Integer temp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = temp;
    }

    private boolean binarySearch(Integer[] sortedList, Integer item) {
        int min = 0;
        int max = currentSize - 1;

        while (min <= max) {
            int middle = (max + min) / 2;

            if (Objects.equals(sortedList[middle], item)) {
                return true;
            }

            if (item < sortedList[middle]) {
                max = middle - 1;
            } else {
                min = middle + 1;
            }
        }
        return false;
    }
}
