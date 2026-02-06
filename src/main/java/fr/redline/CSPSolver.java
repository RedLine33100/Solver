package fr.redline;

import fr.redline.contrainte.Constraint;
import fr.redline.contrainte.ConstraintResult;
import fr.redline.contrainte.reduction.ReductionResult;
import fr.redline.utils.OptimizedList;
import fr.redline.value.Variable;


public class CSPSolver<T> {

    protected final OptimizedList<Variable<T>> uknVariables = new OptimizedList<>();
    protected Variable<T>[] variableArray;

    public void addConstraint(Constraint<T> constraint) {
        OptimizedList<Variable<T>> unknownVariables = constraint.getUnknownVariables();
        unknownVariables.activeValues().forEach(vari->{
            uknVariables.add(vari);
            vari.addLinkedConstraint(constraint);
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

            ReductionResult<T> reductionResult = new ReductionResult<>();

            for (Constraint<T> constraint : variable.getLinkedConstraints().activeValues()) {

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
        variableArray = new Variable[uknVariables.size()];
        int i = 0;

        for (Variable<T> var : uknVariables.activeValues()){
            variableArray[i] = var;
            i++;
        }

        return this.solve(0);

    }

    public OptimizedList<Variable<T>> getUnknownVariables() {
        return uknVariables;
    }

}
