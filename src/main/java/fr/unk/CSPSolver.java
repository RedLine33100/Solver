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

    public boolean setAndReduce(DomainMap<T> domainMap, Variable<T> variable, T value){
        variable.setValue(value);
        List<T> po = domainMap.getDomain(variable).getPossibility();
        po.clear();
        po.add(value);
        for(Constraint<T> constraint : variable.getConstrainst()) {
            if (!constraint.reduceDomain(domainMap)) {
                return false;
            }
        }
        return true;
    }

    public boolean solveDomain(DomainMap<T> domainMap){

        Variable<T> next = null;
        int nextSize = Integer.MAX_VALUE;
        int calcul = 0;

        for (Map.Entry<Variable<T>, Domain<T>> entry : domainMap.getMap().entrySet()) {
            int size = entry.getValue().getPossibility().size();
            if(size == 1) {
                if(!setAndReduce(domainMap, entry.getKey(), entry.getValue().getPossibility().getFirst()))
                    return false;
                calcul++;
                continue;
            }
            if(size == 0) {
                System.out.println("Size: 0");
                return false;
            }
            if(size<nextSize){
                nextSize = size;
                next = entry.getKey();
            }
        }

        if (calcul != 0 && calcul == domainMap.getMap().size()) {
            return true;
        }

        if(next == null) {
            System.out.println("Size: 1");
            return false;
        }

        return solve(next, domainMap);
    }

    private boolean solve(Variable<T> variable, DomainMap<T> domain) {

        Domain<T> tDomain = domain.getDomain(variable);

        for (T t : tDomain.getPossibility()){

            DomainMap<T> newDomain = domain.duplicate();

            boolean res = setAndReduce(newDomain, variable, t);

            if(!res)
                continue;

            if(solveDomain(newDomain))
                return true;

        }

        return false;
    }

    public Map<String, T> trySolve() {

        this.domainMap.getMap().keySet().forEach(variable -> {
            variable.invalidate();
            variable.getConstrainst().clear();
        });

        this.constraintList.forEach(Constraint::registerToVar);

        DomainMap<T> newDomain = domainMap.duplicate();

        if (!this.solveDomain(newDomain)) {
            return null;
        }

        return new HashMap<>() {{
            for (Variable<T> variable : newDomain.getMap().keySet()) {
                if (variable.getValue() != null)
                    put(variable.getVarName(), variable.getValue());
            }
        }};

    }

}
