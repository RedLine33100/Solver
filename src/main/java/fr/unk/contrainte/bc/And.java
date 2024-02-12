package fr.unk.contrainte.bc;

import fr.unk.contrainte.Constraint;

import java.util.Map;

public class And implements Constraint {
    final Constraint c1;
    final Constraint c2;

    public And(Constraint c1, Constraint c2){
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public boolean satisfied(Map<String, Object> objectMap) {
        return this.c1.satisfied(objectMap) && this.c2.satisfied(objectMap);
    }
}
