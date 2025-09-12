package fr.redline.domaine.number;

import fr.redline.domaine.Domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastIntDomain implements Domain<Integer> {

    private final int min, max;
    private final boolean[] values;

    public FastIntDomain(int min, int max) {
        this.values = new boolean[max - min + 1];
        this.min = min;
        this.max = max;
        Arrays.fill(values, true);
    }


    @Override
    public List<Integer> getPossibility() {
        List<Integer> possibility = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            if (values[i]) {
                possibility.add(i + min);
            }
        }
        return possibility;
    }

    @Override
    public boolean inDomain(Integer value) {
        return values[value - min];
    }

    @Override
    public void removeFromDomain(Integer value) {
        values[value - min] = false;
    }

    @Override
    public void addToDomain(Integer value) {
        values[value - min] = true;
    }
}
