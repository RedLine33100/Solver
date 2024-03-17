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

    /**
     * Register var and its domain to iterate on when trySolve is called
     * @param variable the var to iterate on
     * @param domain the domain of the var
     */
    public void addUnknownVariable(Variable<T> variable, Domain<T> domain){
        this.domainMap.addDomain(variable, domain);
    }

    /**
     * Add constraint to solver
     * @param constraint Constraint to check on while try to solve
     */
    public void addConstraint(Constraint<T> constraint){
        this.constraintList.add(constraint);
    }

    /**
     * Check if a var is registered
     * @param variable the var
     * @return true if variable is stored
     */
    public boolean hasUnknownVariable(Variable<T> variable) {
        return this.domainMap.getMap().containsKey(variable);
    }

    /**
     * Get registered domain for var
     * @param variable the var of the domain we want
     * @return the domain of the var
     */
    public Domain<T> getDefinedDomain(Variable<T> variable) {
        return this.domainMap.getMap().get(variable);
    }

    /**
     * Attribute value to var and reduce its domain
     * @param domainMap domain to reduce
     * @param variable variable which value gonna be attribuated
     * @param value the new var value
     * @return True if there is at least one solution
     */
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

    /**
     * Function will search for the domain with low amount of possibility and start
     * the search of a valid value on it
     * @param domainMap current domain map to search on
     * @return True if a solution is found
     */
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

    /**
     * Iterate on all domain possibility for the var and call solveDomain for the other domain
     * @param variable variable to test all possibility
     * @param domain All of the domain
     * @return True if solution is found
     */
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
