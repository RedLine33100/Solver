package fr.unk.variable;

import java.util.Map;

public class Cst<T> implements Variable<T>{

    String varName;
    T value;

    public Cst(String varName, T value){
        this.varName = varName;
        this.value = value;
    }

    @Override
    public T getValue(Map<String, Object> maps) {
        return value;
    }
}
