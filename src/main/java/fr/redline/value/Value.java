package fr.redline.value;

import fr.redline.value.variable.VarType;

import java.util.Map;

public class Value<T> implements ValueGetter<T>{

    private final T value;

    public Value(T value){
        this.value = value;
    }

    @Override
    public T getValue(Map<String, T> map) {
        return value;
    }

    @Override
    public VarType getType() {
        return VarType.ALREADY_CALCULATED;
    }
}
