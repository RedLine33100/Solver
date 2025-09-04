package fr.redline.value;

import fr.redline.contrainte.Constraint;
import fr.redline.value.variable.VarType;
import fr.redline.value.variable.Variable;

import java.util.LinkedHashSet;

public abstract class ValueGetter<T> {

    LinkedHashSet<Variable<T>> linkedVars = new LinkedHashSet<>();
    LinkedHashSet<Constraint<T>> linkedConstraints = new LinkedHashSet<>();

    public abstract T getValue();
    public abstract VarType getType();

    protected void addUnknownVar(Variable<T> var){
        linkedVars.add(var);
    }

    protected void addAllUnknownVars(LinkedHashSet<Variable<T>> vars){
        linkedVars.addAll(vars);
    }

    public LinkedHashSet<Variable<T>> getUnknownVariables(){
        return linkedVars;
    }

    public void addLinkedConstraint(Constraint<T> constraint){
        linkedConstraints.add(constraint);
    }

    public LinkedHashSet<Constraint<T>> getLinkedConstraints(){
        return linkedConstraints;
    }

}
