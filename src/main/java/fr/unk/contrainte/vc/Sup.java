package fr.unk.contrainte.vc;

import fr.unk.contrainte.Constraint;
import fr.unk.domaine.Domain;
import fr.unk.domaine.DomainMap;
import fr.unk.variable.Getter;
import fr.unk.variable.Variable;

/**
 * Check if two value are respectinf the inf or equals to constraint
 * @param <T> the Type of the value which need to be comparable
 */
public class Sup<T extends Comparable<T>> extends Constraint<T> {

    final Getter<T> fv;
    final Getter<T> sv;

    final boolean equals;

    public Sup(Getter<T> fv, Getter<T> sv, boolean equals){
        super(fv, sv);
        this.fv = fv;
        this.sv = sv;
        this.equals = equals;
    }

    public Sup(T fv, Getter<T> sv, boolean equals){
        this(new Getter<>(fv), sv, equals);
    }

    public Sup(Getter<T> fv, T sv, boolean equals){
        this(fv, new Getter<>(sv), equals);
    }

    @Override
    public Boolean trySatisfied() {
        T f1 = fv.getValue();
        T f2 = sv.getValue();
        if(f1 == null || f2 == null)
            return null;
        int val = f1.compareTo(f2);
        return equals ? val >= 0 : val > 0;
    }

    @Override
    public boolean reduceDomain(DomainMap<T> domainMap){

        T fValue = fv.getValue(), sValue = sv.getValue();
        if(fValue == null && sValue == null)
            return true;

        if(fValue != null && sValue != null)
            return true;

        Variable<T> sVar = null;

        for (Variable<T> checkVar : (fValue == null) ? this.getVarOnLeft() : this.getVarOnRight()) {
            if (checkVar.getValue() == null) {
                if (sVar == null)
                    sVar = checkVar;
                else
                    return true;
            }
        }

        if (sVar == null)
            return true;

        Domain<T> sDomain = domainMap.getDomain(sVar);

        for(T possibility : sDomain.duplicate().getPossibility()){
            sVar.setValue(possibility);
            if(!satisfied()) {
                sDomain.getPossibility().remove(possibility);
                if(sDomain.getPossibility().isEmpty())
                    return false;
            }
        }

        return true;
    }

}
