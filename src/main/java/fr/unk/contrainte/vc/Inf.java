package fr.unk.contrainte.vc;

import fr.unk.contrainte.Constraint;
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
    public boolean satisfied(Map<String, T> objectMap) {
        int val = fv.getValue(objectMap).compareTo(sv.getValue(objectMap));
        return equals ? val <= 0 : val < 0;
    }

}