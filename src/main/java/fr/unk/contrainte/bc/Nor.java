package fr.unk.contrainte.bc;

import fr.unk.contrainte.Constraint;
import fr.unk.domaine.DomainMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Nor<T> extends Constraint<T> {

    final Constraint<T> c1;
    final Constraint<T> c2;

    public Nor(Constraint<T> c1, Constraint<T> c2){
        super(
                new ArrayList<>(){{addAll(c1.getVarOnLeft()); addAll(c2.getVarOnLeft());}},
                new ArrayList<>(){{addAll(c1.getVarOnRight()); addAll(c2.getVarOnRight());}}
        );
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public boolean satisfied() {
        boolean c1s = this.c1.satisfied();
        boolean c2s = this.c2.satisfied();

        return (c1s && !c2s) || (!c1s && c2s);
    }

    @Override
    public List<DomainMap<T>> reduceDomain(DomainMap<T> domainMap){
        ArrayList<DomainMap<T>> domainMapArrayList = new ArrayList<>();
        domainMapArrayList.addAll(this.c1.reduceDomain(domainMap));
        domainMapArrayList.addAll(this.c2.reduceDomain(domainMap));
        return domainMapArrayList;
    }

}
