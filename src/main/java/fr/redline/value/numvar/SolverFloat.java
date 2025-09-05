package fr.redline.value.numvar;

import fr.redline.utils.Triplet;
import fr.redline.value.Value;

import java.util.function.BinaryOperator;

public class SolverFloat extends Calcul<Float> {

    public SolverFloat(Value<Float> prev){
        super(prev, null);
    }

    SolverFloat(Value<Float> previous, Triplet<BinaryOperator<Float>, BinaryOperator<Float>, Value<Float>> calcul){
        super(previous, calcul);
    }

    private SolverFloat copyAddCalc(Triplet<BinaryOperator<Float>, BinaryOperator<Float>, Value<Float>> calcul){
        return new SolverFloat(this, calcul);
    }

    @Override
    public SolverFloat add(Value<Float> variable){
        return this.copyAddCalc(new Triplet<>(Float::sum, (int1, int2) -> int1-int2, variable));
    }

    @Override
    public SolverFloat remove(Value<Float> variable){
        return this.copyAddCalc(new Triplet<>((int1, int2) -> int1-int2, Float::sum, variable));
    }

    @Override
    public SolverFloat divide(Value<Float> variable) {
        return this.copyAddCalc(new Triplet<>((int1, int2) -> int1/int2, (int1, int2) -> int1*int2, variable));
    }

    @Override
    public SolverFloat multiply(Value<Float> variable) {
        return this.copyAddCalc(new Triplet<>((int1, int2) -> int1*int2, (int1, int2) -> int1/int2, variable));
    }

    @Override
    public SolverFloat modulo(Value<Float> variable) {
        return this.copyAddCalc(new Triplet<>((int1, int2) -> int1%int2, (int1, int2) -> null, variable));
    }

}
