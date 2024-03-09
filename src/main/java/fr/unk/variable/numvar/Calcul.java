package fr.unk.variable.numvar;

import fr.unk.util.Pair;
import fr.unk.variable.Getter;
import fr.unk.variable.Variable;

import java.util.ArrayList;
import java.util.List;

public abstract class Calcul<T> extends Variable<T> {

    private final Operation<T> operation;
    private final List<Variable<T>> variableList;

    public Calcul(String varName, Operation<T> operation) {
        super(varName);
        this.operation = operation;
        this.variableList = new ArrayList<>();
        if(this.operation != null) {
            if (this.operation.getVariable().isVar())
                this.variableList.addAll(((Variable<T>) this.operation.getVariable()).getVariableList());
        }
        this.variableList.add(this);
    }

    public Calcul(String varName) {
        this(varName, null);
    }

    abstract Calcul<T> add(Getter<T> getter);
    public Calcul<T> add(T variable){
        return this.add(new Getter<>(variable));
    }

    abstract Calcul<T> remove(Getter<T> getter);
    public Calcul<T> remove(T variable){
        return this.remove(new Getter<>(variable));
    }

    public abstract Calcul<T> divide(Getter<T> variable);

    public Calcul<T> divide(T variable){
        return this.divide(new Getter<>(variable));
    }

    public abstract Calcul<T> multiply(Getter<T> variable);
    public Calcul<T> multiply(T variable){
        return this.multiply(new Getter<>(variable));
    }

    public abstract Calcul<T> modulo(Getter<T> variable);
    public Calcul<T> modulo(T variable){
        return this.modulo(new Getter<>(variable));
    }

    abstract Calcul<T> newCopy(Operation<T> addedOperation);

    @Override
    public T getValue() {
        T calculatedValue = super.getValue();
        if(calculatedValue != null || operation == null)
            return calculatedValue;
        T prevVal = this.operation.getPrevious().getValue(), curVal = this.operation.getVariable().getValue();
        if(prevVal == null || curVal == null)
            return null;
        T result = operation.getBinaryOperator().apply(prevVal, curVal);
        super.setValue(result);
        return result;
    }

    public Pair<Variable<T>, T> getRevert(T result){

        if(operation == null)
            return null;

        T prevVal = operation.getPrevious().getValue(), curVal = operation.getVariable().getValue();
        T revertVal;

        if(prevVal == null && curVal == null)
            return null;

        Variable<T> checkName;

        if(prevVal == null) {
            checkName = (Variable<T>) operation.getPrevious();
            revertVal = operation.getRevertOperator().apply(result, curVal);
        }else{
            checkName = (Variable<T>) operation.getVariable();
            revertVal = operation.getRevertOperator().apply(result, prevVal);
        }

        if(prevVal == null) {
            if (operation.getPrevious().isCalcul())
                return ((Calcul<T>) operation.getPrevious()).getRevert(revertVal);
        }else if (curVal == null)
            if(operation.getVariable().isCalcul())
                return ((Calcul<T>) operation.getVariable()).getRevert(revertVal);

        return new Pair<>(checkName, revertVal);

    }

    @Override
    public List<Variable<T>> getVariableList(){
       return variableList;
    }

    @Override
    public boolean isCalcul(){
        return operation != null;
    }

}
