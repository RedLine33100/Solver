package fr.redline.contrainte.vc;

import fr.redline.contrainte.Constraint;
import fr.redline.contrainte.ConstraintResult;
import fr.redline.contrainte.reduction.ReductionResult;
import fr.redline.value.Variable;

import java.util.LinkedHashSet;

public class DiffConstraint<T extends Comparable<T>> implements Constraint<T> {

    private final Variable<T> fv, sv;
    private final LinkedHashSet<Variable<T>> unknownVariables = new LinkedHashSet<>();

    public DiffConstraint(Variable<T> constraint1, Variable<T> constraint2) {
        this.fv = constraint1;
        this.sv = constraint2;
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

        return vf.compareTo(vs) != 0 ? ConstraintResult.TRUE : ConstraintResult.FALSE;
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
        return null;
    }

}
