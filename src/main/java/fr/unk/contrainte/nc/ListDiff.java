package fr.unk.contrainte.nc;

import fr.unk.contrainte.Constraint;
import fr.unk.variable.Variable;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ListDiff<T extends Comparable<T>> implements Constraint<T> {

    final List<Variable<T>> variableList;

    public ListDiff(List<Variable<T>> variables){
        this.variableList = variables;
    }

    public ListDiff(Variable<T>... variables){
        this(Arrays.asList(variables));
    }

    @Override
    public boolean satisfied(Map<String, T> objectMap) {

        for(int i = 0; i<variableList.size(); i++)
            for(int y = i+1; y<variableList.size(); y++)
                if (variableList.get(i).getValue(objectMap).compareTo(variableList.get(y).getValue(objectMap)) == 0)
                    return false;

        return true;
    }

}
