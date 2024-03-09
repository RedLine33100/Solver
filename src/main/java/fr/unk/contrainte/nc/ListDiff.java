package fr.unk.contrainte.nc;

import fr.unk.contrainte.Constraint;
import fr.unk.domaine.DomainMap;
import fr.unk.util.Pair;
import fr.unk.variable.VarGetter;
import fr.unk.variable.Variable;
import fr.unk.variable.numvar.Calcul;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListDiff<T extends Comparable<T>> extends Constraint<T> {

    final List<VarGetter<T>> variableList;

    public ListDiff(List<VarGetter<T>> variables){
        super(variables, null);
        this.variableList = variables;
    }

    public ListDiff(VarGetter<T>... variables){
        this(Arrays.asList(variables));
    }

    @Override
    public boolean satisfied() {

        List<Variable<T>> variableList = getVarOnLeft();

        for(int i = 0; i<variableList.size(); i++) {
            T vi = variableList.get(i).getValue();
            if(vi == null)
                return false;
            for (int y = i + 1; y < variableList.size(); y++) {
                if (vi.compareTo(variableList.get(y).getValue()) == 0)
                    return false;
            }
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
                    domainMap.getDomain(revertValue.getL()).getPossibility().remove(revertValue.getR());
                }else
                    domainMap.getDomain(secVariable).getPossibility().remove(variable.getValue());
            }
        }

    }

}
