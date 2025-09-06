package fr.redline.contrainte.nc;

import fr.redline.contrainte.Constraint;
import fr.redline.contrainte.ConstraintResult;
import fr.redline.utils.Pair;
import fr.redline.value.Value;
import fr.redline.value.variable.Variable;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class ListDiff<T extends Comparable<T>> implements Constraint<T> {

    private final List<Value<T>> variableList;
    private final LinkedHashSet<Variable<T>> uknVar = new LinkedHashSet<>();

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

    @Override
    public LinkedHashSet<Variable<T>> getUnknownVariables() {
        return uknVar;
    }

    @Override
    public Pair<List<Value<T>>, Integer> reverseVariables(T reversedValue) {
        return new Pair<>(new ArrayList<>(), 0);
    }

    @Override
    public Pair<List<Value<T>>, Integer> tryReverse() {
        return new Pair<>(new ArrayList<>(), 0);
    }

}
