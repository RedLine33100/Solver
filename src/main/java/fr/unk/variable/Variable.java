package fr.unk.variable;

import fr.unk.domaine.Domain;

import java.util.Map;

public class Variable<T> extends VarGetter<T>{
    final String varName;
    final Domain<T> domain;

    public Variable(String varName, Domain<T> domain){
        super(null);
        this.varName = varName;
        this.domain = domain;
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

    public Domain<T> getDomain(){
        return this.domain;
    }
}
