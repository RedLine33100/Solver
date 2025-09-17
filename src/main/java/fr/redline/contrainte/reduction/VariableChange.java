package fr.redline.contrainte.reduction;

import fr.redline.utils.OptimizedList;
import fr.redline.value.Variable;

public class VariableChange<T> {

    OptimizedList<T> removedFromDomain = new OptimizedList<>();
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
        for(T t : removedFromDomain.activeValues()){
            variable.getDomain().addToDomain(t);
        }
        variable.setValue(previousValue);
    }

    public Variable<T> getVariable() {
        return this.variable;
    }

}
