package fr.unk.variable;

import java.util.Map;

public class VarGetter<T> {

    T t;

    public VarGetter(T variable){
        this.t = variable;
    }

    public boolean isVar(){
        return t == null;
    }

    public T getValue(Map<String, Object> map){
        return this.t;
    }

}
