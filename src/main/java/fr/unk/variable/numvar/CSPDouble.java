package fr.unk.variable.numvar;

import fr.unk.variable.VarGetter;

import java.util.List;

public class CSPDouble extends Calcul<Double> {
    public CSPDouble(String varName) {
        super(varName, Double.class);
    }

    CSPDouble(String varName, List<Operation<Double>> operationList) {
        super(varName, Double.class, operationList);
    }

    @Override
    public CSPDouble add(VarGetter<Double> variable){
        return (CSPDouble) this.copyAddCalc(Double::sum, (int1, int2) -> int1-int2, variable);
    }

    @Override
    public CSPDouble remove(VarGetter<Double> variable){
        return (CSPDouble) this.copyAddCalc((int1, int2) -> int1-int2, Double::sum, variable);
    }

    @Override
    public CSPDouble divide(VarGetter<Double> variable) {
        return (CSPDouble) this.copyAddCalc((int1, int2) -> int1/int2, (int1, int2) -> int1*int2, variable);
    }

    @Override
    public CSPDouble multiply(VarGetter<Double> variable) {
        return (CSPDouble) this.copyAddCalc((int1, int2) -> int1*int2, (int1, int2) -> int1/int2, variable);
    }

    @Override
    public CSPDouble modulo(VarGetter<Double> variable) {
        return (CSPDouble) this.copyAddCalc((int1, int2) -> int1%int2, null, variable);
    }

    @Override
    Calcul<Double> newCopy(List<Operation<Double>> operations) {
        return new CSPDouble(this.getVarName(), operations);
    }
}
