package fr.unk;

import fr.unk.contrainte.Constraint;
import fr.unk.domaine.Domain;
import fr.unk.domaine.DomainMap;
import fr.unk.variable.Variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSPSolver<T> {

    List<Constraint<T>> constraintList = new ArrayList<>();
    private final DomainMap<T> domainMap = new DomainMap<>();

    public void addUnknownVariable(Variable<T> variable, Domain<T> domain){
        this.domainMap.addDomain(variable, domain);
    }

    public void addConstraint(Constraint<T> constraint){
        this.constraintList.add(constraint);
    }

    public boolean hasUnknownVariable(Variable<T> variable) {
        return this.domainMap.getMap().containsKey(variable);
    }

    public Domain<T> getDefinedDomain(Variable<T> variable) {
        return this.domainMap.getMap().get(variable);
    }

    public boolean reduceAndSolve(DomainMap<T> domainMap){
        constraintList.forEach(tConstraint -> tConstraint.reduceDomain(domainMap));

        Variable<T> next = null;
        int nextSize = Integer.MAX_VALUE;
        int calcul = 0;

        for (Map.Entry<Variable<T>, Domain<T>> entry : domainMap.getMap().entrySet()) {
            int size = entry.getValue().getPossibility().size();
            if(size == 1) {
                entry.getKey().setValue(entry.getValue().getPossibility().getFirst());
                calcul++;
                continue;
            }
            if(size == 0)
                return false;
            if(size<nextSize){
                nextSize = size;
                next = entry.getKey();
            }
        }

        if (calcul != 0 && calcul == domainMap.getMap().size())
            return true;

        if(next == null)
            return false;

        return solve(next, domainMap);
    }

    private boolean solve(Variable<T> variable, DomainMap<T> domain) {

        Domain<T> tDomain = domain.getAndRemoveDomain(variable);

        for (T t : tDomain.getPossibility()){

            variable.setValue(t);
            if (domain.getMap().isEmpty()) {
                for(Constraint<T> tConstraint : constraintList){
                    if(!tConstraint.satisfied()){
                        return false;
                    }
                }
            }

            if(reduceAndSolve(domain.duplicate()))
                return true;

        }

        return false;
    }

    public Map<String, T> trySolve() {

        this.domainMap.getMap().keySet().forEach(Variable::invalidate);
        if (!this.reduceAndSolve(this.domainMap.duplicate()))
            return null;

        return new HashMap<>() {{
            for (Variable<T> variable : domainMap.getMap().keySet()) {
                if (variable.getValue() != null)
                    put(variable.getVarName(), variable.getValue());
            }
        }};

    }

}
