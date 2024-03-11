package fr.unk.contrainte.vc;

import fr.unk.contrainte.Constraint;
import fr.unk.domaine.Domain;
import fr.unk.domaine.DomainMap;
import fr.unk.variable.Getter;
import fr.unk.variable.Variable;

import java.util.List;

public class Inf<T extends Comparable<T>> extends Constraint<T> {

    final Getter<T> fv;
    final Getter<T> sv;

    final boolean equals;

    public Inf(Getter<T> fv, Getter<T> sv, boolean equals){
        super(fv, sv);
        this.fv = fv;
        this.sv = sv;
        this.equals = equals;
    }

    public Inf(T fv, Getter<T> sv, boolean equals){
        this(new Getter<>(fv), sv, equals);
    }

    public Inf(Getter<T> fv, T sv, boolean equals){
        this(fv, new Getter<>(sv), equals);
    }

    @Override
    public boolean satisfied() {
        T f1 = fv.getValue();
        T f2 = sv.getValue();
        if(f1 == null || f2 == null)
            return false;
        int val = f1.compareTo(f2);
        return equals ? val <= 0 : val < 0;
    }

    @Override
    public void reduceDomain(DomainMap<T> domainMap){

        T fValue = fv.getValue(), sValue = sv.getValue();
        if(fValue == null && sValue == null)
            return;

        if(fValue != null && sValue != null)
            return;

        List<Variable<T>> getters = Constraint.getTotalUnknown((fValue == null) ? this.getVarOnLeft() : this.getVarOnRight());

        if(getters.size() != 1)
            return;

        Variable<T> sVar = getters.getFirst();
        Domain<T> sDomain = domainMap.getDomain(sVar);

        for(T possibility : sDomain.duplicate().getPossibility()){
            sVar.setValue(possibility);
            if(!satisfied())
                sDomain.getPossibility().remove(possibility);
        }

    }

}