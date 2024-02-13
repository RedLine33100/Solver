package fr.unk.variable;

import java.util.Map;

public class Var<T> implements Variable<T>{
    final String varName;
    final Class<T> tClass;
    final T valueDefined;

    public Var(String varName, Class<T> tClass){
        this.varName = varName;
        this.tClass = tClass;
        this.valueDefined = null;
    }

    public Var(T valueDefined){
        this.varName = null;
        this.tClass = null;
        this.valueDefined = valueDefined;
    }

    @Override
    public T getValue(Map<String, Object> maps) {
        if (this.valueDefined != null)
            return this.valueDefined;

        if(this.tClass == null || this.varName == null)
            return null;

        Object value = maps.get(varName);
        if(tClass.isInstance(value))
            return tClass.cast(value);
        return null;
    }
}
