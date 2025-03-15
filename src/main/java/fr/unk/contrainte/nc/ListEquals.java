package fr.unk.contrainte.nc;

import fr.unk.contrainte.Constraint;
import fr.unk.contrainte.ConstraintResult;
import fr.unk.variable.Variable;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ListEquals<T extends Comparable<T>> implements Constraint<T> {

    final List<Variable<T>> variableList;

    public ListEquals(List<Variable<T>> variables){
        this.variableList = variables;
    }

    public ListEquals(Variable<T>... variables){
        this(Arrays.asList(variables));
    }

    @Override
    public ConstraintResult satisfied(Map<String, T> objectMap) {

        for(int i = 0; i<variableList.size(); i++) {
            T v = variableList.get(i).getValue(objectMap);
            if(v == null)
                return ConstraintResult.UNKNOWN;
            for (int y = i + 1; y < variableList.size(); y++)
                if (v.compareTo(variableList.get(y).getValue(objectMap)) != 0)
                    return ConstraintResult.FALSE;
        }

        return ConstraintResult.TRUE;
    }

}
