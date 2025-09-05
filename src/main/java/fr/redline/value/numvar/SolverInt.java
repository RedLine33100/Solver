package fr.redline.value.numvar;

import fr.redline.utils.Triplet;
import fr.redline.value.Value;

import java.util.function.BinaryOperator;

public class SolverInt extends Calcul<Integer> {

    public SolverInt(Value<Integer> prev){
        super(prev, null);
    }

    SolverInt(Value<Integer> previous, Triplet<BinaryOperator<Integer>, BinaryOperator<Integer>, Value<Integer>> calcul){
        super(previous, calcul);
    }

    private SolverInt copyAddCalc(Triplet<BinaryOperator<Integer>, BinaryOperator<Integer>, Value<Integer>> calcul){
        return new SolverInt(this, calcul);
    }

    @Override
    public SolverInt add(Value<Integer> variable){
        return this.copyAddCalc(new Triplet<>(Integer::sum, (int1, int2) -> int1-int2, variable));
    }

    @Override
    public SolverInt remove(Value<Integer> variable){
        return this.copyAddCalc(new Triplet<>((int1, int2) -> int1-int2, Integer::sum, variable));
    }

    @Override
    public SolverInt divide(Value<Integer> variable) {
        return this.copyAddCalc(new Triplet<>((int1, int2) -> int1/int2, (int1, int2) -> int1*int2, variable));
    }

    @Override
    public SolverInt multiply(Value<Integer> variable) {
        return this.copyAddCalc(new Triplet<>((int1, int2) -> int1*int2, (int1, int2) -> int1/int2, variable));
    }

    @Override
    public SolverInt modulo(Value<Integer> variable) {
        return this.copyAddCalc(new Triplet<>((int1, int2) -> int1%int2, (int1, int2) -> null, variable));
    }

}
