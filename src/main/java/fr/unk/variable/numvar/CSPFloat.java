package fr.unk.variable.numvar;

import fr.unk.variable.VarGetter;

import java.util.List;

public class CSPFloat extends Calcul<Float> {
    public CSPFloat(String varName) {
        super(varName);
    }

    CSPFloat(String varName, Operation<Float> operationList){
        super(varName, operationList);
    }

    @Override
    public CSPFloat add(VarGetter<Float> variable){
        return this.newCopy(new Operation<>((int1, int2) -> int1+int2, (int1, int2) -> int1-int2, this, variable));
    }

    @Override
    public CSPFloat remove(VarGetter<Float> variable){
        return this.newCopy(new Operation<>((int1, int2) -> int1-int2, (int1, int2) -> int1+int2, this, variable));
    }

    @Override
    public CSPFloat divide(VarGetter<Float> variable) {
        return this.newCopy(new Operation<>((int1, int2) -> int1/int2, (int1, int2) -> int1*int2, this, variable));
    }

    @Override
    public CSPFloat multiply(VarGetter<Float> variable) {
        return this.newCopy(new Operation<>((int1, int2) -> int1*int2, (int1, int2) -> int1/int2, this, variable));
    }

    @Override
    public CSPFloat modulo(VarGetter<Float> variable) {
        return this.newCopy(new Operation<>((int1, int2) -> int1%int2, null, variable));
    }

    @Override
    CSPFloat newCopy(Operation<Float> operations) {
        CSPFloat cspDouble = new CSPFloat(this.getVarName(), operations);
        operations.addDepend(cspDouble);
        return cspDouble;
    }
}
