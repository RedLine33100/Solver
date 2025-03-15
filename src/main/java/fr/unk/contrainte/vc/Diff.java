package fr.unk.contrainte.vc;

import fr.unk.contrainte.Constraint;
import fr.unk.contrainte.ConstraintResult;
import fr.unk.variable.VarGetter;

import java.util.Map;

public class Diff<T extends Comparable<T>> implements Constraint<T> {

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