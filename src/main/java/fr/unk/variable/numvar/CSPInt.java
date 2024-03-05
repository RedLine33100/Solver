package fr.unk.variable.numvar;

import fr.unk.variable.VarGetter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

public class CSPInt extends Calcul<Integer> {
    public CSPInt(String varName) {
        super(varName, Integer.class);
    }

    CSPInt(String varName, Map<BinaryOperator<Integer>, VarGetter<Integer>> pairList){
        super(varName, Integer.class, pairList);
    }

    private CSPInt copyAddCalc(BinaryOperator<Integer> bo, VarGetter<Integer> calc){
        Map<BinaryOperator<Integer>, VarGetter<Integer>> list = new HashMap<>(this.operatorList);
        list.put(bo, calc);
        return new CSPInt(this.getVarName(), list);
    }

    @Override
    public CSPInt add(VarGetter<Integer> variable){
        return this.copyAddCalc((int1, int2) -> int1+int2, variable);
    }

    @Override
    public CSPInt remove(VarGetter<Integer> variable){
        return this.copyAddCalc((int1, int2) -> int1-int2, variable);
    }

    @Override
    public CSPInt divide(VarGetter<Integer> variable) {
        return this.copyAddCalc((int1, int2) -> int1/int2, variable);
    }

    @Override
    public CSPInt multiply(VarGetter<Integer> variable) {
        return this.copyAddCalc((int1, int2) -> int1*int2, variable);
    }

    @Override
    public CSPInt modulo(VarGetter<Integer> variable) {
        return this.copyAddCalc((int1, int2) -> int1%int2, variable);
    }
}
