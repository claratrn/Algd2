package ch.fhnw.algd2.arraycollections;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

public class SortedSet<E extends Comparable<? super E>> extends AbstractArrayCollection<E> implements Set<E> {
    public static final int DEFAULT_CAPACITY = 100;
    private E[] data;

    public SortedSet() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public SortedSet(int capacity) {
        data = (E[]) new Comparable[capacity];
    }

    @Override
    public boolean add(E e) {

        Objects.requireNonNull(e);

        if (size() < data.length && !contains(e)) {
            data[size()] = e;
            Arrays.sort(data, 0, size());
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        Objects.requireNonNull(o);

        if (size() > 0) {
            int i = 0;
            while (i < size() && !data[i].equals(o)) {
                i++;
            }
            if (i < size()) { //TODO: better search algo?
                data[i] = null;
                Arrays.sort(data, 0, size());
                return true;
            }
        }
        return false;
        // TODO implement unless collection shall be immutable
        // throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {

        Objects.requireNonNull(o);

        int i = 0;
        while (i < size() && data[i] != null && !data[i].equals(o)) {
            i++;
        }

        if (i < size()) {
            return true;
        }
        return false;
        // TODO must be implemented
        // throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(data, size());
    }

    @Override
    public int size() {
        int i = 0;
        while (i < data.length && data[i] != null) {
            i++;
        }
        return i;
    }

    public static void main(String[] args) {
        SortedSet<Integer> bag = new SortedSet<Integer>();
        bag.add(2);
        bag.add(2);
        bag.add(1);
        System.out.println(Arrays.toString(bag.toArray()));
    }
}
