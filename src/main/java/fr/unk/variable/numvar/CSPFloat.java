package fr.unk.variable.numvar;

import fr.unk.utils.Pair;
import fr.unk.variable.Variable;

import java.util.Map;
import java.util.function.BinaryOperator;

public class CSPFloat extends Calcul<Float> {
    public CSPFloat(String varName) {
        super(varName, Float.class);
    }

    public CSPFloat(Float value) {
        super(value);
    }

    public void add(Variable<Float> variable){
        addCalcul(Float::sum, variable);
    }

    public void remove(Variable<Float> variable){
        addCalcul((int1, int2) -> int1-int2, variable);
    }

    @Override
    public void divide(Variable<Float> variable) {

        addCalcul((int1, int2) -> int1/int2, variable);
    }

    @Override
    public void multiply(Variable<Float> variable) {
        addCalcul((int1, int2) -> int1*int2, variable);
    }

    @Override
    public void modulo(Variable<Float> variable) {
        addCalcul((int1, int2) -> int1%int2, variable);
    }

    @Override
    public Float getValue(Map<String, Object> maps) {
        Float value = super.getValue(maps);
        if(value == null)
            return null;
        for(Pair<BinaryOperator<Float>, Variable<Float>> pair : this.operatorList){
            value = pair.getL().apply(value, pair.getR().getValue(maps));
        }
        return value;
    }
}
