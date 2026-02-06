package fr.redline.contrainte.nc;

import fr.redline.contrainte.Constraint;
import fr.redline.contrainte.ConstraintResult;
import fr.redline.contrainte.reduction.ReductionResult;
import fr.redline.utils.OptimizedList;
import fr.redline.value.VarType;
import fr.redline.value.Variable;
import fr.redline.value.numvar.Calcul;


public class ListEquals<T extends Comparable<T>> implements Constraint<T> {

    private final Variable<T>[] variableList;
    private final OptimizedList<Variable<T>> uknVar = new OptimizedList<>();

    public ListEquals(Variable<T>[] variableList) {
        this.variableList = variableList;
        for (Variable<T> variable : this.variableList) {
            variable.getUnknownVariables().activeValues().forEach(uknVar::add);
        }
    }

    @Override
    public ConstraintResult evaluate() {

        ConstraintResult cr = ConstraintResult.TRUE;

        for (int i = 0; i < variableList.length; i++) {
            T v = variableList[i].getValue();
            if (v == null) {
                cr = ConstraintResult.UNKNOWN;
                continue;
            }
            for (int y = 0; y < variableList.length; y++) {
                if (i == y)
                    continue;
                T otherValue = variableList[y].getValue();
                if (otherValue == null) {
                    cr = ConstraintResult.UNKNOWN;
                    continue;
                }
                if (v.compareTo(otherValue) != 0)
                    return ConstraintResult.FALSE;
            }
        }

        return cr;
    }

    @Override
    public OptimizedList<Variable<T>> getUnknownVariables() {
        return uknVar;
    }

    @Override
    public void reduce(ReductionResult<T> reductionResult) {

        for (int i = 0; i < variableList.length - 1; i++) {

            T vi = variableList[i].getValue();

            if (vi == null)
                continue;

            for (int j = i + 1; j < variableList.length; j++) {

                Variable<T> toSet = variableList[j];

                if (toSet.getValue() != null)
                    continue;

                if (toSet.getType() == VarType.CALCULATED) {

                    ((Calcul<T>) toSet).reverseVariables(reductionResult, vi, false);

                } else {

                    reductionResult.getVariableChange(toSet).setValue(vi);

                }

            }

            return;

        }

    }

    @Override
    public ConstraintResult testAndReduce(ReductionResult<T> reductionResult) {

        ConstraintResult cr = ConstraintResult.TRUE;

        for (int i = 0; i < variableList.length - 1; i++) {

            Variable<T> iVar = variableList[i];
            T iValue = iVar.getValue();
            if (iValue == null) {
                cr = ConstraintResult.UNKNOWN;
                continue;
            }

            for (int j = i + 1; j < variableList.length; j++) {

                Variable<T> jVar = variableList[j];
                T jValue = jVar.getValue();

                if (jValue == null) {

                    if (jVar.getType() == VarType.CALCULATED)
                        ((Calcul<T>) jVar).reverseVariables(reductionResult, iValue, false);
                    else
                        reductionResult.getVariableChange(jVar).setValue(iValue);

                }

                jValue = jVar.getValue();

                if (jValue == null) {
                    cr = ConstraintResult.UNKNOWN;
                    continue;
                }

                if (iValue.compareTo(jValue) != 0) {
                    return ConstraintResult.FALSE;
                }

            }

        }
        return cr;
    }

}
