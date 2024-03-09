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
            if(!(getter instanceof Variable<T>))
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

    public abstract boolean satisfied();

    public List<Variable<T>> getVarOnLeft(){
        return leftVar;
    }
    public List<Variable<T>> getVarOnRight(){
        return rightVar;
    }

    public abstract void reduceDomain(DomainMap<T> domainMap);

}
