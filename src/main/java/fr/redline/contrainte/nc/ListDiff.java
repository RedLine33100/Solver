package fr.redline.contrainte.nc;

import fr.redline.contrainte.Constraint;
import fr.redline.contrainte.ConstraintResult;
import fr.redline.contrainte.reduction.ReductionResult;
import fr.redline.value.VarType;
import fr.redline.value.Variable;
import fr.redline.value.numvar.Calcul;

import java.util.LinkedHashSet;

public class ListDiff<T extends Comparable<T>> implements Constraint<T> {

    private final Variable<T>[] variableList;
    private final LinkedHashSet<Variable<T>> uknVar = new LinkedHashSet<>();

    public ListDiff(Variable<T>[] variables) {
        this.variableList = variables;
        for (Variable<T> v : this.variableList) {
            uknVar.addAll(v.getUnknownVariables());
        }
    }

    @Override
    public ConstraintResult evaluate() {

        ConstraintResult cr = ConstraintResult.TRUE;

        for (int i = 0; i < variableList.length - 1; i++) {
            T v = variableList[i].getValue();
            if (v == null) {
                cr = ConstraintResult.UNKNOWN;
                continue;
            }
            for (int y = i + 1; y < variableList.length; y++) {
                T otherValue = variableList[y].getValue();
                if (otherValue == null) {
                    cr = ConstraintResult.UNKNOWN;
                    continue;
                }
                if (v.compareTo(otherValue) == 0)
                    return ConstraintResult.FALSE;
            }
        }

        return cr;
    }

    @Override
    public LinkedHashSet<Variable<T>> getUnknownVariables() {
        return uknVar;
    }

    @Override
    public void reduce(ReductionResult<T> reductionResult) {

        for(int i = 0; i < variableList.length-1; i++){

            Variable<T> iVar = variableList[i];
            T iValue = iVar.getValue();
            if(iValue == null)
                continue;

            for(int j = i+1; j < variableList.length; j++){

                Variable<T> jVar = variableList[j];
                T jValue = jVar.getValue();

                if(jValue != null)
                    continue;

                if(jVar.getType() == VarType.CALCULATED)
                    ((Calcul<T>) jVar).reverseVariables(reductionResult, iValue, true);
                else
                    reductionResult.getVariableChange(jVar).varDomainReduce(iValue);

            }

        }

    }

    @Override
    public ConstraintResult testAndReduce(ReductionResult<T> reductionResult, boolean canReduce) {

        ConstraintResult cr = ConstraintResult.TRUE;

        for(int i = 0; i < variableList.length-1; i++){

            Variable<T> iVar = variableList[i];
            T iValue = iVar.getValue();
            if(iValue == null) {
                cr = ConstraintResult.UNKNOWN;
                continue;
            }

            for(int j = i+1; j < variableList.length; j++){

                Variable<T> jVar = variableList[j];
                T jValue = jVar.getValue();

                if(canReduce && jValue == null) {

                    if (jVar.getType() == VarType.CALCULATED)
                        ((Calcul<T>) jVar).reverseVariables(reductionResult, iValue, true);
                    else
                        reductionResult.getVariableChange(jVar).varDomainReduce(iValue);

                }

                jValue = jVar.getValue();

                if(jValue == null){
                    cr = ConstraintResult.UNKNOWN;
                    continue;
                }

                if(iValue.compareTo(jValue) == 0){
                    return ConstraintResult.FALSE;
                }

            }

        }
        return cr;
    }

}
