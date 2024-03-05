package fr.unk.variable.numvar;

import fr.unk.variable.VarGetter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

public class CSPFloat extends Calcul<Float> {
    public CSPFloat(String varName) {
        super(varName, Float.class);
    }

    CSPFloat(String varName, Map<BinaryOperator<Float>, VarGetter<Float>> pairList){
        super(varName, Float.class, pairList);
    }

    private CSPFloat copyAddCalc(BinaryOperator<Float> bo, VarGetter<Float> calc){
        Map<BinaryOperator<Float>, VarGetter<Float>> list = new HashMap<>(this.operatorList);
        list.put(bo, calc);
        return new CSPFloat(this.getVarName(), list);
    }

    @Override
    public CSPFloat add(VarGetter<Float> variable){
        return this.copyAddCalc((int1, int2) -> int1+int2, variable);
    }

    @Override
    public CSPFloat remove(VarGetter<Float> variable){
        return this.copyAddCalc((int1, int2) -> int1-int2, variable);
    }

    @Override
    public CSPFloat divide(VarGetter<Float> variable) {
        return this.copyAddCalc((int1, int2) -> int1/int2, variable);
    }

    @Override
    public CSPFloat multiply(VarGetter<Float> variable) {
        return this.copyAddCalc((int1, int2) -> int1*int2, variable);
    }

    @Override
    public CSPFloat modulo(VarGetter<Float> variable) {
        return this.copyAddCalc((int1, int2) -> int1%int2, variable);
    }
}
