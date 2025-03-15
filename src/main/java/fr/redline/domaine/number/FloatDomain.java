package fr.redline.domaine.number;

import fr.redline.domaine.Domain;

import java.util.ArrayList;
import java.util.List;

public class FloatDomain implements Domain<Float> {

    final float min, max, jump;

    public FloatDomain(float min, float max, float jump){
        this.min = min;
        this.max = max;
        this.jump = jump;
    }

    @Override
    public List<Float> getPossibility() {
        List<Float> doubles = new ArrayList<>();
        float current = min;
        while (current <= max){
            doubles.add(current);
            current += this.jump;
        }
        return doubles;
    }
}
