package fr.unk.contrainte.vc;

import fr.unk.contrainte.Constraint;
import fr.unk.variable.VarGetter;

import java.util.Map;

public class Sup<T extends Comparable<T>> extends Constraint<T> {

    final VarGetter<T> fv;
    final VarGetter<T> sv;

    final boolean equals;

    public Sup(VarGetter<T> fv, VarGetter<T> sv, boolean equals){
        super(fv, sv);
        this.fv = fv;
        this.sv = sv;
        this.equals = equals;
    }

    public Sup(T fv, VarGetter<T> sv, boolean equals){
        this(new VarGetter<>(fv), sv, equals);
    }

    public Sup(VarGetter<T> fv, T sv, boolean equals){
        this(fv, new VarGetter<>(sv), equals);
    }

    @Override
    public boolean satisfied(Map<String, Object> objectMap) {
        T f1 = fv.getValue(objectMap);
        T f2 = sv.getValue(objectMap);
        if(f1 == null || f2 == null)
            return false;
        int val = f1.compareTo(f2);
        return equals ? val >= 0 : val > 0;
    }

}
