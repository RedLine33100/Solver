package fr.unk.contrainte.vc;

import fr.unk.contrainte.Constraint;
import fr.unk.variable.Variable;

import java.util.Map;

public class Diff<T extends Comparable<T>> implements Constraint {

    final Variable<T> fv;
    final Variable<T> sv;

    public Diff(Variable<T> fv, Variable<T> sv){
        this.fv = fv;
        this.sv = sv;
    }

    @Override
    public boolean satisfied(Map<String, Object> objectMap) {
        return fv.getValue(objectMap).compareTo(sv.getValue(objectMap)) != 0;
    }

}