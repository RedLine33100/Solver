package fr.unk.variable.numvar;

import fr.unk.variable.VarGetter;
import fr.unk.variable.Variable;

import java.util.function.BinaryOperator;

public class Operation<T> {

    BinaryOperator<T> binaryOperator, revertOperator;
    VarGetter<T> variable;

    public Operation(BinaryOperator<T> binaryOperator, BinaryOperator<T> revertOperator, VarGetter<T> variable){
        this.binaryOperator = binaryOperator;
        this.revertOperator = revertOperator;
        this.variable = variable;
    }

    public Operation(BinaryOperator<T> binaryOperator, VarGetter<T> variable){
        this(binaryOperator, null, variable);
    }

    public BinaryOperator<T> getBinaryOperator() {
        return binaryOperator;
    }

    public BinaryOperator<T> getRevertOperator() {
        return revertOperator;
    }

    public VarGetter<T> getVariable() {
        return variable;
    }

    public boolean canRevert(){
        return revertOperator != null;
    }
}
