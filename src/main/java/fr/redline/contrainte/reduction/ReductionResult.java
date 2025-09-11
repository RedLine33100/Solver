package fr.redline.contrainte.reduction;

import fr.redline.contrainte.ConstraintResult;
import fr.redline.value.Variable;

import java.util.ArrayList;
import java.util.List;

public class ReductionResult<T> {

    public List<VariableChange<T>> variables = new ArrayList<>();

    public VariableChange<T> getVariableChange(Variable<T> value) {

        VariableChange<T> found = null;
        for (VariableChange<T> change : variables) {
            if (change.getVariable().equals(value)) {
                found = change;
                break;
            }
        }

        if (found == null) {
            found = new VariableChange<>(value);
            variables.add(found);
        }

        return found;

    }

    public void resetAll() {
        variables.forEach(VariableChange::reset);
        variables.clear();
    }

}
