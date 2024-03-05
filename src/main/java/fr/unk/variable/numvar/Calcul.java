package fr.unk.variable.numvar;

import fr.unk.variable.VarGetter;
import fr.unk.variable.Variable;

import java.util.*;
import java.util.function.BinaryOperator;

public abstract class Calcul<T extends Number> extends Variable<T> {

    Map<BinaryOperator<T>, VarGetter<T>> operatorList;

    public Calcul(String varName, Class<T> tClass, Map<BinaryOperator<T>, VarGetter<T>> operatorList) {
        super(varName, tClass);
        this.operatorList = operatorList;
    }

    public Calcul(String varName, Class<T> tClass) {
        this(varName, tClass, new HashMap<>());
    }

    abstract Calcul<T> add(VarGetter<T> varGetter);
    public Calcul<T> add(T variable){
        return this.add(new VarGetter<>(variable));
    }

    abstract Calcul<T> remove(VarGetter<T> varGetter);
    public Calcul<T> remove(T variable){
        return this.remove(new VarGetter<>(variable));
    }

    public abstract Calcul<T> divide(VarGetter<T> variable);

    public Calcul<T> divide(T variable){
        return this.divide(new VarGetter<>(variable));
    }

    public abstract Calcul<T> multiply(VarGetter<T> variable);
    public Calcul<T> multiply(T variable){
        return this.multiply(new VarGetter<>(variable));
    }

    public abstract Calcul<T> modulo(VarGetter<T> variable);
    public Calcul<T> modulo(T variable){
        return this.modulo(new VarGetter<>(variable));
    }

    @Override
    public T getValue(Map<String, Object> maps) {
        T value = super.getValue(maps);
        if(value == null)
            return null;
        for(Map.Entry<BinaryOperator<T>, VarGetter<T>> pair : this.operatorList.entrySet()){
            value = pair.getKey().apply(value, pair.getValue().getValue(maps));
        }
        return value;
    }

}
