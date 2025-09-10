package fr.redline.contrainte.nc;

import fr.redline.contrainte.Constraint;
import fr.redline.contrainte.ConstraintResult;
import fr.redline.utils.Pair;
import fr.redline.value.Variable;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class ListEquals<T extends Comparable<T>> implements Constraint<T> {

    private final Variable<T>[] variableList;
    private final LinkedHashSet<Variable<T>> uknVar = new LinkedHashSet<>();

    public ListEquals(Variable<T>[] variableList){
        this.variableList = variableList;
        for (Variable<T> variable : this.variableList) {
            uknVar.addAll(variable.getUnknownVariables());
        }
    }

    @Override
    public ConstraintResult evaluate() {

        ConstraintResult cr = ConstraintResult.TRUE;

        for(int i = 0; i<variableList.length; i++) {
            T v = variableList[i].getValue();
            if(v == null) {
                cr = ConstraintResult.UNKNOWN;
                continue;
            }
            for (int y = i + 1; y < variableList.length; y++) {
                T otherValue = variableList[y].getValue();
                if(otherValue == null){
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
    public LinkedHashSet<Variable<T>> getUnknownVariables() {
        return uknVar;
    }

    @Override
    public Pair<List<Variable<T>>, Integer> reverseVariables(T reversedValue) {
        return new Pair<>(new ArrayList<>(), 0);
    }

    @Override
    public Pair<List<Variable<T>>, Integer> tryReverse() {
        return new Pair<>(new ArrayList<>(), 0);
    }

}
