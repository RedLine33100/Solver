package fr.unk;

import fr.unk.contrainte.Constraint;
import fr.unk.domaine.Domain;
import fr.unk.domaine.DomainMap;
import fr.unk.variable.Variable;

import java.util.*;

public class CSPSolver<T> {

    List<Constraint<T>> constraintList = new ArrayList<>();
    private final DomainMap<T> domainMap = new DomainMap<>();

    public void addUnknownVariable(Variable<T> variable, Domain<T> domain){
        this.domainMap.addDomain(variable, domain);
    }

    public void addConstraint(Constraint<T> constraint){
        this.constraintList.add(constraint);
    }

    public boolean reduceAndSolve(DomainMap<T> domainMap){
        System.out.println("Reduce and solve");
        constraintList.forEach(tConstraint -> tConstraint.reduceDomain(domainMap));

        Variable<T> next = null;
        int nextSize = Integer.MAX_VALUE;
        int calcul = 0;

        for(Map.Entry<Variable<T>, Domain<T>> entry : domainMap.getDomainMap().entrySet()){
            int size = entry.getValue().getPossibility().size();
            if(size == 1) {
                entry.getKey().setCalculatedValue(entry.getValue().getPossibility().getFirst());
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

        if(calcul == domainMap.getDomainMap().size())
            return true;

        if(next == null)
            return false;

        return solve(next, domainMap);
    }

    public boolean solve(Variable<T> variable, DomainMap<T> domain){

        Domain<T> tDomain = domain.getAndRemoveDomain(variable);

        for (T t : tDomain.getPossibility()){

            variable.setCalculatedValue(t);
            if(domain.getDomainMap().isEmpty()){
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

    public DomainMap<T> getDomainMap(){
        return this.domainMap;
    }

    public boolean trySolve(){

        this.domainMap.getDomainMap().keySet().forEach(Variable::invalidate);
        return this.reduceAndSolve(this.domainMap.duplicate());

    }

}
