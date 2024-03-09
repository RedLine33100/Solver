package fr.unk.domaine.number;

import fr.unk.domaine.Domain;

import java.util.ArrayList;
import java.util.List;

public class IntDomain implements Domain<Integer> {

    final List<Integer> intList;

    public IntDomain(List<Integer> Integers){
        this.intList = Integers;
    }

    public IntDomain(Integer min, Integer max, Integer jump){
        Integer current = min;
        this.intList = new ArrayList<>();
        while (current <= max){
            this.intList.add(current);
            current += jump;
        }
    }

    @Override
    public List<Integer> getPossibility() {
        return this.intList;
    }

    @Override
    public Domain<Integer> duplicate() {
        return new IntDomain(this.intList);
    }
}
