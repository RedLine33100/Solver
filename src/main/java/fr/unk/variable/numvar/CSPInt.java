package fr.unk.variable.numvar;

import fr.unk.utils.Pair;
import fr.unk.variable.Variable;

import java.util.Map;
import java.util.function.BinaryOperator;

public class CSPInt extends Calcul<Integer> {
    public CSPInt(String varName) {
        super(varName, Integer.class);
    }

    public CSPInt(Integer value) {
        super(value);
    }

    public void add(Variable<Integer> variable){
        addCalcul(Integer::sum, variable);
    }

    public void remove(Variable<Integer> variable){
        addCalcul((int1, int2) -> int1-int2, variable);
    }

    @Override
    public void divide(Variable<Integer> variable) {

        addCalcul((int1, int2) -> int1/int2, variable);
    }

    @Override
    public void multiply(Variable<Integer> variable) {
        addCalcul((int1, int2) -> int1*int2, variable);
    }

    @Override
    public void modulo(Variable<Integer> variable) {
        addCalcul((int1, int2) -> int1%int2, variable);
    }

    @Override
    public Integer getValue(Map<String, Object> maps) {
        Integer value = super.getValue(maps);
        if(value == null)
            return null;
        for(Pair<BinaryOperator<Integer>, Variable<Integer>> pair : this.operatorList){
            value = pair.getL().apply(value, pair.getR().getValue(maps));
        }
        return value;
    }
}
