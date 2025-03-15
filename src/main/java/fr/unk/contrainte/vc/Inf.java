package fr.unk.contrainte.vc;

import fr.unk.contrainte.Constraint;
import fr.unk.contrainte.ConstraintResult;
import fr.unk.variable.VarGetter;

import java.util.Map;

public class Inf<T extends Comparable<T>> implements Constraint<T> {

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
    public ConstraintResult satisfied(Map<String, T> objectMap) {

        T vf = fv.getValue(objectMap);
        if(vf == null)
            return ConstraintResult.UNKNOWN;
        T vs = sv.getValue(objectMap);
        if(vs == null)
            return ConstraintResult.UNKNOWN;

        int val = vf.compareTo(vs);
        boolean res = equals ? val <= 0 : val < 0;

        return res ? ConstraintResult.TRUE : ConstraintResult.FALSE;
    }

}