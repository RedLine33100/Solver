package fr.unk.domaine.number;

import fr.unk.domaine.Domain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class DoubleDomain implements Domain<Double> {

    final double min, max, jump;

    public DoubleDomain(double min, double max, double jump){
        this.min = min;
        this.max = max;
        this.jump = jump;
    }

    @Override
    public List<Double> getPossibility() {
        List<Double> doubles = new ArrayList<>();
        double current = min;
        while (current <= max){
            doubles.add(current);
            current += this.jump;
        }
        return doubles;
    }
}
