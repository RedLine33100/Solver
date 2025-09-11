package fr.redline.domaine.number;

import fr.redline.domaine.Domain;
import fr.redline.utils.OptimizedList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FloatDomain implements Domain<Float> {

    final OptimizedList<Float> list = new OptimizedList<>();

    public FloatDomain(float min, float max, float jump) {
        float current = min;
        while (current <= max) {
            list.add(current);
            current += jump;
        }
    }

    @Override
    public List<Float> getPossibility() {
        return list.getValues();
    }

    @Override
    public boolean inDomain(Float value) {
        return list.contains(value);
    }

    @Override
    public void removeFromDomain(Float value) {
        list.remove(value);
    }

    @Override
    public void addToDomain(Float value) {
        list.add(value);
    }

}
