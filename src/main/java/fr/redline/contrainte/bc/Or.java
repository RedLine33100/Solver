package fr.redline.contrainte.bc;

import fr.redline.contrainte.Constraint;
import fr.redline.contrainte.ConstraintResult;

public class Or implements Constraint {

    final Constraint c1;
    final Constraint c2;

    public Or(Constraint c1, Constraint c2){
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public ConstraintResult satisfied() {
        ConstraintResult res1 = c1.satisfied();
        ConstraintResult res2 = c2.satisfied();
        if(res1==ConstraintResult.UNKNOWN || res2==ConstraintResult.UNKNOWN)
            return ConstraintResult.UNKNOWN;
        return res1 == ConstraintResult.TRUE || res2 == ConstraintResult.TRUE ? ConstraintResult.TRUE : ConstraintResult.FALSE;
    }

}
