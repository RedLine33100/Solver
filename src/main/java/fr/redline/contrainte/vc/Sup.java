package fr.redline.contrainte.vc;

import fr.redline.contrainte.Constraint;
import fr.redline.contrainte.ConstraintResult;
import fr.redline.value.Value;
import fr.redline.value.ValueGetter;

import java.util.Map;

public class Sup<T extends Comparable<T>> implements Constraint<T> {

    final ValueGetter<T> fv;
    final ValueGetter<T> sv;

    final boolean equals;

    public Sup(ValueGetter<T> fv, ValueGetter<T> sv, boolean equals){
        this.fv = fv;
        this.sv = sv;
        this.equals = equals;
    }

    public Sup(T fv, ValueGetter<T> sv, boolean equals){
        this.fv = new Value<>(fv);
        this.sv = sv;
        this.equals = equals;
    }

    public Sup(ValueGetter<T> fv, T sv, boolean equals){
        this.fv = fv;
        this.sv = new Value<>(sv);
        this.equals = equals;
    }

    @Override
    public ConstraintResult satisfied(Map<String, T> objectMap) {

        T vf = fv.getValue(objectMap);
        if(vf == null)
            return ConstraintResult.UNKNOWN;
        T vs = sv.getValue(objectMap);
        if(vs == null)
            return ConstraintResult.UNKNOWN;

        int val = vf.compareTo(vs);
        boolean result = equals ? val >= 0 : val > 0;
        return result ? ConstraintResult.TRUE : ConstraintResult.FALSE;
    }

}
