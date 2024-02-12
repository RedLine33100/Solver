package fr.unk.variable;

import java.util.Map;

public class Var<T> implements Variable<T>{
    String varName;
    Class<T> tClass;

    public Var(String varName, Class<T> tClass){
        this.varName = varName;
        this.tClass = tClass;
    }

    @Override
    public T getValue(Map<String, Object> maps) {
        Object value = maps.get(varName);
        if(tClass.isInstance(value))
            return (T) value;
        return null;
    }
}
