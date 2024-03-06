package fr.unk.variable;

import java.util.Map;

public class VarGetter<T> {

    T t;

    public VarGetter(T variable){
        if(variable instanceof VarGetter<?>)
            throw new NullPointerException("VarGetter cannot be VarGetter of himself ");
        this.t = variable;
    }

    public boolean isVar(){
        return false;
    }

    public boolean isCalcul(){
        return false;
    }

    public T getValue(Map<String, Object> map){
        return this.t;
    }

}
