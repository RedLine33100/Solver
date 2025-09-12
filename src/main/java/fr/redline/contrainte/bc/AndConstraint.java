package fr.redline.contrainte.bc;

import fr.redline.contrainte.Constraint;
import fr.redline.contrainte.ConstraintResult;
import fr.redline.contrainte.reduction.ReductionResult;
import fr.redline.value.Variable;

import java.util.LinkedHashSet;

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
        if (res1 != ConstraintResult.TRUE)
            return res1;

        return c2.evaluate();
    }

    @Override
    public LinkedHashSet<Variable<T>> getUnknownVariables() {
        return unknownVariables;
    }

    @Override
    public void reduce(ReductionResult<T> reductionResult) {
        c1.reduce(reductionResult);
        c2.reduce(reductionResult);
    }

    @Override
    public ConstraintResult testAndReduce(ReductionResult<T> reductionResult) {
        ConstraintResult res1 = c1.testAndReduce(reductionResult);
        ConstraintResult res2 = c2.testAndReduce(reductionResult);

        if (res1 == ConstraintResult.UNKNOWN || res2 == ConstraintResult.UNKNOWN)
            return ConstraintResult.UNKNOWN;

        if (res1 != res2)
            return ConstraintResult.FALSE;

        return res1 == ConstraintResult.TRUE ? ConstraintResult.TRUE : ConstraintResult.FALSE;
    }

}
