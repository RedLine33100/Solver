package fr.redline.value;

import fr.redline.contrainte.Constraint;
import fr.redline.value.variable.VarType;
import fr.redline.value.variable.Variable;

import java.util.LinkedHashSet;

public class Value<T> {

    private LinkedHashSet<Value<T>> linkedVars = new LinkedHashSet<>();
    private LinkedHashSet<Constraint<T>> linkedConstraints = new LinkedHashSet<>();
    T value = null;

    protected Value(){

    }

    public Value(T value) {
        this.value = value;
    }

    public T getValue(){
        return this.value;
    }

    public void setValue(T value){
        if(value == this.value)
            return;
        this.value = value;
        for(Value<T> v : linkedVars){
            v.setValue(null);
        }
    }

    public VarType getType(){
        return VarType.ALREADY_CALCULATED;
    }

    public LinkedHashSet<Value<T>> getLinkedValue(){
        return linkedVars;
    }

    public void registerLinkedValue(Value<T> value){
        this.linkedVars.add(value);
    }

    public void addLinkedConstraint(Constraint<T> constraint){
        linkedConstraints.add(constraint);
    }

    public LinkedHashSet<Constraint<T>> getLinkedConstraints(){
        return linkedConstraints;
    }

    public LinkedHashSet<Variable<T>> getUnknownVariables(){
        return new LinkedHashSet<>();
    }

}
