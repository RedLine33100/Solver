package fr.unk.contrainte;

import fr.unk.variable.VarGetter;
import fr.unk.variable.Variable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public abstract class Constraint<T> {

    List<Variable<T>> leftVar, rightVar;

    public static <T> List<Variable<T>> toVariableList(Collection<VarGetter<T>> varGetterList){

        List<Variable<T>> variableList = new ArrayList<>();

        if(varGetterList == null)
            return variableList;

        for(VarGetter<T> varGetter : varGetterList){
            if(!(varGetter instanceof Variable<T>))
                continue;
            variableList.addAll(((Variable<T>) varGetter).getVariableList());
        }

        return variableList;

    }

    public Constraint(List<VarGetter<T>> leftVar, List<VarGetter<T>> rightVar){
        this.leftVar = toVariableList(leftVar);
        this.rightVar = toVariableList(rightVar);
    }

    public Constraint(VarGetter<T> leftVar, VarGetter<T> rightVar){
        this(new ArrayList<>() {{
            if(leftVar != null)
                add(leftVar);
        }}, new ArrayList<>() {{
            if(rightVar != null)
                add(rightVar);
        }});
    }

    public abstract boolean satisfied(Map<String, T> objectMap);

    public List<Variable<T>> getVarOnLeft(){
        return leftVar;
    }
    public List<Variable<T>> getVarOnRight(){
        return rightVar;
    }

}
