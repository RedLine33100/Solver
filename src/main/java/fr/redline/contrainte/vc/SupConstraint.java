package fr.redline.contrainte.vc;

import fr.redline.contrainte.Constraint;
import fr.redline.contrainte.ConstraintResult;
import fr.redline.contrainte.reduction.ReductionResult;
import fr.redline.value.Variable;

import java.util.LinkedHashSet;

public class SupConstraint<T extends Comparable<T>> implements Constraint<T> {

    private final Variable<T> fv, sv;
    private final LinkedHashSet<Variable<T>> unknownVariables = new LinkedHashSet<>();
    private final boolean equals;

    public SupConstraint(Variable<T> constraint1, Variable<T> constraint2, boolean equals) {
        this.fv = constraint1;
        this.sv = constraint2;
        this.equals = equals;
        this.unknownVariables.addAll(constraint1.getUnknownVariables());
        this.unknownVariables.addAll(constraint2.getUnknownVariables());
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
    public LinkedHashSet<Variable<T>> getUnknownVariables() {
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
