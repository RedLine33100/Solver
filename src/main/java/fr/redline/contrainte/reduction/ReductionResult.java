package fr.redline.contrainte.reduction;

import fr.redline.value.Variable;

import java.util.HashMap;
import java.util.Map;

public class ReductionResult<T> {

    private final Map<String, VariableChange<T>> variables = new HashMap<>();

    public VariableChange<T> getVariableChange(Variable<T> value) {

        VariableChange<T> found = variables.get(value.getName());

        if (found == null) {
            found = new VariableChange<>(value);
            variables.put(value.getName(), found);        }

        return found;

    }

    public void resetAll() {
        for (Map.Entry<String, VariableChange<T>> var : variables.entrySet()) {
            if (var != null) var.getValue().reset();
        }
    }

}
