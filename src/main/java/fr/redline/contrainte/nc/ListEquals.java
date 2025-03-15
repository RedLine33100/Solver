package fr.redline.contrainte.nc;

import fr.redline.contrainte.Constraint;
import fr.redline.contrainte.ConstraintResult;
import fr.redline.value.ValueGetter;

import java.util.List;
import java.util.Map;

public class ListEquals<T extends Comparable<T>> implements Constraint<T> {

    final List<ValueGetter<T>> ValueGetterList;

    public ListEquals(List<ValueGetter<T>> ValueGetters){
        this.ValueGetterList = ValueGetters;
    }

    @Override
    public ConstraintResult satisfied() {

        for(int i = 0; i<ValueGetterList.size(); i++) {
            T v = ValueGetterList.get(i).getValue();
            if(v == null)
                return ConstraintResult.UNKNOWN;
            for (int y = i + 1; y < ValueGetterList.size(); y++)
                if (v.compareTo(ValueGetterList.get(y).getValue()) != 0)
                    return ConstraintResult.FALSE;
        }

        return ConstraintResult.TRUE;
    }

}
