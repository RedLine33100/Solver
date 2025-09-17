package fr.redline.contrainte;

import fr.redline.contrainte.reduction.ReductionResult;
import fr.redline.utils.OptimizedList;
import fr.redline.value.Variable;

public interface Constraint<T> {

    ConstraintResult evaluate();

    OptimizedList<Variable<T>> getUnknownVariables();

    void reduce(ReductionResult<T> reductionResult);

    ConstraintResult testAndReduce(ReductionResult<T> reductionResult);

}
