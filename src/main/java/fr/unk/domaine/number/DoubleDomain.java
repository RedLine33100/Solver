package fr.unk.domaine.number;

import fr.unk.domaine.Domain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class DoubleDomain implements Domain<Double> {

    final List<Double> doubleList;

    public DoubleDomain(List<Double> doubles){
        this.doubleList = doubles;
    }

    public DoubleDomain(double min, double max, double jump){
        double current = min;
        this.doubleList = new ArrayList<>();
        while (current <= max){
            this.doubleList.add(current);
            current += jump;
        }
    }

    @Override
    public List<Double> getPossibility() {
        return this.doubleList;
    }

    @Override
    public Domain<Double> duplicate() {
        return new DoubleDomain(this.doubleList);
    }
}
