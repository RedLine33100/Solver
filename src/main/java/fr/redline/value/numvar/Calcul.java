package fr.redline.value.numvar;

import fr.redline.contrainte.reduction.ReductionResult;
import fr.redline.domaine.Domain;
import fr.redline.utils.Triplet;
import fr.redline.value.VarType;
import fr.redline.value.Variable;

import java.util.LinkedHashSet;
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
        if (operator != null) {
            operator.v().registerLinkedValue(this);
        }
    }

    abstract Calcul<T> add(Variable<T> variable);

    public Calcul<T> add(T variable) {
        return this.add(new Variable<>(variable));
    }

    abstract Calcul<T> remove(Variable<T> variable);

    public Calcul<T> remove(T variable) {
        return this.remove(new Variable<>(variable));
    }

    public abstract Calcul<T> divide(Variable<T> variable);

    public Calcul<T> divide(T variable) {
        return this.divide(new Variable<>(variable));
    }

    public abstract Calcul<T> multiply(Variable<T> variable);

    public Calcul<T> multiply(T variable) {
        return this.multiply(new Variable<>(variable));
    }

    public abstract Calcul<T> modulo(Variable<T> variable);

    public Calcul<T> modulo(T variable) {
        return this.modulo(new Variable<>(variable));
    }

    @Override
    public T getValue() {

        T superValue = super.getValue();
        if (superValue != null) {
            return superValue;
        }

        if (previous == null)
            return null;

        T value = previous.getValue();
        if (value == null)
            return null;

        if (operator == null)
            return value;

        T opValue = operator.v().getValue();
        if (opValue == null)
            return null;

        super.setValue(operator.l().apply(value, opValue));
        return super.getValue();
    }

    public void reverseVariables(ReductionResult<T> reductionResult, T reversedValue, boolean reduce) {

        if (previous == null) {

            if (!reduce)
                reductionResult.getVariableChange(this).setValue(reversedValue);
            else
                reductionResult.getVariableChange(this).varDomainReduce(reversedValue);
            return;

        }

        if (!reduce && super.getValue() == null) {
            super.setValue(reversedValue);
        }


        T previousValue = previous.getValue();
        T previousRightValue = null;

        if (operator != null)
            previousRightValue = operator.v().getValue();

        Variable<T> toChangeVar = null;
        T newValue = null;

        if (previousValue == null && previousRightValue != null) {

            toChangeVar = previous;
            newValue = operator.r().apply(reversedValue, previousRightValue);

        } else if (previousValue != null && operator != null && previousRightValue == null) {

            toChangeVar = operator.v();
            newValue = operator.r().apply(reversedValue, previousValue);

        }

        if (toChangeVar == null)
            return;

        if (toChangeVar.getType() == VarType.CALCULATED)
            ((Calcul<T>) toChangeVar).reverseVariables(reductionResult, newValue, reduce);
        else {

            if (!reduce)
                toChangeVar.setValue(newValue);
            else
                reductionResult.getVariableChange(toChangeVar).varDomainReduce(newValue);

        }
    }

    @Override
    public VarType getType() {
        return VarType.CALCULATED;
    }

    @Override
    public LinkedHashSet<Variable<T>> getUnknownVariables() {
        if (previous == null)
            return new LinkedHashSet<>();
        LinkedHashSet<Variable<T>> vars = previous.getUnknownVariables();
        if (operator != null)
            vars.addAll(operator.v().getUnknownVariables());
        return vars;
    }
}
