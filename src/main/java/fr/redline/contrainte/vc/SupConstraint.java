package fr.redline.contrainte.vc;

import fr.redline.contrainte.Constraint;
import fr.redline.contrainte.ConstraintResult;
import fr.redline.contrainte.reduction.ReductionResult;
import fr.redline.utils.OptimizedList;
import fr.redline.value.Variable;

public class SupConstraint<T extends Comparable<T>> implements Constraint<T> {

    private final Variable<T> fv, sv;
    private final OptimizedList<Variable<T>> unknownVariables = new OptimizedList<>();
    private final boolean equals;

    public SupConstraint(Variable<T> constraint1, Variable<T> constraint2, boolean equals) {
        this.fv = constraint1;
        this.sv = constraint2;
        this.equals = equals;
        constraint1.getUnknownVariables().activeValues().forEach(unknownVariables::add);
        constraint2.getUnknownVariables().activeValues().forEach(unknownVariables::add);
    }

    @Override
    public ConstraintResult evaluate() {
        T vf = fv.getValue();
        if (vf == null)
            return ConstraintResult.UNKNOWN;
        T vs = sv.getValue();
        if (vs == null)
            return ConstraintResult.UNKNOWN;

        int val = vf.compareTo(vs);
        boolean res = equals ? val >= 0 : val > 0;

        return res ? ConstraintResult.TRUE : ConstraintResult.FALSE;
    }

    @Override
    public OptimizedList<Variable<T>> getUnknownVariables() {
        return unknownVariables;
    }

    @Override
    public void reduce(ReductionResult<T> reductionResult) {

    }

    @Override
    public ConstraintResult testAndReduce(ReductionResult<T> reductionResult) {
        return this.evaluate();
    }


}
