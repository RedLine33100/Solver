package fr.unk.variable.numvar;

import fr.unk.utils.Pair;
import fr.unk.variable.Variable;

import java.util.Map;
import java.util.function.BinaryOperator;

public class CSPDouble extends Calcul<Double> {
    public CSPDouble(String varName) {
        super(varName, Double.class);
    }

    public CSPDouble(Double value) {
        super(value);
    }

    public void add(Variable<Double> variable){
        addCalcul(Double::sum, variable);
    }

    public void remove(Variable<Double> variable){
        addCalcul((int1, int2) -> int1-int2, variable);
    }

    @Override
    public void divide(Variable<Double> variable) {

        addCalcul((int1, int2) -> int1/int2, variable);
    }

    @Override
    public void multiply(Variable<Double> variable) {
        addCalcul((int1, int2) -> int1*int2, variable);
    }

    @Override
    public void modulo(Variable<Double> variable) {
        addCalcul((int1, int2) -> int1%int2, variable);
    }

    @Override
    public Double getValue(Map<String, Object> maps) {
        Double value = super.getValue(maps);
        if(value == null)
            return null;
        for(Pair<BinaryOperator<Double>, Variable<Double>> pair : this.operatorList){
            value = pair.getL().apply(value, pair.getR().getValue(maps));
        }
        return value;
    }
}
