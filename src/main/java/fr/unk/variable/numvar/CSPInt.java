package fr.unk.variable.numvar;

import fr.unk.variable.VarGetter;

import java.util.List;

public class CSPInt extends Calcul<Integer> {
    public CSPInt(String varName) {
        super(varName, Integer.class);
    }

    CSPInt(String varName, List<Operation<Integer>> operationList){
        super(varName, Integer.class, operationList);
    }

    @Override
    public CSPInt add(VarGetter<Integer> variable){
        return (CSPInt) this.copyAddCalc((int1, int2) -> int1+int2, (int1, int2) -> int1-int2, variable);
    }

    @Override
    public CSPInt remove(VarGetter<Integer> variable){
        return (CSPInt) this.copyAddCalc((int1, int2) -> int1-int2, (int1, int2) -> int1+int2, variable);
    }

    @Override
    public CSPInt divide(VarGetter<Integer> variable) {
        return (CSPInt) this.copyAddCalc((int1, int2) -> int1/int2, (int1, int2) -> int1*int2, variable);
    }

    @Override
    public CSPInt multiply(VarGetter<Integer> variable) {
        return (CSPInt) this.copyAddCalc((int1, int2) -> int1*int2, (int1, int2) -> int1/int2, variable);
    }

    @Override
    public CSPInt modulo(VarGetter<Integer> variable) {
        return (CSPInt) this.copyAddCalc((int1, int2) -> int1%int2, null, variable);
    }

    @Override
    Calcul<Integer> newCopy(List<Operation<Integer>> operationList) {
        return new CSPInt(this.getVarName(), operationList);
    }
}
