package fr.unk.contrainte.vc;

import fr.unk.contrainte.Constraint;
import fr.unk.domaine.DomainMap;
import fr.unk.util.Pair;
import fr.unk.variable.Getter;
import fr.unk.variable.Variable;
import fr.unk.variable.numvar.Calcul;

import java.util.List;

public class Diff<T extends Comparable<T>> extends Constraint<T> {

    final Getter<T> fv;
    final Getter<T> sv;

    public Diff(Getter<T> fv, Getter<T> sv){
        super(fv, sv);
        this.fv = fv;
        this.sv = sv;
    }

    public Diff(T fv, Getter<T> sv){
        this(new Getter<>(fv), sv);
    }

    public Diff(Getter<T> fv, T sv){
        this(fv, new Getter<>(sv));
    }

    @Override
    public Boolean trySatisfied() {
        T f1 = fv.getValue();
        T f2 = sv.getValue();
        if(f1 == null || f2 == null)
            return null;
        return f1.compareTo(f2) != 0;
    }

    private void red(DomainMap<T> domainMap, Getter<T> alreadySet, Variable<T> notSet){

        if(notSet.getValue() != null || alreadySet.getValue() == null)
            return;

        List<T> domainList;
        T toRemove = alreadySet.getValue();

        if(notSet.isCalcul()){

            Pair<Variable<T>, T> pair = ((Calcul<T>) notSet).getRevert(alreadySet.getValue());
            if(pair == null)
                return;
            domainList = domainMap.getDomain(pair.getL()).getPossibility();
            toRemove = pair.getR();

        }else {
            domainList = domainMap.getDomain(notSet).getPossibility();
            notSet.invalidate();
        }

        domainList.remove(toRemove);

    }

    @Override
    public void reduceDomain(DomainMap<T> domainMap){

        if(sv instanceof Variable<T>)
            this.red(domainMap, fv, (Variable<T>) sv);
        if(fv instanceof Variable<T>)
            this.red(domainMap, sv, (Variable<T>) fv);

    }

}