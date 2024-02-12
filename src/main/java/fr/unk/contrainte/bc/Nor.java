package fr.unk.contrainte.bc;

import fr.unk.contrainte.Constraint;

import java.util.Map;

public class Nor implements Constraint {

    final Constraint c1;
    final Constraint c2;

    public Nor(Constraint c1, Constraint c2){
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public boolean satisfied(Map<String, Object> objectMap) {
        boolean c1s = this.c1.satisfied(objectMap);
        boolean c2s = this.c2.satisfied(objectMap);

        return (c1s && !c2s) || (!c1s && c2s);
    }

}
