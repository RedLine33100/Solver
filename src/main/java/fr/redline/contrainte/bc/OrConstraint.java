package fr.redline.contrainte.bc;

import fr.redline.contrainte.Constraint;
import fr.redline.contrainte.ConstraintResult;
import fr.redline.contrainte.reduction.ReductionResult;
import fr.redline.value.Variable;

import java.util.LinkedHashSet;

public class OrConstraint<T> implements Constraint<T> {

    private final Constraint<T> c1, c2;
    private final LinkedHashSet<Variable<T>> unknownVariables = new LinkedHashSet<>();

    public OrConstraint(Constraint<T> constraint1, Constraint<T> constraint2) {
        this.c1 = constraint1;
        this.c2 = constraint2;
        this.unknownVariables.addAll(constraint1.getUnknownVariables());
        this.unknownVariables.addAll(constraint2.getUnknownVariables());
    }

    @Override
    public ConstraintResult evaluate() {
        ConstraintResult res1 = c1.evaluate();
        ConstraintResult res2 = c2.evaluate();
        if (res1 == ConstraintResult.UNKNOWN || res2 == ConstraintResult.UNKNOWN)
            return ConstraintResult.UNKNOWN;
        return res1 == ConstraintResult.TRUE || res2 == ConstraintResult.TRUE ? ConstraintResult.TRUE : ConstraintResult.FALSE;
    }

    @Override
    public LinkedHashSet<Variable<T>> getUnknownVariables() {
        return unknownVariables;
    }

    @Override
    public void reduce(ReductionResult<T> reductionResult) {

    }

    @Override
    public ConstraintResult testAndReduce(ReductionResult<T> reductionResult, boolean canReduce) {
        ConstraintResult res1 = c1.testAndReduce(reductionResult, false);
        ConstraintResult res2 = c2.testAndReduce(reductionResult, false);

        if(res1 == ConstraintResult.UNKNOWN || res2 == ConstraintResult.UNKNOWN)
            return ConstraintResult.UNKNOWN;

        return res1 == ConstraintResult.TRUE || res2 == ConstraintResult.TRUE ? ConstraintResult.TRUE : ConstraintResult.FALSE;
    }

}
