package fr.unk.contrainte.bc;

import fr.unk.contrainte.Constraint;
import fr.unk.contrainte.ConstraintResult;

import java.util.Map;

public class Nor<T> implements Constraint<T> {

    final Constraint<T> c1;
    final Constraint<T> c2;

    public Nor(Constraint<T> c1, Constraint<T> c2){
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public ConstraintResult satisfied(Map<String, T> objectMap) {
        ConstraintResult c1s = this.c1.satisfied(objectMap);
        if(c1s == ConstraintResult.UNKNOWN)
            return ConstraintResult.UNKNOWN;

        ConstraintResult c2s = this.c2.satisfied(objectMap);
        if(c2s == ConstraintResult.UNKNOWN)
            return ConstraintResult.UNKNOWN;

        return c1s != c2s ? ConstraintResult.TRUE : ConstraintResult.FALSE;
    }

}
