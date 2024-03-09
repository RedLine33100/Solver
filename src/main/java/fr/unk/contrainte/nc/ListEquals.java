package fr.unk.contrainte.nc;

import fr.unk.contrainte.Constraint;
import fr.unk.variable.VarGetter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ListEquals<T extends Comparable<T>> extends Constraint<T> {

    final List<VarGetter<T>> variableList;

    public ListEquals(List<VarGetter<T>> variables){
        super(variables, null);
        this.variableList = variables;
    }

    public ListEquals(VarGetter<T>... variables){
        this(Arrays.asList(variables));
    }

    @Override
    public boolean satisfied(Map<String, T> objectMap) {

        for(int i = 0; i<variableList.size(); i++) {
            T vi = variableList.get(i).getValue(objectMap);
            if(vi == null)
                return false;
            for (int y = i + 1; y < variableList.size(); y++) {
                if (variableList.get(i).getValue(objectMap).compareTo(variableList.get(y).getValue(objectMap)) != 0)
                    return false;
            }
        }

        return true;
    }

}
