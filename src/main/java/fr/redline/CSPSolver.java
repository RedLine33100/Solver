package fr.redline;

import fr.redline.contrainte.Constraint;
import fr.redline.contrainte.ConstraintResult;
import fr.redline.value.variable.Variable;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class CSPSolver<T> {

    protected final LinkedHashSet<Variable<T>> uknVariables = new LinkedHashSet<>();
    protected final List<Constraint<T>> constraintList = new ArrayList<>();

    public void addConstraint(Constraint<T> constraint){
        this.uknVariables.addAll(constraint.getUnknownVariables());
        this.constraintList.add(constraint);

        constraint.getUnknownVariables().forEach(variable -> variable.addLinkedConstraint(constraint));
    }

    public boolean solve(LinkedHashSet<Variable<T>> remains){

        if(remains.isEmpty())
            return false;

        Variable<T> variable = remains.removeFirst();

        boolean empty = remains.isEmpty();

        for (T t : variable.getDomain().getPossibility()){

            variable.setValue(t);

            boolean hasUnknownVariable = false;
            boolean failed = false;

            for(Constraint<T> constraint : variable.getLinkedConstraints()){
                ConstraintResult constraintResult = constraint.evaluate();
                if(constraintResult == ConstraintResult.UNKNOWN)
                    hasUnknownVariable = true;
                else if(constraintResult == ConstraintResult.FALSE) {
                    failed = true;
                    break;
                }
            }

            if(failed)
                continue;

            if(!empty || hasUnknownVariable) {
                if(solve(new LinkedHashSet<>(remains)))
                    return true;
                continue;
            }

            return true;

        }

        variable.setValue(null);

        return false;

    }

    public boolean trySolve(){



        return this.solve(new LinkedHashSet<>(uknVariables));

    }

    public LinkedHashSet<Variable<T>> getUnknownVariables(){
        return uknVariables;
    }

}
