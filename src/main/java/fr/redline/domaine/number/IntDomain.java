package fr.redline.domaine.number;

import fr.redline.domaine.Domain;
import fr.redline.utils.OptimizedList;

import java.util.List;

public class IntDomain implements Domain<Integer> {

    OptimizedList<Integer> list = new OptimizedList<>();

    public IntDomain(int min, int max, int jump) {
        int current = min;
        while (current <= max) {
            list.add(current);
            current += jump;
        }
    }

    @Override
    public List<Integer> getPossibility() {
        return list.getValues();
    }

    @Override
    public boolean inDomain(Integer value) {
        return list.contains(value);
    }

    @Override
    public void removeFromDomain(Integer value) {
        list.remove(value);
    }

    @Override
    public void addToDomain(Integer value) {
        list.add(value);
    }
}
