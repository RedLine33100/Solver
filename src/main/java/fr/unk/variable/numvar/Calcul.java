package fr.unk.variable.numvar;

import fr.unk.utils.Pair;
import fr.unk.variable.Var;
import fr.unk.variable.Variable;

import java.util.*;
import java.util.function.BinaryOperator;

public abstract class Calcul<T extends Number> extends Var<T> {

    final List<Pair<BinaryOperator<T>, Variable<T>>> operatorList = new ArrayList<>();

    public Calcul(String varName, Class<T> tClass) {
        super(varName, tClass);
    }

    public Calcul(T valueDefined) {
        super(valueDefined);
    }

    protected void addCalcul(BinaryOperator<T> binaryOperator, Variable<T> variable){
        this.operatorList.add(new Pair<>(binaryOperator, variable));
    }

    public abstract void add(Variable<T> variable);
    public abstract void remove(Variable<T> variable);
    public abstract void divide(Variable<T> variable);
    public abstract void multiply(Variable<T> variable);
    public abstract void modulo(Variable<T> variable);

}
