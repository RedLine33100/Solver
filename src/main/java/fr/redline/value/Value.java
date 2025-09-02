package fr.redline.value;

import fr.redline.value.variable.VarType;
import fr.redline.value.variable.Variable;

import java.util.LinkedHashSet;

public class Value<T> implements ValueGetter<T>{

    private final T value;

    public Value(T value){
        this.value = value;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public VarType getType() {
        return VarType.ALREADY_CALCULATED;
    }

    @Override
    public LinkedHashSet<Variable<T>> getUnknownVariables() {
        return new LinkedHashSet<>();
    }
}
