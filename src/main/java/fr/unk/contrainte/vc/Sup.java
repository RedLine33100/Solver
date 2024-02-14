package fr.unk.contrainte.vc;

import fr.unk.contrainte.Constraint;
import fr.unk.variable.VarGetter;
import fr.unk.variable.Variable;

import java.util.Map;

public class Sup<T extends Comparable<T>> implements Constraint {

    final VarGetter<T> fv;
    final VarGetter<T> sv;

    final boolean equals;

    public Sup(VarGetter<T> fv, VarGetter<T> sv, boolean equals){
        this.fv = fv;
        this.sv = sv;
        this.equals = equals;
    }

    public Sup(T fv, VarGetter<T> sv, boolean equals){
        this.fv = new VarGetter<>(fv);
        this.sv = sv;
        this.equals = equals;
    }

    public Sup(VarGetter<T> fv, T sv, boolean equals){
        this.fv = fv;
        this.sv = new VarGetter<>(sv);
        this.equals = equals;
    }

    @Override
    public boolean satisfied(Map<String, Object> objectMap) {
        int val = fv.getValue(objectMap).compareTo(sv.getValue(objectMap));
        return equals ? val >= 0 : val > 0;
    }

}
