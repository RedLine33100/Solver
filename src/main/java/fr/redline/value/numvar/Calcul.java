package fr.redline.value.numvar;

import fr.redline.utils.Pair;
import fr.redline.value.Value;
import fr.redline.value.ValueGetter;
import fr.redline.value.variable.VarType;
import fr.redline.value.variable.Variable;

import java.util.LinkedHashSet;
import java.util.function.BinaryOperator;

public abstract class Calcul<T extends Number> implements ValueGetter<T> {

    protected final ValueGetter<T> previous;
    protected final Pair<BinaryOperator<T>, ValueGetter<T>> operator;
    protected LinkedHashSet<Variable<T>> variables = new LinkedHashSet<>();

    public Calcul(ValueGetter<T> previous, Pair<BinaryOperator<T>, ValueGetter<T>> operator) {
        this.previous = previous;
        this.operator = operator;
        this.variables.addAll(previous.getUnknownVariables());
        if(operator != null)
            this.variables.addAll(operator.r().getUnknownVariables());
    }

    abstract Calcul<T> add(ValueGetter<T> valueGetter);
    public Calcul<T> add(T variable){
        return this.add(new Value<>(variable));
    }

    abstract Calcul<T> remove(ValueGetter<T> valueGetter);
    public Calcul<T> remove(T variable){
        return this.remove(new Value<>(variable));
    }

    public abstract Calcul<T> divide(ValueGetter<T> variable);

    public Calcul<T> divide(T variable){
        return this.divide(new Value<>(variable));
    }

    public abstract Calcul<T> multiply(ValueGetter<T> variable);
    public Calcul<T> multiply(T variable){
        return this.multiply(new Value<>(variable));
    }

    public abstract Calcul<T> modulo(ValueGetter<T> variable);
    public Calcul<T> modulo(T variable){
        return this.modulo(new Value<>(variable));
    }

    @Override
    public T getValue() {
        T value = previous.getValue();
        if(value == null)
            return null;
        if(operator == null)
            return value;
        return operator.l().apply(value, operator.r().getValue());
    }

    @Override
    public VarType getType() {
        return VarType.CALCULATED;
    }

    @Override
    public LinkedHashSet<Variable<T>> getUnknownVariables(){
        return variables;
    }

}
