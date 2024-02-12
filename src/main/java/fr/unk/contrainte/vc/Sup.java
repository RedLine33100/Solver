package fr.unk.contrainte.vc;

import fr.unk.contrainte.Constraint;
import fr.unk.variable.Variable;

import java.util.Map;

public class Sup<T extends Comparable<T>> implements Constraint {

    final Variable<T> fv;
    final Variable<T> sv;

    final boolean equals;

    public Sup(Variable<T> fv, Variable<T> sv, boolean equals){
        this.fv = fv;
        this.sv = sv;
        this.equals = equals;
    }

    @Override
    public boolean satisfied(Map<String, Object> objectMap) {
        int val = fv.getValue(objectMap).compareTo(sv.getValue(objectMap));
        return equals ? val >= 0 : val > 0;
    }

}
