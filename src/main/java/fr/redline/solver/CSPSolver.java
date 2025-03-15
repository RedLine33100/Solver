package fr.redline.solver;

import fr.redline.contrainte.Constraint;
import fr.redline.contrainte.ConstraintResult;
import fr.redline.value.variable.Variable;

import java.util.*;

public class CSPSolver<T> {

    protected final List<Variable<T>> uknVariables = new ArrayList<>();
    protected final List<Constraint> constraintList = new ArrayList<>();

    public void addUnknownVariable(Variable<T> variable){
        this.uknVariables.add(variable);
    }

    public void addConstraint(Constraint constraint){
        this.constraintList.add(constraint);
    }

    public Map<String, T> solve(List<Variable<T>> remains){

        if(remains.isEmpty())
            return null;

        Variable<T> variable = remains.removeFirst();

        boolean empty = remains.isEmpty();

        for (T t : variable.getDomain().getPossibility()){

            variable.setValue(t);

            boolean hasUnknownVariable = false;
            boolean failed = false;

            for(Constraint constraint : constraintList){
                ConstraintResult constraintResult = constraint.satisfied();
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
                Map<String, T> mayResult = solve(new ArrayList<>(remains));
                if(mayResult != null) {
                    mayResult.put(variable.getVarName(), t);
                    return mayResult;
                }
            }

            return new HashMap<>(){{
                put(variable.getVarName(), t);
            }};

        }

        variable.setValue(null);

        return null;

    }

    public Map<String, T> trySolve(){

        return this.solve(uknVariables);

    }

}
