package fr.unk.contrainte.bc;

import fr.unk.contrainte.Constraint;

import java.util.ArrayList;
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
    public boolean satisfied(Map<String, T> objectMap) {
        boolean c1s = this.c1.satisfied(objectMap);
        boolean c2s = this.c2.satisfied(objectMap);

        return (c1s && !c2s) || (!c1s && c2s);
    }

}
