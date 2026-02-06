package fr.redline.value.numvar;

import fr.redline.contrainte.reduction.ReductionResult;
import fr.redline.domaine.Domain;
import fr.redline.utils.OptimizedList;
import fr.redline.utils.Triplet;
import fr.redline.value.VarType;
import fr.redline.value.Variable;

import java.util.function.BinaryOperator;

public abstract class Calcul<T> extends Variable<T> {

    protected final Variable<T> previous;
    protected final Triplet<BinaryOperator<T>, BinaryOperator<T>, Variable<T>> operator;
    protected VarType type = VarType.CALCULATED;

    public Calcul(String name, T current) {
        super(name, current);
        this.previous = null;
        this.operator = null;
        this.type = VarType.ALREADY_CALCULATED;
    }

    public Calcul(String name, Domain<T> domain) {
        super(name, domain);
        this.previous = null;
        this.operator = null;
        this.type = VarType.UNKNOWN;
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

        T opValue = operator.v().getValue();
        if (opValue == null)
            return null;

        super.setValue(operator.l().apply(value, opValue));
        return super.getValue();
    }

    public boolean reverseVariables(ReductionResult<T> reductionResult, T reversedValue, boolean reduce) {

        Variable<T> toChangeVar;
        T newValue;

        if (previous == null) {

            toChangeVar = this;
            newValue = reversedValue;

        } else {

            T previousValue = previous.getValue();
            T previousRightValue = operator.v().getValue();

            if (previousValue == null && previousRightValue == null) {
                return true;
            }

            if (previousValue == null) {

                toChangeVar = previous;
                newValue = operator.r().apply(reversedValue, previousRightValue);

            } else {

                toChangeVar = operator.v();
                newValue = operator.r().apply(reversedValue, previousValue);

            }

        }

        boolean result;

        if (toChangeVar.getType() == VarType.CALCULATED)
            result = ((Calcul<T>) toChangeVar).reverseVariables(reductionResult, newValue, reduce);
        else {
            if (!reduce)
                result = reductionResult.getVariableChange(toChangeVar).setValue(newValue);
            else {
                reductionResult.getVariableChange(toChangeVar).varDomainReduce(newValue);
                result = true;
            }
        }

        return result;

    }

    @Override
    public VarType getType() {
        return type;
    }

    @Override
    public OptimizedList<Variable<T>> getUnknownVariables() {
        if (previous == null)
            return new OptimizedList<>();
        OptimizedList<Variable<T>> vars = previous.getUnknownVariables();
        operator.v().getUnknownVariables().activeValues().forEach(vars::add);
        return vars;
    }
}
