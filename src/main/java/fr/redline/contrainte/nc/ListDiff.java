package fr.redline.contrainte.nc;

import fr.redline.contrainte.Constraint;
import fr.redline.contrainte.ConstraintResult;
import fr.redline.value.ValueGetter;

import java.util.List;
import java.util.Map;

public class ListDiff<T extends Comparable<T>> implements Constraint<T> {

    final List<ValueGetter<T>> variableList;

    public ListDiff(List<ValueGetter<T>> variables){
        this.variableList = variables;
    }

    @Override
    public ConstraintResult satisfied(Map<String, T> objectMap) {

        for(int i = 0; i<variableList.size(); i++) {
            T v = variableList.get(i).getValue(objectMap);
            if(v == null)
                return ConstraintResult.UNKNOWN;
            for (int y = i + 1; y < variableList.size(); y++)
                if (v.compareTo(variableList.get(y).getValue(objectMap)) == 0)
                    return ConstraintResult.FALSE;
        }

        return ConstraintResult.TRUE;
    }

}
