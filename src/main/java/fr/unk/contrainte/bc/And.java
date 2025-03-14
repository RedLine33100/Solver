package fr.unk.contrainte.bc;

import fr.unk.contrainte.Constraint;

import java.util.Map;

public class And<T> implements Constraint<T> {
    final Constraint<T> c1;
    final Constraint<T> c2;

    public And(Constraint<T> c1, Constraint<T> c2){
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public boolean satisfied(Map<String, T> objectMap) {
        return this.c1.satisfied(objectMap) && this.c2.satisfied(objectMap);
    }
}
