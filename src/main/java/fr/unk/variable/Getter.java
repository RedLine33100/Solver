package fr.unk.variable;

public class Getter<T> {

    T t;

    public Getter(T variable){
        if(variable instanceof Getter<?>)
            throw new NullPointerException("VarGetter cannot be VarGetter of himself ");
        this.t = variable;
    }

    public boolean isVar(){
        return false;
    }

    public boolean isCalcul(){
        return false;
    }

    public T getValue(){
        return this.t;
    }

}
