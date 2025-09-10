package fr.redline.contrainte;

import fr.redline.utils.Pair;
import fr.redline.value.Variable;

import java.util.LinkedHashSet;
import java.util.List;

public interface Constraint<T> {

    ConstraintResult evaluate();
    LinkedHashSet<Variable<T>> getUnknownVariables();
    Pair<List<Variable<T>>, Integer> reverseVariables(T reversedValue);
    Pair<List<Variable<T>>, Integer> tryReverse();
}
