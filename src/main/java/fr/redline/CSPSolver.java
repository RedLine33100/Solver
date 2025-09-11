package fr.redline;

import fr.redline.contrainte.Constraint;
import fr.redline.contrainte.ConstraintResult;
import fr.redline.contrainte.reduction.ReductionResult;
import fr.redline.value.Variable;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class CSPSolver<T> {

    protected final LinkedHashSet<Variable<T>> uknVariables = new LinkedHashSet<>();
    protected final List<Constraint<T>> constraintList = new ArrayList<>();

    public void addConstraint(Constraint<T> constraint) {
        LinkedHashSet<Variable<T>> unknownVariables = constraint.getUnknownVariables();
        this.uknVariables.addAll(unknownVariables);
        this.constraintList.add(constraint);

        unknownVariables.forEach(variable -> variable.addLinkedConstraint(constraint));
    }

    public boolean solve(LinkedHashSet<Variable<T>> remains) {

        if (remains.isEmpty())
            return false;

        Variable<T> variable = remains.removeFirst();

        if (variable.getValue() != null) {
            return solve(new LinkedHashSet<>(remains));
        }

        boolean empty = remains.isEmpty();

        for (T t : variable.getDomain().getPossibility()) {

            if (!variable.setValue(t))
                continue;

            boolean hasUnknownVariable = false;
            boolean failed = false;

            ReductionResult<T> reductionResult = new ReductionResult<T>();

            for (Constraint<T> constraint : variable.getLinkedConstraints()) {
                /*
                ConstraintResult constraintResult = constraint.evaluate();
                if (constraintResult == ConstraintResult.UNKNOWN)
                    hasUnknownVariable = true;
                else if (constraintResult == ConstraintResult.FALSE) {
                    failed = true;
                    break;
                }
                constraint.reduce(reductionResult);

                 */
                ConstraintResult constraintResult = constraint.testAndReduce(reductionResult, true);
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
                if (solve(new LinkedHashSet<>(remains)))
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

        System.out.println("Nombre de valeur: " + uknVariables.size());
        return this.solve(new LinkedHashSet<>(uknVariables));

    }

    public LinkedHashSet<Variable<T>> getUnknownVariables() {
        return uknVariables;
    }

}
