package fr.unk.variable.numvar;

import fr.unk.utils.Pair;
import fr.unk.variable.VarGetter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;

public class CSPInt extends Calcul<Integer> {
    public CSPInt(String varName) {
        super(varName, Integer.class);
    }

    CSPInt(String varName, List<Pair<BinaryOperator<Integer>, VarGetter<Integer>>> pairList){
        super(varName, Integer.class, pairList);
    }

    private CSPInt copyAddCalc(Pair<BinaryOperator<Integer>, VarGetter<Integer>> calc){
        List<Pair<BinaryOperator<Integer>, VarGetter<Integer>>> list = new ArrayList<>(this.operatorList);
        list.add(calc);
        return new CSPInt(this.getVarName(), list);
    }

    @Override
    public CSPInt add(VarGetter<Integer> variable){
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1+int2, variable));
    }

    @Override
    public CSPInt remove(VarGetter<Integer> variable){
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1-int2, variable));
    }

    @Override
    public CSPInt divide(VarGetter<Integer> variable) {
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1/int2, variable));
    }

    @Override
    public CSPInt multiply(VarGetter<Integer> variable) {
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1*int2, variable));
    }

    @Override
    public CSPInt modulo(VarGetter<Integer> variable) {
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1%int2, variable));
    }

    @Override
    public Integer getValue(Map<String, Object> maps) {
        Integer value = super.getValue(maps);
        if(value == null)
            return null;
        for(Pair<BinaryOperator<Integer>, VarGetter<Integer>> pair : this.operatorList){
            value = pair.getL().apply(value, pair.getR().getValue(maps));
        }
        return value;
    }
}
