package fr.redline.contrainte.reduction;

import fr.redline.value.Variable;

import java.util.LinkedHashSet;

public class VariableChange<T> {

    LinkedHashSet<T> removedFromDomain = new LinkedHashSet<>();
    T previousValue;
    Variable<T> variable;

    public VariableChange(Variable<T> variable) {
        this.previousValue = variable.getValue();
        this.variable = variable;
    }

    public void varDomainReduce(T remove) {
        variable.getDomain().removeFromDomain(remove);
        removedFromDomain.add(remove);
    }

    public boolean setValue(T value) {
        return variable.setValue(value);
    }

    public void reset() {
        removedFromDomain.forEach(variable.getDomain()::addToDomain);
        variable.setValue(previousValue);
    }

    public Variable<T> getVariable() {
        return this.variable;
    }

}
