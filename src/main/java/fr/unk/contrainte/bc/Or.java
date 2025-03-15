package fr.unk.contrainte.bc;

import fr.unk.contrainte.Constraint;
import fr.unk.contrainte.ConstraintResult;

import java.util.Map;

public class Or<T> implements Constraint<T> {

    final Constraint<T> c1;
    final Constraint<T> c2;

    public Or(Constraint<T> c1, Constraint<T> c2){
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public ConstraintResult satisfied(Map<String, T> objectMap) {
        ConstraintResult res1 = c1.satisfied(objectMap);
        ConstraintResult res2 = c2.satisfied(objectMap);
        if(res1==ConstraintResult.UNKNOWN || res2==ConstraintResult.UNKNOWN)
            return ConstraintResult.UNKNOWN;
        return res1 == ConstraintResult.TRUE || res2 == ConstraintResult.TRUE ? ConstraintResult.TRUE : ConstraintResult.FALSE;
    }

}
