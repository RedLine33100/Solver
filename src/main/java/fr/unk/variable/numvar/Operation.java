package fr.unk.variable.numvar;

import fr.unk.variable.Getter;
import fr.unk.variable.Variable;

import java.util.function.BinaryOperator;

/**
 * Store the operation and the variable for the Calcul class
 * @param <T> the value type
 */
public class Operation<T> {

    BinaryOperator<T> binaryOperator, revertOperator;
    Getter<T> variable, previous;

    public Operation(BinaryOperator<T> binaryOperator, BinaryOperator<T> revertOperator, Getter<T> previous, Getter<T> variable){
        this.binaryOperator = binaryOperator;
        this.revertOperator = revertOperator;
        this.variable = variable;
        this.previous = previous;
    }

    public Operation(BinaryOperator<T> binaryOperator, Getter<T> previous, Getter<T> variable){
        this(binaryOperator, null, previous, variable);
    }

    public BinaryOperator<T> getBinaryOperator() {
        return binaryOperator;
    }

    public BinaryOperator<T> getRevertOperator() {
        return revertOperator;
    }

    public Getter<T> getPrevious() {
        return previous;
    }

    public Getter<T> getVariable() {
        return variable;
    }

    public void addDepend(Variable<T> depend){
        if(variable.isVar())
            ((Variable<T>) variable).addDepend(depend);
        if (previous != null && previous.isVar())
            ((Variable<T>) previous).addDepend(depend);
    }

}
