package fr.unk.variable;

import java.util.Map;

public class Variable<T> extends VarGetter<T>{
    final String varName;
    final Class<T> tClass;

    public Variable(String varName, Class<T> tClass){
        super(null);
        this.varName = varName;
        this.tClass = tClass;
    }

    public String getVarName(){
        return this.varName;
    }

    @Override
    public T getValue(Map<String, Object> maps) {
        if(this.tClass == null || this.varName == null)
            return null;

        Object value = maps.get(varName);
        if(tClass.isInstance(value))
            return tClass.cast(value);
        return null;
    }
}
