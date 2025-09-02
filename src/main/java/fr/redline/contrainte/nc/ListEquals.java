package fr.redline.contrainte.nc;

import fr.redline.contrainte.Constraint;
import fr.redline.contrainte.ConstraintResult;
import fr.redline.value.ValueGetter;
import fr.redline.value.variable.Variable;

import java.util.LinkedHashSet;
import java.util.List;

public class ListEquals<T extends Comparable<T>> implements Constraint<T> {

    final List<ValueGetter<T>> valueGetterList;
    LinkedHashSet<Variable<T>> uknVar = new LinkedHashSet<>();

    public ListEquals(List<ValueGetter<T>> valueGetterList){
        this.valueGetterList = valueGetterList;
        this.valueGetterList.forEach(valueGetter -> uknVar.addAll(valueGetter.getUnknownVariables()));
    }

    @Override
    public ConstraintResult satisfied() {

        for(int i = 0; i<valueGetterList.size(); i++) {
            T v = valueGetterList.get(i).getValue();
            if(v == null)
                return ConstraintResult.UNKNOWN;
            for (int y = i + 1; y < valueGetterList.size(); y++)
                if (v.compareTo(valueGetterList.get(y).getValue()) != 0)
                    return ConstraintResult.FALSE;
        }

        return ConstraintResult.TRUE;
    }

    @Override
    public LinkedHashSet<Variable<T>> getUnknownVariables() {
        return uknVar;
    }

}
