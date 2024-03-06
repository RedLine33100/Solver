package fr.unk.variable.numvar;

import fr.unk.variable.VarGetter;
import fr.unk.variable.Variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;

public abstract class Calcul<T extends Number> extends Variable<T> {

    final List<Operation<T>> operationList;
    final List<Variable<T>> variableList;
    boolean canBeReverted = true;

    public Calcul(String varName, Class<T> tClass, List<Operation<T>> operationList) {
        super(varName, tClass);
        this.operationList = operationList;
        this.variableList = new ArrayList<>();
        this.operationList.forEach(operation ->{
            if(operation.getVariable().isVar())
                variableList.addAll(((Variable<T>)operation.getVariable()).getVariableList());
            if(!operation.canRevert())
                canBeReverted = false;
        });
        this.variableList.add(this);
    }

    public Calcul(String varName, Class<T> tClass) {
        this(varName, tClass, new ArrayList<>());
    }

    Calcul<T> copyAddCalc(BinaryOperator<T> bo, BinaryOperator<T> revert, VarGetter<T> calc){
        return this.newCopy(new ArrayList<>(operationList){{add(new Operation<T>(bo, revert, calc));}});
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

    abstract Calcul<T> newCopy(List<Operation<T>> operationList);

    @Override
    public T getValue(Map<String, Object> maps) {
        T value = super.getValue(maps);
        if(value == null)
            return null;
        for(Operation<T> operation : operationList){
            T secValue = operation.getVariable().getValue(maps);
            if(secValue == null)
                return null;
            value = operation.getBinaryOperator().apply(value, secValue);
        }
        return value;
    }

    public T getRevert(Map<String, Object> map, T result){

        if(!canRevert())
            return null;

        List<Operation<T>> revertOperationList = operationList.reversed();
        while (!revertOperationList.isEmpty()) {

            Operation<T> operation = revertOperationList.removeFirst();
            T secValue = operation.getVariable().getValue(map);

            if(secValue == null) {
                T newVal = newCopy(revertOperationList.reversed()).getValue(map);
                if(newVal == null)
                    return null;
                return operation.getRevertOperator().apply(result, newVal);
            }

            result = operation.getRevertOperator().apply(result, operation.getVariable().getValue(map));

        }

        return result;
    }

    @Override
    public List<Variable<T>> getVariableList(){
       return variableList;
    }

    public boolean canRevert(){
        return canBeReverted;
    }

}
