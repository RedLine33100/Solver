package fr.redline.value;

import fr.redline.value.variable.VarType;
import fr.redline.value.variable.Variable;

import java.util.LinkedHashSet;

public interface ValueGetter<T> {

    T getValue();
    VarType getType();
    LinkedHashSet<Variable<T>> getUnknownVariables();

}
