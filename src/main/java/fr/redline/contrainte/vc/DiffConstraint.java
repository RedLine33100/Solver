package fr.redline.contrainte.vc;

import fr.redline.contrainte.Constraint;
import fr.redline.contrainte.ConstraintResult;
import fr.redline.contrainte.reduction.ReductionResult;
import fr.redline.utils.OptimizedList;
import fr.redline.value.VarType;
import fr.redline.value.Variable;
import fr.redline.value.numvar.Calcul;

public class DiffConstraint<T extends Comparable<T>> implements Constraint<T> {

    private final Variable<T> fv, sv;
    private final OptimizedList<Variable<T>> unknownVariables = new OptimizedList<>();

    public DiffConstraint(Variable<T> constraint1, Variable<T> constraint2) {
        this.fv = constraint1;
        this.sv = constraint2;
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

        return vf.compareTo(vs) != 0 ? ConstraintResult.TRUE : ConstraintResult.FALSE;
    }

    @Override
    public OptimizedList<Variable<T>> getUnknownVariables() {
        return unknownVariables;
    }

    @Override
    public void reduce(ReductionResult<T> reductionResult) {

        T vf = fv.getValue();
        T vs = sv.getValue();

        if (vf == null && vs == null)
            return;

        Variable<T> toChange;
        T newValue;

        if (vf == null) {
            toChange = fv;
            newValue = vs;
        } else {
            toChange = sv;
            newValue = vf;
        }

        if (toChange.getType() == VarType.CALCULATED)
            ((Calcul<T>) toChange).reverseVariables(reductionResult, newValue, true);
        else
            reductionResult.getVariableChange(toChange).varDomainReduce(newValue);

    }

    @Override
    public ConstraintResult testAndReduce(ReductionResult<T> reductionResult) {
        T vf = fv.getValue();
        T vs = sv.getValue();

        if (vf == null && vs == null)
            return ConstraintResult.UNKNOWN;

        Variable<T> toChange;
        T newValue;

        if (vf == null) {
            toChange = fv;
            newValue = vs;
        } else {
            toChange = sv;
            newValue = vf;
        }

        if (toChange.getType() == VarType.CALCULATED)
            ((Calcul<T>) toChange).reverseVariables(reductionResult, newValue, true);
        else
            reductionResult.getVariableChange(toChange).varDomainReduce(newValue);

        return this.evaluate();
    }

}
