package fr.redline.contrainte;

import fr.redline.value.variable.Variable;

import java.util.LinkedHashSet;

public interface Constraint<T> {

    ConstraintResult evaluate();
    LinkedHashSet<Variable<T>> getUnknownVariables();

}
