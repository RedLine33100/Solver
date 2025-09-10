package fr.redline.value.numvar;

import fr.redline.domaine.Domain;
import fr.redline.utils.Pair;
import fr.redline.utils.Triplet;
import fr.redline.value.Variable;
import fr.redline.value.VarType;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.function.BinaryOperator;

public abstract class Calcul<T> extends Variable<T> {

    protected final Variable<T> previous;
    protected final Triplet<BinaryOperator<T>, BinaryOperator<T>, Variable<T>> operator;

    public Calcul(String name, T current) {
        super(name, current);
        this.previous = null;
        this.operator = null;
    }

    public Calcul(String name, Domain<T> domain) {
        super(name, domain);
        this.previous = null;
        this.operator = null;
    }

    public Calcul(Variable<T> previous, Triplet<BinaryOperator<T>, BinaryOperator<T>, Variable<T>> operator) {
        super(previous.getName());
        this.previous = previous;
        this.operator = operator;
        previous.registerLinkedValue(this);
        if(operator != null) {
            operator.v().registerLinkedValue(this);
        }
    }

    abstract Calcul<T> add(Variable<T> variable);
    public Calcul<T> add(T variable){
        return this.add(new Variable<>(variable));
    }

    abstract Calcul<T> remove(Variable<T> variable);
    public Calcul<T> remove(T variable){
        return this.remove(new Variable<>(variable));
    }

    public abstract Calcul<T> divide(Variable<T> variable);

    public Calcul<T> divide(T variable){
        return this.divide(new Variable<>(variable));
    }

    public abstract Calcul<T> multiply(Variable<T> variable);
    public Calcul<T> multiply(T variable){
        return this.multiply(new Variable<>(variable));
    }

    public abstract Calcul<T> modulo(Variable<T> variable);
    public Calcul<T> modulo(T variable){
        return this.modulo(new Variable<>(variable));
    }

    @Override
    public T getValue() {

        T superValue = super.getValue();
        if(superValue != null){
            return superValue;
        }

        T value = previous.getValue();
        if(value == null)
            return null;

        if(operator == null)
            return value;

        T opValue = operator.v().getValue();
        if(opValue == null)
            return null;

        super.setValue(operator.l().apply(value, opValue));
        return this.getValue();
    }

    public Pair<List<Variable<T>>, Integer> reverseVariables(T reversedValue){
        T previousValue = previous.getValue();
        T previousRightValue = null;

        List<Variable<T>> changedVariables = new ArrayList<>();
        int unknownCount = 0;

        if(operator != null)
            previousRightValue = operator.v().getValue();

        if(previousValue == null && previousRightValue != null){

            previousValue = operator.r().apply(reversedValue, previousRightValue);
            if(previous.getType() == VarType.CALCULATED) {
                Pair<List<Variable<T>>, Integer> pair = ((Calcul<T>) previous).reverseVariables(previousValue);
                changedVariables.addAll(pair.l());
                unknownCount += pair.r();
            }else {
                previous.setValue(previousValue);
                changedVariables.add(previous);
                unknownCount += 1;
            }

        }else if(previousValue != null && operator != null && previousRightValue == null){

            previousRightValue = operator.r().apply(reversedValue, previousValue);
            if(operator.v().getType() == VarType.CALCULATED) {
                Pair<List<Variable<T>>, Integer> pair = ((Calcul<T>) operator.v()).reverseVariables(previousRightValue);
                changedVariables.addAll(pair.l());
                unknownCount += pair.r();
            }else {
                operator.v().setValue(previousRightValue);
                changedVariables.add(operator.v());
                unknownCount += 1;
            }

        }

        if(super.getValue() == null){
            super.setValue(reversedValue);
            changedVariables.add(this);
            unknownCount += 1;
        }

        return new Pair<>(changedVariables, unknownCount);
    }

    @Override
    public VarType getType() {
        return VarType.CALCULATED;
    }

    @Override
    public LinkedHashSet<Variable<T>> getUnknownVariables() {
        LinkedHashSet<Variable<T>> vars = previous.getUnknownVariables();
        if(operator != null)
            vars.addAll(operator.v().getUnknownVariables());
        return vars;
    }
}
