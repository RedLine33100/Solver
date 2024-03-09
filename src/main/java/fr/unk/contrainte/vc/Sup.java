package fr.unk.contrainte.vc;

import fr.unk.contrainte.Constraint;
import fr.unk.domaine.DomainMap;
import fr.unk.variable.VarGetter;

import java.util.ArrayList;
import java.util.List;
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
    public boolean satisfied() {
        T f1 = fv.getValue();
        T f2 = sv.getValue();
        if(f1 == null || f2 == null)
            return false;
        int val = f1.compareTo(f2);
        return equals ? val >= 0 : val > 0;
    }

    @Override
    public void reduceDomain(DomainMap<T> domainMap){
    }

}
