package fr.unk.contrainte;

import java.util.Map;

public interface Constraint {

    boolean satisfied(Map<String, Object> objectMap);

}
