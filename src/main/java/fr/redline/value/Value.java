package fr.redline.value;

import fr.redline.value.variable.VarType;

public class Value<T> extends ValueGetter<T>{

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

}
