package fr.unk.contrainte;

import java.util.Map;

public interface Constraint<T> {

    boolean satisfied(Map<String, T> objectMap);

}
