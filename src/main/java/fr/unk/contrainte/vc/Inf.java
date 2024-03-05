package fr.unk.contrainte.vc;

import fr.unk.contrainte.Constraint;
import fr.unk.variable.VarGetter;

import java.util.Map;

public class Inf<T extends Comparable<T>> implements Constraint {

    final VarGetter<T> fv;
    final VarGetter<T> sv;

    final boolean equals;

    public Inf(VarGetter<T> fv, VarGetter<T> sv, boolean equals){
        this.fv = fv;
        this.sv = sv;
        this.equals = equals;
    }

    public Inf(T fv, VarGetter<T> sv, boolean equals){
        this.fv = new VarGetter<>(fv);
        this.sv = sv;
        this.equals = equals;
    }

    public Inf(VarGetter<T> fv, T sv, boolean equals){
        this.fv = fv;
        this.sv = new VarGetter<>(sv);
        this.equals = equals;
    }

    @Override
    public boolean satisfied(Map<String, Object> objectMap) {
        T f1 = fv.getValue(objectMap);
        T f2 = sv.getValue(objectMap);
        if(f1 == null || f2 == null)
            return false;
        int val = f1.compareTo(f2);
        return equals ? val <= 0 : val < 0;
    }

}