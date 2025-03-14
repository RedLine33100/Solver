package fr.unk.variable.numvar;

import fr.unk.utils.Pair;
import fr.unk.variable.VarGetter;

import java.util.function.BinaryOperator;

public class CalculInt extends Calcul<Integer> {

    public CalculInt(VarGetter<Integer> prev){
        super(prev, null);
    }

    CalculInt(VarGetter<Integer> prev, Pair<BinaryOperator<Integer>, VarGetter<Integer>> pair){
        super(prev, pair);
    }

    private CalculInt copyAddCalc(Pair<BinaryOperator<Integer>, VarGetter<Integer>> calc){
        return new CalculInt(this, calc);
    }

    @Override
    public CalculInt add(VarGetter<Integer> variable){
        return this.copyAddCalc(new Pair<>(Integer::sum, variable));
    }

    @Override
    public CalculInt remove(VarGetter<Integer> variable){
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1-int2, variable));
    }

    @Override
    public CalculInt divide(VarGetter<Integer> variable) {
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1/int2, variable));
    }

    @Override
    public CalculInt multiply(VarGetter<Integer> variable) {
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1*int2, variable));
    }

    @Override
    public CalculInt modulo(VarGetter<Integer> variable) {
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1%int2, variable));
    }
}
