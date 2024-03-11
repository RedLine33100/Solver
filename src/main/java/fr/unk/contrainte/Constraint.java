package fr.unk.contrainte;

import fr.unk.domaine.DomainMap;
import fr.unk.variable.Getter;
import fr.unk.variable.Variable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Constraint<T> {

    List<Variable<T>> leftVar, rightVar;

    public static <T> List<Variable<T>> toVariableList(Collection<Getter<T>> getterList){

        List<Variable<T>> variableList = new ArrayList<>();

        if(getterList == null)
            return variableList;

        for(Getter<T> getter : getterList){
            if (!getter.isVar())
                continue;
            variableList.addAll(((Variable<T>) getter).getVariableList());
        }

        return variableList;

    }
    public Constraint(List<Getter<T>> leftVar, List<Getter<T>> rightVar){
        this.leftVar = toVariableList(leftVar);
        this.rightVar = toVariableList(rightVar);
    }

    public Constraint() {
        this(new ArrayList<>(), new ArrayList<>());
    }

    public Constraint(Getter<T> leftVar, Getter<T> rightVar){
        this(new ArrayList<>() {{
            if(leftVar != null)
                add(leftVar);
        }}, new ArrayList<>() {{
            if(rightVar != null)
                add(rightVar);
        }});
    }

    public abstract Boolean trySatisfied();

    public boolean satisfied(){
        Boolean s = trySatisfied();
        if(s == null)
            return false;
        return s;
    }

    public List<Variable<T>> getVarOnLeft(){
        return leftVar;
    }
    public List<Variable<T>> getVarOnRight(){
        return rightVar;
    }

    /**
     *
     * @param domainMap
     * @return false if this reduction remove all var possibility
     */
    public abstract boolean reduceDomain(DomainMap<T> domainMap);

    public void registerToVar(){
        this.leftVar.forEach(variable -> variable.getConstrainst().add(this));
        this.rightVar.forEach(variable -> variable.getConstrainst().add(this));
    }

}
