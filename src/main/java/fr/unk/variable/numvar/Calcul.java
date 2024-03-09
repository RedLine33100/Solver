package fr.unk.variable.numvar;

import fr.unk.variable.VarGetter;
import fr.unk.variable.Variable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Calcul<T extends Number> extends Variable<T> {

    final Operation<T> operation;
    final List<Variable<T>> variableList;

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

    abstract Calcul<T> newCopy(Operation<T> addedOperation);

    @Override
    public T getValue(Map<String, T> maps) {
        if(operation == null)
            return super.getValue(maps);
        T prevVal = this.operation.getPrevious().getValue(maps), curVal = this.operation.getVariable().getValue(maps);
        if(prevVal == null || curVal == null)
            return null;
        return operation.getBinaryOperator().apply(prevVal, curVal);
    }

    public T getRevert(Map<String, T> map, T result){

        if(operation == null)
            return null;

        T prevVal = operation.getPrevious().getValue(map), curVal = operation.getVariable().getValue(map);
        T revertVal;

        if(prevVal == null && curVal == null)
            return null;

        if(prevVal == null)
            revertVal = operation.getRevertOperator().apply(result, curVal);
        else revertVal = operation.getRevertOperator().apply(result, prevVal);

        if(prevVal == null){
            if(operation.getPrevious().isCalcul())
                return ((Calcul<T>) operation.getPrevious()).getRevert(map, revertVal);
        }else if (curVal == null){
            if(operation.getVariable().isCalcul())
                return ((Calcul<T>) operation.getVariable()).getRevert(map, revertVal);
        }

        return revertVal;

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
