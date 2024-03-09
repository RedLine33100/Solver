package fr.unk.variable.numvar;

import fr.unk.variable.VarGetter;
import fr.unk.variable.Variable;

import java.util.function.BinaryOperator;

public class Operation<T> {

    BinaryOperator<T> binaryOperator, revertOperator;
    VarGetter<T> variable, previous;

    public Operation(BinaryOperator<T> binaryOperator, BinaryOperator<T> revertOperator, VarGetter<T> previous, VarGetter<T> variable){
        this.binaryOperator = binaryOperator;
        this.revertOperator = revertOperator;
        this.variable = variable;
        this.previous = previous;
    }

    public Operation(BinaryOperator<T> binaryOperator, VarGetter<T> previous, VarGetter<T> variable){
        this(binaryOperator, null, previous, variable);
    }

    public BinaryOperator<T> getBinaryOperator() {
        return binaryOperator;
    }

    public BinaryOperator<T> getRevertOperator() {
        return revertOperator;
    }

    public VarGetter<T> getPrevious() {
        return previous;
    }

    public VarGetter<T> getVariable() {
        return variable;
    }

    public void addDepend(Variable<T> depend){
        if(variable.isVar())
            ((Variable<T>) variable).addDepend(depend);
        if (previous != null && previous.isVar())
            ((Variable<T>) previous).addDepend(depend);
    }

}
