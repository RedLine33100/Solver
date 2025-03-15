package fr.redline.contrainte;

import java.util.Map;

public interface Constraint<T> {

    ConstraintResult satisfied(Map<String, T> objectMap);

}
