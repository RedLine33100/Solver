package fr.redline.contrainte.nc;

import fr.redline.contrainte.Constraint;
import fr.redline.contrainte.ConstraintResult;
import fr.redline.value.Value;
import fr.redline.value.variable.Variable;

import java.util.LinkedHashSet;
import java.util.List;

public class ListDiff<T extends Comparable<T>> implements Constraint<T> {

    final List<Value<T>> variableList;
    LinkedHashSet<Variable<T>> uknVar = new LinkedHashSet<>();

    public ListDiff(List<Value<T>> variables){
        this.variableList = variables;
        this.variableList.forEach(variable -> uknVar.addAll(variable.getUnknownVariables()));
    }

    @Override
    public ConstraintResult evaluate() {

        for(int i = 0; i<variableList.size(); i++) {
            T v = variableList.get(i).getValue();
            if(v == null)
                return ConstraintResult.UNKNOWN;
            for (int y = i + 1; y < variableList.size(); y++)
                if (v.compareTo(variableList.get(y).getValue()) == 0)
                    return ConstraintResult.FALSE;
        }

        return ConstraintResult.TRUE;
    }

    public LinkedHashSet<Variable<T>> getUnknownVariables() {
        return uknVar;
    }

}
