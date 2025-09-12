package fr.redline;

import fr.redline.contrainte.Constraint;
import fr.redline.contrainte.ConstraintResult;
import fr.redline.contrainte.reduction.ReductionResult;
import fr.redline.value.Variable;

import java.util.LinkedHashSet;

public class CSPSolver<T> {

    protected final LinkedHashSet<Variable<T>> uknVariables = new LinkedHashSet<>();
    protected Variable<T>[] variableArray;
    private int uknVarCount = 0;

    public void addConstraint(Constraint<T> constraint) {
        LinkedHashSet<Variable<T>> unknownVariables = constraint.getUnknownVariables();
        this.uknVariables.addAll(unknownVariables);

        unknownVariables.forEach(variable -> {
            variable.addLinkedConstraint(constraint);
            variable.setVarSolverID(uknVarCount);
            uknVarCount++;
        });
    }

    private boolean solve(int step) {

        if (step == variableArray.length)
            return false;

        Variable<T> variable = variableArray[step];

        if (variable.getValue() != null) {
            return solve(step + 1);
        }

        boolean empty = step + 1 == variableArray.length;

        for (T t : variable.getDomain().getPossibility()) {

            if (!variable.setValue(t))
                continue;

            boolean hasUnknownVariable = false;
            boolean failed = false;

            ReductionResult<T> reductionResult = new ReductionResult<>(uknVarCount);

            for (Constraint<T> constraint : variable.getLinkedConstraints()) {

                ConstraintResult constraintResult = constraint.testAndReduce(reductionResult);
                if (constraintResult == ConstraintResult.UNKNOWN)
                    hasUnknownVariable = true;
                else if (constraintResult == ConstraintResult.FALSE) {
                    failed = true;
                    break;
                }

            }

            if (failed) {
                reductionResult.resetAll();
                continue;
            }

            if (!empty || hasUnknownVariable) {
                if (solve(step + 1))
                    return true;
                reductionResult.resetAll();
                continue;
            }

            return true;

        }

        variable.setValue(null);

        return false;

    }

    public boolean trySolve() {

        variableArray = uknVariables.toArray(new Variable[uknVariables.size()]);
        return this.solve(0);

    }

    public LinkedHashSet<Variable<T>> getUnknownVariables() {
        return uknVariables;
    }

}
