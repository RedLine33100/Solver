package fr.unk.variable.numvar;

import fr.unk.variable.VarGetter;

import java.util.List;

public class CSPFloat extends Calcul<Float> {
    public CSPFloat(String varName) {
        super(varName, Float.class);
    }

    CSPFloat(String varName, List<Operation<Float>> operationList){
        super(varName, Float.class, operationList);
    }

    @Override
    public CSPFloat add(VarGetter<Float> variable){
        return (CSPFloat) this.copyAddCalc((int1, int2) -> int1+int2, (int1, int2) -> int1-int2 , variable);
    }

    @Override
    public CSPFloat remove(VarGetter<Float> variable){
        return (CSPFloat) this.copyAddCalc((int1, int2) -> int1-int2, (int1, int2) -> int1+int2, variable);
    }

    @Override
    public CSPFloat divide(VarGetter<Float> variable) {
        return (CSPFloat) this.copyAddCalc((int1, int2) -> int1/int2, (int1, int2) -> int1*int2, variable);
    }

    @Override
    public CSPFloat multiply(VarGetter<Float> variable) {
        return (CSPFloat) this.copyAddCalc((int1, int2) -> int1*int2, (int1, int2) -> int1/int2, variable);
    }

    @Override
    public CSPFloat modulo(VarGetter<Float> variable) {
        return (CSPFloat) this.copyAddCalc((int1, int2) -> int1%int2, null, variable);
    }

    @Override
    Calcul<Float> newCopy(List<Operation<Float>> operationList) {
        return new CSPFloat(this.getVarName(), operationList);
    }
}
