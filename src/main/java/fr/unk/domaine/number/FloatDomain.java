package fr.unk.domaine.number;

import fr.unk.domaine.Domain;

import java.util.ArrayList;
import java.util.List;

public class FloatDomain implements Domain<Float> {

    final List<Float> floatList;

    public FloatDomain(List<Float> doubles){
        this.floatList = doubles;
    }

    public FloatDomain(float min, float max, float jump){
        float current = min;
        this.floatList = new ArrayList<>();
        while (current <= max){
            this.floatList.add(current);
            current += jump;
        }
    }

    @Override
    public List<Float> getPossibility() {
        return this.floatList;
    }

    @Override
    public Domain<Float> duplicate() {
        return new FloatDomain(new ArrayList<>(this.floatList));
    }
}
