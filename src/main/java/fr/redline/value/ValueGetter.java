package fr.redline.value;

import fr.redline.value.variable.VarType;

public interface ValueGetter<T> {

    T getValue();
    VarType getType();

}
