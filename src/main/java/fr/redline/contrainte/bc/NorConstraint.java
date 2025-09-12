package fr.redline.contrainte.bc;

import fr.redline.contrainte.Constraint;
import fr.redline.contrainte.ConstraintResult;
import fr.redline.contrainte.reduction.ReductionResult;
import fr.redline.value.Variable;

import java.util.LinkedHashSet;

public class NorConstraint<T> implements Constraint<T> {

    private final Constraint<T> c1, c2;
    private final LinkedHashSet<Variable<T>> unknownVariables = new LinkedHashSet<>();

    public NorConstraint(Constraint<T> constraint1, Constraint<T> constraint2) {
        this.c1 = constraint1;
        this.c2 = constraint2;
        this.unknownVariables.addAll(constraint1.getUnknownVariables());
        this.unknownVariables.addAll(constraint2.getUnknownVariables());
    }

    @Override
    public ConstraintResult evaluate() {
        ConstraintResult c1s = c1.evaluate();
        if (c1s == ConstraintResult.UNKNOWN)
            return ConstraintResult.UNKNOWN;

        ConstraintResult c2s = c2.evaluate();
        if (c2s == ConstraintResult.UNKNOWN)
            return ConstraintResult.UNKNOWN;

        return c1s != c2s ? ConstraintResult.TRUE : ConstraintResult.FALSE;
    }

    @Override
    public LinkedHashSet<Variable<T>> getUnknownVariables() {
        return unknownVariables;
    }

    @Override
    public void reduce(ReductionResult<T> reductionResult) {

    }

    @Override
    public ConstraintResult testAndReduce(ReductionResult<T> reductionResult) {
        ConstraintResult res1 = c1.evaluate();
        ConstraintResult res2 = c2.evaluate();

        if (res1 != res2) {
            if (res1 == ConstraintResult.UNKNOWN) {
                res1 = c1.testAndReduce(reductionResult);
            } else if (res2 == ConstraintResult.UNKNOWN) {
                res2 = c2.testAndReduce(reductionResult);
            }
        }

        if (res1 == ConstraintResult.UNKNOWN || res2 == ConstraintResult.UNKNOWN)
            return ConstraintResult.UNKNOWN;

        return res1 == res2 ? ConstraintResult.FALSE : ConstraintResult.TRUE;
    }

}
