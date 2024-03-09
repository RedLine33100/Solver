package fr.unk.contrainte.nc;

import fr.unk.contrainte.Constraint;
import fr.unk.domaine.DomainMap;
import fr.unk.util.Pair;
import fr.unk.variable.VarGetter;
import fr.unk.variable.Variable;
import fr.unk.variable.numvar.Calcul;

import java.util.List;

public class ListEquals<T extends Comparable<T>> extends Constraint<T> {

    final List<VarGetter<T>> variableList;

    public ListEquals(List<VarGetter<T>> variables){
        super(variables, null);
        this.variableList = variables;
    }

    @Override
    public boolean satisfied() {

        if (variableList.isEmpty())
            return false;

        T fv = variableList.getFirst().getValue();

        for (VarGetter<T> variable : variableList) {
            T vi = variable.getValue();
            if (vi == null || fv.compareTo(vi) != 0)
                return false;
        }

        return true;
    }

    @Override
    public void reduceDomain(DomainMap<T> domainMap){

        for(VarGetter<T> variable : this.variableList){
            if(variable.getValue() == null)
                continue;
            for(Variable<T> secVariable : this.getVarOnLeft()){
                if(secVariable.getValue() != null)
                    continue;
                if(variable.isVar())
                    if(variable.getVarName().equals(secVariable.getVarName()))
                        continue;

                if(secVariable.isCalcul()){
                    Pair<Variable<T>, T> revertValue = ((Calcul<T>)secVariable).getRevert(variable.getValue());
                    List<T> domainList = domainMap.getDomain(revertValue.getL()).getPossibility();
                    domainList.clear();
                    domainList.add(revertValue.getR());
                }else
                    domainMap.getDomain(secVariable).getPossibility().remove(variable.getValue());
            }
        }

    }

}
