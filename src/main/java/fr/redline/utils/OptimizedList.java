package fr.redline.utils;

import java.util.*;

public class OptimizedList<T> {
    private final List<T> values;      // storage for all values ever seen
    private final Map<T, Integer> pos; // value -> current index
    private int size;                  // number of active values

    public OptimizedList() {
        this.values = new ArrayList<>();
        this.pos = new HashMap<>();
        this.size = 0;
    }

    public OptimizedList(Collection<T> initialValues) {
        this.values = new ArrayList<>(initialValues);
        this.pos = new HashMap<>();
        this.size = values.size();
        for (int i = 0; i < size; i++) {
            pos.put(values.get(i), i);
        }
    }

    /**
     * Returns true if value is currently active
     */
    public boolean contains(T val) {
        return pos.containsKey(val);
    }

    /**
     * Current active size
     */
    public int size() {
        return size;
    }

    /**
     * Get active value by index [0..size)
     */
    public T get(int i) {
        if (i < 0 || i >= size) throw new IndexOutOfBoundsException();
        return values.get(i);
    }

    /**
     * Remove a value in O(1), does not preserve order
     */
    public void remove(T val) {
        Integer idx = pos.get(val);
        if (idx == null) return; // not active

        int lastIdx = size - 1;
        T lastVal = values.get(lastIdx);

        // swap val with last element
        values.set(idx, lastVal);
        pos.put(lastVal, idx);

        size--;
        pos.remove(val);
    }

    /**
     * Restore a previously removed value in O(1)
     */
    public void restore(T val) {
        if (!pos.containsKey(val)) {
            values.set(size, val);  // reuse slot
            pos.put(val, size);
            size++;
        }
    }

    /**
     * Add a completely new value in amortized O(1)
     */
    public void add(T val) {
        if (pos.containsKey(val)) return; // already active

        if (size < values.size()) {
            // reuse slot if we already had capacity
            values.set(size, val);
        } else {
            // expand backing array
            values.add(val);
        }
        pos.put(val, size);
        size++;
    }

    /**
     * Iterate active values
     */
    public Iterable<T> activeValues() {
        return () -> new Iterator<T>() {
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public T next() {
                return values.get(cursor++);
            }
        };
    }

    public List<T> getValues() {
        return Collections.unmodifiableList(values);
    }
}
