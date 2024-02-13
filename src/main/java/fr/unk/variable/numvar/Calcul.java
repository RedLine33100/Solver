package fr.unk.variable.numvar;

import fr.unk.utils.Pair;
import fr.unk.variable.Var;
import fr.unk.variable.Variable;

import java.util.*;
import java.util.function.BinaryOperator;

public abstract class Calcul<T extends Number> extends Var<T> {

    List<Pair<BinaryOperator<T>, Variable<T>>> operatorList;

    public Calcul(String varName, Class<T> tClass, List<Pair<BinaryOperator<T>, Variable<T>>> operatorList) {
        super(varName, tClass);
        this.operatorList = operatorList;
    }

    public Calcul(String varName, Class<T> tClass) {
        this(varName, tClass, new ArrayList<>());
    }

    public Calcul(T valueDefined, List<Pair<BinaryOperator<T>, Variable<T>>> operatorList) {
        super(valueDefined);
        this.operatorList = operatorList;
    }

    public Calcul(T valueDefined) {
        this(valueDefined, new ArrayList<>());
    }

    public abstract Calcul<T> add(Variable<T> variable);
    public abstract Calcul<T> remove(Variable<T> variable);
    public abstract Calcul<T> divide(Variable<T> variable);
    public abstract Calcul<T> multiply(Variable<T> variable);
    public abstract Calcul<T> modulo(Variable<T> variable);

}
