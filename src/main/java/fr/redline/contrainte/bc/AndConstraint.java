package fr.redline.contrainte.bc;

import fr.redline.contrainte.Constraint;
import fr.redline.contrainte.ConstraintResult;
import fr.redline.utils.Pair;
import fr.redline.value.Variable;

import java.util.LinkedHashSet;
import java.util.List;

public class AndConstraint<T> implements Constraint<T> {

    private final Constraint<T> c1, c2;
    private final LinkedHashSet<Variable<T>> unknownVariables = new LinkedHashSet<>();

    public AndConstraint(Constraint<T> constraint1, Constraint<T> constraint2) {
        this.c1 = constraint1;
        this.c2 = constraint2;
        this.unknownVariables.addAll(constraint1.getUnknownVariables());
        this.unknownVariables.addAll(constraint2.getUnknownVariables());
    }

    @Override
    public ConstraintResult evaluate() {
        ConstraintResult res1 = c1.evaluate();
        if(res1 != ConstraintResult.TRUE)
            return res1;

        return c2.evaluate();
    }

    @Override
    public LinkedHashSet<Variable<T>> getUnknownVariables() {
        return unknownVariables;
    }

    @Override
    public Pair<List<Variable<T>>, Integer> reverseVariables(T reversedValue) {
        return tryReverse();
    }

    @Override
    public Pair<List<Variable<T>>, Integer> tryReverse() {
        Pair<List<Variable<T>>, Integer> res1 = c1.tryReverse();
        Pair<List<Variable<T>>, Integer> res2 = c2.tryReverse();
        res1.l().addAll(res2.l());
        return new Pair<>(res1.l(), res2.r() + res1.r());
    }

}
