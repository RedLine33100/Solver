package fr.redline.contrainte.reduction;

import fr.redline.value.Variable;

import java.util.Arrays;

public class ReductionResult<T> {

    private final VariableChange<T>[] variables;

    public ReductionResult(int varNumber) {
        variables = new VariableChange[varNumber];
        Arrays.fill(variables, null);
    }

    public VariableChange<T> getVariableChange(Variable<T> value) {

        VariableChange<T> found = variables[value.getVarSolverID()];

        if (found == null) {
            found = new VariableChange<>(value);
            variables[value.getVarSolverID()] = found;
        }

        return found;

    }

    public void resetAll() {
        for (VariableChange<T> var : variables) {
            if (var != null) var.reset();
        }
    }

}
