package fr.unk.contrainte.bc;

import fr.unk.contrainte.Constraint;
import fr.unk.domaine.DomainMap;

import java.util.ArrayList;
import java.util.List;

public class And<T> extends Constraint<T> {
    final Constraint<T> c1;
    final Constraint<T> c2;

    public And(Constraint<T> c1, Constraint<T> c2){
        super(
                new ArrayList<>(){{addAll(c1.getVarOnLeft()); addAll(c2.getVarOnLeft());}},
                new ArrayList<>(){{addAll(c1.getVarOnRight()); addAll(c2.getVarOnRight());}}
        );
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public boolean satisfied() {
        return this.c1.satisfied() && this.c2.satisfied();
    }

    @Override
    public void reduceDomain(DomainMap<T> domainMap){
        this.c1.reduceDomain(domainMap);
        this.c2.reduceDomain(domainMap);
    }

}
