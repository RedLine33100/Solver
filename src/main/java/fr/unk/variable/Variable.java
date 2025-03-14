package fr.unk.variable;

import java.util.Map;

public class Variable<T> extends VarGetter<T>{
    final String varName;

    public Variable(String varName){
        super(null);
        this.varName = varName;
    }

    public String getVarName(){
        return this.varName;
    }

    @Override
    public T getValue(Map<String, T> maps) {
        if(this.varName == null)
            return null;
        return maps.get(varName);
    }
}
