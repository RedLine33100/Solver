package fr.unk.variable.numvar;

import fr.unk.utils.Pair;
import fr.unk.variable.VarGetter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;

public class CSPDouble extends Calcul<Double> {
    public CSPDouble(String varName) {
        super(varName, Double.class);
    }

    CSPDouble(String varName, List<Pair<BinaryOperator<Double>, VarGetter<Double>>> pairList){
        super(varName, Double.class, pairList);
    }

    private CSPDouble copyAddCalc(Pair<BinaryOperator<Double>, VarGetter<Double>> calc){
        List<Pair<BinaryOperator<Double>, VarGetter<Double>>> list = new ArrayList<>(this.operatorList);
        list.add(calc);
        return new CSPDouble(this.getVarName(), list);
    }

    @Override
    public CSPDouble add(VarGetter<Double> variable){
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1+int2, variable));
    }

    @Override
    public CSPDouble remove(VarGetter<Double> variable){
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1-int2, variable));
    }

    @Override
    public CSPDouble divide(VarGetter<Double> variable) {
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1/int2, variable));
    }

    @Override
    public CSPDouble multiply(VarGetter<Double> variable) {
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1*int2, variable));
    }

    @Override
    public CSPDouble modulo(VarGetter<Double> variable) {
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1%int2, variable));
    }

    @Override
    public Double getValue(Map<String, Double> maps) {
        Double value = super.getValue(maps);
        if(value == null)
            return null;
        for(Pair<BinaryOperator<Double>, VarGetter<Double>> pair : this.operatorList){
            value = pair.getL().apply(value, pair.getR().getValue(maps));
        }
        return value;
    }
}
