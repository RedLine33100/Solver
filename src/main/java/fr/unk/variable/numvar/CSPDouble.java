package fr.unk.variable.numvar;

import fr.unk.variable.VarGetter;
import fr.unk.variable.Variable;

public class CSPDouble extends Calcul<Double> {
    public CSPDouble(String varName) {
        super(varName);
    }

    CSPDouble(String varName, Operation<Double> operationList) {
        super(varName, operationList);
    }

    @Override
    public CSPDouble add(VarGetter<Double> variable){
        return this.newCopy(new Operation<>(Double::sum, (int1, int2) -> int1-int2, this, variable));
    }

    @Override
    public CSPDouble remove(VarGetter<Double> variable){
        return this.newCopy(new Operation<>((int1, int2) -> int1-int2, Double::sum, this, variable));
    }

    @Override
    public CSPDouble divide(VarGetter<Double> variable) {
        return this.newCopy(new Operation<>((int1, int2) -> int1/int2, (int1, int2) -> int1*int2, this, variable));
    }

    @Override
    public CSPDouble multiply(VarGetter<Double> variable) {
        return this.newCopy(new Operation<>((int1, int2) -> int1*int2, (int1, int2) -> int1/int2, this, variable));
    }

    @Override
    public CSPDouble modulo(VarGetter<Double> variable) {
        return this.newCopy(new Operation<>((int1, int2) -> int1%int2, null, this, variable));
    }

    @Override
    CSPDouble newCopy(Operation<Double> operations) {
        CSPDouble cspDouble = new CSPDouble(this.getVarName(), operations);
        operations.addDepend(cspDouble);
        return cspDouble;
    }
}
