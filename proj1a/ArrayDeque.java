import java.lang.reflect.Array;

public class ArrayDeque<T> {
    private T[] array = (T[]) new Object[8];
    private int startIndex = 7;
    private int endIndex = 0;

    public ArrayDeque() {}

    private void resize(T[] newArray, int amount) {
        int i = startIndex + 1;
        for (int j = 0; j < amount; j++) {
            if (i == array.length) {
                i = 0;
            }
            newArray[j] = array[i];
            i++;
        }

        startIndex = newArray.length - 1;
        endIndex = size();
        array = newArray;
    }

    private void checkResize() {
        int amount = size();

        if (amount == array.length) {
            T[] newArray = (T[]) new Object[amount * 2];
            resize(newArray, amount);
        } else if (amount <= array.length / 4) {
            int newSize = array.length / 2;
            if (newSize < 16) {
                newSize = 16;
            }

            T[] newArray = (T[]) new Object[newSize];
            resize(newArray, amount);
        }
    }

    private int safeDecrement(int index) {
        index--;
        if (index < 0) {
            index = array.length - 1;
        }
        return index;
    }

    private int safeIncrement(int index) {
        index++;
        if (index == array.length) {
            index = 0;
        }
        return index;
    }

    public void addFirst(T item) {
        array[startIndex] = item;
        startIndex = safeDecrement(startIndex);
    }

    public void addLast(T item) {
        array[endIndex] = item;
        endIndex = safeIncrement(endIndex);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return endIndex + array.length - 1 - startIndex;
    }

    public void printDeque() {
        String res = "";

        for (int i = startIndex + 1; i < array.length; i++) {
            res += array[i] + " ";
        }
        for (int i = 0; array[i] != null; i++) {
            res += array[i] + " ";
        }

        System.out.println(res);
    }

    public T removeFirst() {
        if (size() == 0) {
            return null;
        }

        int firstIndex = safeIncrement(startIndex);

        T res = array[firstIndex];
        array[firstIndex] = null;
        startIndex = firstIndex;
        return res;
    }

    public T removeLast() {
        if (size() == 0) {
            return null;
        }

        int lastIndex = safeDecrement(endIndex);

        T res = array[lastIndex];
        array[lastIndex] = null;
        endIndex = lastIndex;
        return res;
    }

    public T get(int index) {
        int actualIndex = startIndex + 1 + index;
        if (actualIndex > array.length - 1) {
            actualIndex -= array.length - 1;
        }

        return array[actualIndex];
    }
}
