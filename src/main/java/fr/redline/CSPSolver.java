package fr.redline;

import fr.redline.contrainte.Constraint;
import fr.redline.contrainte.ConstraintResult;
import fr.redline.value.variable.Variable;

import java.util.*;

public class CSPSolver<T> {

    protected final List<Variable<T>> uknVariables = new ArrayList<>();
    protected final List<Constraint<T>> constraintList = new ArrayList<>();

    public void addUnknownVariable(Variable<T> variable){
        this.uknVariables.add(variable);
    }

    public void addConstraint(Constraint<T> constraint){
        this.constraintList.add(constraint);
    }

    public Map<String, T> solve(List<Variable<T>> remains, Map<String, T> objectMap){

        if(remains.isEmpty())
            return null;

        Variable<T> variable = remains.removeFirst();

        boolean empty = remains.isEmpty();

        for (T t : variable.domain().getPossibility()){

            objectMap.put(variable.varName(), t);

            boolean hasUnknownVariable = false;
            boolean failed = false;

            for(Constraint<T> constraint : constraintList){
                ConstraintResult constraintResult = constraint.satisfied(objectMap);
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
                Map<String, T> mayResult = solve(new ArrayList<>(remains), objectMap);
                if(mayResult != null)
                    return mayResult;
            }

            return objectMap;

        }

        return null;

    }

    public Map<String, T> trySolve(){

        return this.solve(uknVariables, new HashMap<>());

    }

}
