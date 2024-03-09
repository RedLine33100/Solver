package fr.unk;

import fr.unk.contrainte.Constraint;
import fr.unk.domaine.Domain;
import fr.unk.variable.Variable;

import java.util.*;

public class CSPSolver<T> {

    Map<Variable<T>, Domain<T>> uknVariables = new HashMap<>();
    List<Constraint<T>> constraintList = new ArrayList<>();

    public void addUnknownVariable(Variable<T> variable, Domain<T> domain){
        this.uknVariables.put(variable, domain);
    }

    public void addConstraint(Constraint<T> constraint){
        this.constraintList.add(constraint);
    }

    public Map<String, T> solve(Map<Variable<T>, Domain<T>> remains, Map<String, T> objectMap){

        Optional<Map.Entry<Variable<T>, Domain<T>>> optionalEntry = remains.entrySet().stream().findFirst();
        if(optionalEntry.isEmpty())
            return null;

        Map.Entry<Variable<T>, Domain<T>> pair = optionalEntry.get();
        remains.remove(pair.getKey());

        boolean empty = remains.isEmpty();

        for (T t : pair.getValue().getPossibility()){

            objectMap.put(pair.getKey().getVarName(), t);

            if(!empty) {
                Map<String, T> mayResult = solve(new HashMap<>(remains), objectMap);
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
