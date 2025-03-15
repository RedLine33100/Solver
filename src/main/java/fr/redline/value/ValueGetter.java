package fr.redline.value;

import fr.redline.value.variable.VarType;

import java.util.Map;

public interface ValueGetter<T> {

    T getValue(Map<String, T> map);
    VarType getType();

}
