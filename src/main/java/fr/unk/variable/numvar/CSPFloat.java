package fr.unk.variable.numvar;

import fr.unk.utils.Pair;
import fr.unk.variable.VarGetter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;

public class CSPFloat extends Calcul<Float> {
    public CSPFloat(String varName) {
        super(varName, Float.class);
    }

    CSPFloat(String varName, List<Pair<BinaryOperator<Float>, VarGetter<Float>>> pairList){
        super(varName, Float.class, pairList);
    }

    private CSPFloat copyAddCalc(Pair<BinaryOperator<Float>, VarGetter<Float>> calc){
        List<Pair<BinaryOperator<Float>, VarGetter<Float>>> list = new ArrayList<>(this.operatorList);
        list.add(calc);
        return new CSPFloat(this.getVarName(), list);
    }

    @Override
    public CSPFloat add(VarGetter<Float> variable){
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1+int2, variable));
    }

    @Override
    public CSPFloat remove(VarGetter<Float> variable){
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1-int2, variable));
    }

    @Override
    public CSPFloat divide(VarGetter<Float> variable) {
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1/int2, variable));
    }

    @Override
    public CSPFloat multiply(VarGetter<Float> variable) {
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1*int2, variable));
    }

    @Override
    public CSPFloat modulo(VarGetter<Float> variable) {
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1%int2, variable));
    }

    @Override
    public Float getValue(Map<String, Float> maps) {
        Float value = super.getValue(maps);
        if(value == null)
            return null;
        for(Pair<BinaryOperator<Float>, VarGetter<Float>> pair : this.operatorList){
            value = pair.getL().apply(value, pair.getR().getValue(maps));
        }
        return value;
    }
}
