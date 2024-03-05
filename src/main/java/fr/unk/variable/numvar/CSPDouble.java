package fr.unk.variable.numvar;

import fr.unk.variable.VarGetter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

public class CSPDouble extends Calcul<Double> {
    public CSPDouble(String varName) {
        super(varName, Double.class);
    }

    CSPDouble(String varName, Map<BinaryOperator<Double>, VarGetter<Double>> pairList){
        super(varName, Double.class, pairList);
    }

    private CSPDouble copyAddCalc(BinaryOperator<Double> bo, VarGetter<Double> calc){
        Map<BinaryOperator<Double>, VarGetter<Double>> list = new HashMap<>(this.operatorList);
        list.put(bo, calc);
        return new CSPDouble(this.getVarName(), list);
    }

    @Override
    public CSPDouble add(VarGetter<Double> variable){
        return this.copyAddCalc((int1, int2) -> int1+int2, variable);
    }

    @Override
    public CSPDouble remove(VarGetter<Double> variable){
        return this.copyAddCalc((int1, int2) -> int1-int2, variable);
    }

    @Override
    public CSPDouble divide(VarGetter<Double> variable) {
        return this.copyAddCalc((int1, int2) -> int1/int2, variable);
    }

    @Override
    public CSPDouble multiply(VarGetter<Double> variable) {
        return this.copyAddCalc((int1, int2) -> int1*int2, variable);
    }

    @Override
    public CSPDouble modulo(VarGetter<Double> variable) {
        return this.copyAddCalc((int1, int2) -> int1%int2, variable);
    }
}
