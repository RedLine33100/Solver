package fr.redline.contrainte.bc;

import fr.redline.contrainte.Constraint;
import fr.redline.contrainte.ConstraintResult;

public class And<T> implements Constraint<T> {
    final Constraint<T> c1;
    final Constraint<T> c2;

    public And(Constraint<T> c1, Constraint<T> c2){
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public ConstraintResult satisfied() {
        ConstraintResult res1 = this.c1.satisfied();
        if(res1 == ConstraintResult.UNKNOWN)
            return res1;
        if(res1==ConstraintResult.FALSE)
            return res1;

        ConstraintResult res2 = this.c2.satisfied();
        if(res2 == ConstraintResult.UNKNOWN)
            return res2;
        if(res2==ConstraintResult.FALSE)
            return res2;

        return ConstraintResult.TRUE;
    }
}
