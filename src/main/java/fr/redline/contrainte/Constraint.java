package fr.redline.contrainte;

import fr.redline.contrainte.reduction.ReductionResult;
import fr.redline.value.Variable;

import java.util.LinkedHashSet;

public interface Constraint<T> {

    ConstraintResult evaluate();

    LinkedHashSet<Variable<T>> getUnknownVariables();

    void reduce(ReductionResult<T> reductionResult);

    ConstraintResult testAndReduce(ReductionResult<T> reductionResult, boolean canReduce);

}
