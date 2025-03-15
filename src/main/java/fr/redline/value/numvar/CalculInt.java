package fr.redline.value.numvar;

import fr.redline.utils.Pair;
import fr.redline.value.ValueGetter;

import java.util.function.BinaryOperator;

public class CalculInt extends Calcul<Integer> {

    public CalculInt(ValueGetter<Integer> prev){
        super(prev, null);
    }

    CalculInt(ValueGetter<Integer> prev, Pair<BinaryOperator<Integer>, ValueGetter<Integer>> pair){
        super(prev, pair);
    }

    private CalculInt copyAddCalc(Pair<BinaryOperator<Integer>, ValueGetter<Integer>> calc){
        return new CalculInt(this, calc);
    }

    @Override
    public CalculInt add(ValueGetter<Integer> variable){
        return this.copyAddCalc(new Pair<>(Integer::sum, variable));
    }

    @Override
    public CalculInt remove(ValueGetter<Integer> variable){
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1-int2, variable));
    }

    @Override
    public CalculInt divide(ValueGetter<Integer> variable) {
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1/int2, variable));
    }

    @Override
    public CalculInt multiply(ValueGetter<Integer> variable) {
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1*int2, variable));
    }

    @Override
    public CalculInt modulo(ValueGetter<Integer> variable) {
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1%int2, variable));
    }
}
