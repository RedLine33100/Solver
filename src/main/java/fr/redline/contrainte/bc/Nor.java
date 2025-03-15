package fr.redline.contrainte.bc;

import fr.redline.contrainte.Constraint;
import fr.redline.contrainte.ConstraintResult;

public class Nor<T> implements Constraint<T> {

    final Constraint<T> c1;
    final Constraint<T> c2;

    public Nor(Constraint<T> c1, Constraint<T> c2){
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public ConstraintResult satisfied() {
        ConstraintResult c1s = this.c1.satisfied();
        if(c1s == ConstraintResult.UNKNOWN)
            return ConstraintResult.UNKNOWN;

        ConstraintResult c2s = this.c2.satisfied();
        if(c2s == ConstraintResult.UNKNOWN)
            return ConstraintResult.UNKNOWN;

        return c1s != c2s ? ConstraintResult.TRUE : ConstraintResult.FALSE;
    }

}
