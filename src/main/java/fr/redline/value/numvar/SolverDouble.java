package fr.redline.value.numvar;

import fr.redline.utils.Triplet;
import fr.redline.value.Value;

import java.util.function.BinaryOperator;

public class SolverDouble extends Calcul<Double> {

    public SolverDouble(Value<Double> prev){
        super(prev, null);
    }

    SolverDouble(Value<Double> previous, Triplet<BinaryOperator<Double>, BinaryOperator<Double>, Value<Double>> calcul){
        super(previous, calcul);
    }

    private SolverDouble copyAddCalc(Triplet<BinaryOperator<Double>, BinaryOperator<Double>, Value<Double>> calcul){
        return new SolverDouble(this, calcul);
    }

    @Override
    public SolverDouble add(Value<Double> variable){
        return this.copyAddCalc(new Triplet<>(Double::sum, (int1, int2) -> int1-int2, variable));
    }

    @Override
    public SolverDouble remove(Value<Double> variable){
        return this.copyAddCalc(new Triplet<>((int1, int2) -> int1-int2, Double::sum, variable));
    }

    @Override
    public SolverDouble divide(Value<Double> variable) {
        return this.copyAddCalc(new Triplet<>((int1, int2) -> int1/int2, (int1, int2) -> int1*int2, variable));
    }

    @Override
    public SolverDouble multiply(Value<Double> variable) {
        return this.copyAddCalc(new Triplet<>((int1, int2) -> int1*int2, (int1, int2) -> int1/int2, variable));
    }

    @Override
    public SolverDouble modulo(Value<Double> variable) {
        return this.copyAddCalc(new Triplet<>((int1, int2) -> int1%int2, (int1, int2) -> null, variable));
    }

}
