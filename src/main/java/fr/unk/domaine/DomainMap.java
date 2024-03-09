package fr.unk.domaine;

import fr.unk.variable.Variable;

import java.util.HashMap;
import java.util.Map;

public class DomainMap<T> {

    Map<Variable<T>, Domain<T>> uknVariables = new HashMap<>();

    public Map<Variable<T>, Domain<T>> getDomainMap(){
        return uknVariables;
    }

    public void addDomain(Variable<T> variable, Domain<T> domain){
        this.uknVariables.put(variable, domain);
    }

    public Domain<T> getDomain(Variable<T> variable){
        return this.uknVariables.get(variable);
    }

    public Domain<T> getAndRemoveDomain(Variable<T> variable){
        return this.uknVariables.remove(variable);
    }

    public void removeDomain(Variable<T> variable){
        this.uknVariables.remove(variable);
    }

    public DomainMap<T> duplicate(){
        DomainMap<T> domainMap = new DomainMap<>();
        uknVariables.forEach((name, domain) -> domainMap.addDomain(name, domain.duplicate()));
        return domainMap;
    }

}
