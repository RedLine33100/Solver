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

    /**
     * Add a add to the current operation
     * @param getter the other value of the calcul
     * @return a new instance of calcul with the new operation
     */
    abstract Calcul<T> add(Getter<T> getter);

    /**
     * Add a add to the current operation
     * @param variable the other value of the calcul
     * @return a new instance of calcul with the new operation
     */
    public Calcul<T> add(T variable){
        return this.add(new Getter<>(variable));
    }

    /**
     * Add a remove to the current operation
     * @param getter the other value of the calcul
     * @return a new instance of calcul with the new operation
     */
    abstract Calcul<T> remove(Getter<T> getter);

    /**
     * Add a remove to the current operation
     * @param variable the other value of the calcul
     * @return a new instance of calcul with the new operation
     */
    public Calcul<T> remove(T variable){
        return this.remove(new Getter<>(variable));
    }

    /**
     * Add a divide to the current operation
     * @param variable the other value of the calcul
     * @return a new instance of calcul with the new operation
     */
    public abstract Calcul<T> divide(Getter<T> variable);

    /**
     * Add a divide to the current operation
     * @param variable the other value of the calcul
     * @return a new instance of calcul with the new operation
     */
    public Calcul<T> divide(T variable){
        return this.divide(new Getter<>(variable));
    }

    /**
     * Add a multiply to the current operation
     * @param variable the other value of the calcul
     * @return a new instance of calcul with the new operation
     */
    public abstract Calcul<T> multiply(Getter<T> variable);

    /**
     * Add a multiply to the current operation
     * @param variable the other value of the calcul
     * @return a new instance of calcul with the new operation
     */
    public Calcul<T> multiply(T variable){
        return this.multiply(new Getter<>(variable));
    }

    /**
     * Add a modulo to the current operation
     * @param variable the other value of the calcul
     * @return a new instance of calcul with the new operation
     */
    public abstract Calcul<T> modulo(Getter<T> variable);

    /**
     * Add a modulo to the current operation
     * @param variable the other value of the calcul
     * @return a new instance of calcul with the new operation
     */
    public Calcul<T> modulo(T variable){
        return this.modulo(new Getter<>(variable));
    }

    /**
     * Generate new copy with a new operation
     * @param addedOperation Operation to add to copy
     * @return the new instance of Calcul
     */
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

    /**
     * Revert the calcul to found the value of an unknown var
     * @param result a pair containing the var and it's founded value
     * @return
     */
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
