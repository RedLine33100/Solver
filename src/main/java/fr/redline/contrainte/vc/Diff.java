package fr.redline.contrainte.vc;

import fr.redline.contrainte.Constraint;
import fr.redline.contrainte.ConstraintResult;
import fr.redline.value.Value;
import fr.redline.value.ValueGetter;

import java.util.Map;

public class Diff<T extends Comparable<T>> implements Constraint<T> {

    final ValueGetter<T> fv;
    final ValueGetter<T> sv;

    public Diff(ValueGetter<T> fv, ValueGetter<T> sv){
        this.fv = fv;
        this.sv = sv;
    }

    public Diff(T fv, ValueGetter<T> sv){
        this.fv = new Value<>(fv);
        this.sv = sv;
    }

    public Diff(ValueGetter<T> fv, T sv){
        this.fv = fv;
        this.sv = new Value<>(sv);
    }

    @Override
    public ConstraintResult satisfied(Map<String, T> objectMap) {

        T vf = fv.getValue(objectMap);
        if(vf == null)
            return ConstraintResult.UNKNOWN;

        T vs = sv.getValue(objectMap);
        if(vs == null)
            return ConstraintResult.UNKNOWN;

        return vf.compareTo(vs) != 0 ? ConstraintResult.TRUE : ConstraintResult.FALSE;
    }

}