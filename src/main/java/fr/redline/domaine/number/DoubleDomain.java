package fr.redline.domaine.number;

import fr.redline.domaine.Domain;
import fr.redline.utils.OptimizedList;

import java.util.List;

public class DoubleDomain implements Domain<Double> {

    final OptimizedList<Double> list = new OptimizedList<>();

    public DoubleDomain(Double min, Double max, Double jump) {
        double current = min;
        while (current <= max) {
            list.add(current);
            current += jump;
        }
    }

    @Override
    public List<Double> getPossibility() {
        return list.getValues();
    }

    @Override
    public boolean inDomain(Double value) {
        return list.contains(value);
    }

    @Override
    public void removeFromDomain(Double value) {
        list.remove(value);
    }

    @Override
    public void addToDomain(Double value) {
        list.add(value);
    }

}
