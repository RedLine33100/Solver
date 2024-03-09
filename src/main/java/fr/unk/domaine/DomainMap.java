package fr.unk.domaine;

import fr.unk.variable.Variable;

import java.util.HashMap;
import java.util.Map;

public class DomainMap<T> {

    Map<String, Domain<T>> uknVariables = new HashMap<>();

    public Map<String, Domain<T>> getDomainMap(){
        return uknVariables;
    }

    private void addDomain(String variable, Domain<T> domain){
        this.uknVariables.remove(variable);
        this.uknVariables.put(variable, domain);
    }

    public void addDomain(Variable<T> variable, Domain<T> domain){
        this.uknVariables.put(variable.getVarName(), domain);
    }

    public Domain<T> getDomain(Variable<T> variable){
        return this.uknVariables.get(variable.getVarName());
    }

    public Domain<T> getDomain(String variable){
        return this.uknVariables.get(variable);
    }

    public void removeDomain(Variable<T> variable){
        this.uknVariables.remove(variable.getVarName());
    }

    public DomainMap<T> duplicate(){
        DomainMap<T> domainMap = new DomainMap<>();
        uknVariables.forEach((name, domain) -> domainMap.addDomain(name, domain.duplicate()));
        return domainMap;
    }

}
