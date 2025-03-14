package fr.unk;

import fr.unk.contrainte.Constraint;
import fr.unk.domaine.Domain;
import fr.unk.utils.Pair;
import fr.unk.variable.Variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSPSolver<T> {

    List<Pair<Variable<T>, Domain<T>>> uknVariables = new ArrayList<>();
    List<Constraint<T>> constraintList = new ArrayList<>();

    public void addUnknownVariable(Variable<T> variable, Domain<T> domain){
        this.uknVariables.add(new Pair<>(variable, domain));
    }

    public void addConstraint(Constraint<T> constraint){
        this.constraintList.add(constraint);
    }

    public Map<String, T> solve(List<Pair<Variable<T>, Domain<T>>> remains, Map<String, T> objectMap){

        if(remains.isEmpty())
            return null;

        Pair<Variable<T>, Domain<T>> pair = remains.removeFirst();

        boolean empty = remains.isEmpty();

        for (T t : pair.getR().getPossibility()){

            objectMap.put(pair.getL().getVarName(), t);

            if(!empty) {
                Map<String, T> mayResult = solve(new ArrayList<>(remains), objectMap);
                if(mayResult != null)
                    return mayResult;
            }

            boolean failed = false;

            for(Constraint<T> constraint : constraintList){
                if(!constraint.satisfied(objectMap)) {
                    failed = true;
                    break;
                }
            }

            if(!failed)
                return objectMap;

        }

        return null;

    }

    public Map<String, T> trySolve(){

        return this.solve(uknVariables, new HashMap<>());

    }

}
