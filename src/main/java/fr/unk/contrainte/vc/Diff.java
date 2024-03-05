package fr.unk.contrainte.vc;

import fr.unk.contrainte.Constraint;
import fr.unk.variable.VarGetter;

import java.util.Map;

public class Diff<T extends Comparable<T>> implements Constraint {

    final VarGetter<T> fv;
    final VarGetter<T> sv;

    public Diff(VarGetter<T> fv, VarGetter<T> sv){
        this.fv = fv;
        this.sv = sv;
    }

    public Diff(T fv, VarGetter<T> sv){
        this.fv = new VarGetter<>(fv);
        this.sv = sv;
    }

    public Diff(VarGetter<T> fv, T sv){
        this.fv = fv;
        this.sv = new VarGetter<>(sv);
    }

    @Override
    public boolean satisfied(Map<String, Object> objectMap) {
        T f1 = fv.getValue(objectMap);
        T f2 = sv.getValue(objectMap);
        if(f1 == null || f2 == null)
            return false;
        return f1.compareTo(f2) != 0;
    }

}