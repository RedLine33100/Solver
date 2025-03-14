package fr.unk.variable.numvar;

import fr.unk.utils.Pair;
import fr.unk.variable.VarGetter;
import fr.unk.variable.Variable;

import java.util.*;
import java.util.function.BinaryOperator;

public abstract class Calcul<T extends Number> extends VarGetter<T> {

    VarGetter<T> previous;
    Pair<BinaryOperator<T>, VarGetter<T>> operator;

    public Calcul(VarGetter<T> previous, Pair<BinaryOperator<T>, VarGetter<T>> operator) {
        super(null);
        this.previous = previous;
        this.operator = operator;
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
    public T getValue(Map<String, T> maps) {
        T value = previous.getValue(maps);
        if(value == null)
            return null;
        if(operator == null)
            return value;
        return operator.getL().apply(value, operator.getR().getValue(maps));
    }

}
