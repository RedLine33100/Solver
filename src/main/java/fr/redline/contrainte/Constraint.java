package fr.redline.contrainte;

import fr.redline.utils.Pair;
import fr.redline.value.Value;
import fr.redline.value.variable.Variable;

import java.util.LinkedHashSet;
import java.util.List;

public interface Constraint<T> {

    ConstraintResult evaluate();
    LinkedHashSet<Variable<T>> getUnknownVariables();
    Pair<List<Value<T>>, Integer> reverseVariables(T reversedValue);
    Pair<List<Value<T>>, Integer> tryReverse();
}
