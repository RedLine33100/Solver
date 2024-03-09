package fr.unk.variable.numvar;

import fr.unk.variable.Getter;

public class CSPInt extends Calcul<Integer> {
    public CSPInt(String varName) {
        super(varName);
    }

    CSPInt(String varName, Operation<Integer> operationList){
        super(varName, operationList);
    }

    @Override
    public CSPInt add(Getter<Integer> variable){
        return this.newCopy(new Operation<>(Integer::sum, (int1, int2) -> int1-int2, this, variable));
    }

    @Override
    public CSPInt remove(Getter<Integer> variable){
        return this.newCopy(new Operation<>((int1, int2) -> int1-int2, Integer::sum, this, variable));
    }

    @Override
    public CSPInt divide(Getter<Integer> variable) {
        return this.newCopy(new Operation<>((int1, int2) -> int1/int2, (int1, int2) -> int1*int2, this, variable));
    }

    @Override
    public CSPInt multiply(Getter<Integer> variable) {
        return this.newCopy(new Operation<>((int1, int2) -> int1*int2, (int1, int2) -> int1/int2, this, variable));
    }

    @Override
    public CSPInt modulo(Getter<Integer> variable) {
        return this.newCopy(new Operation<>((int1, int2) -> int1 % int2, this, variable));
    }

    @Override
    CSPInt newCopy(Operation<Integer> operations) {
        CSPInt cspDouble = new CSPInt(this.getVarName(), operations);
        operations.addDepend(cspDouble);
        return cspDouble;
    }
}
