package fr.unk.variable.numvar;

import fr.unk.utils.Pair;
import fr.unk.variable.VarGetter;

import java.util.function.BinaryOperator;

public class CalculFloat extends Calcul<Float> {

    public CalculFloat(VarGetter<Float> prev){
        super(prev, null);
    }

    CalculFloat(VarGetter<Float> previous, Pair<BinaryOperator<Float>, VarGetter<Float>> pair){
        super(previous, pair);
    }

    private CalculFloat copyAddCalc(Pair<BinaryOperator<Float>, VarGetter<Float>> calc){
        return new CalculFloat(this, calc);
    }

    @Override
    public CalculFloat add(VarGetter<Float> variable){
        return this.copyAddCalc(new Pair<>(Float::sum, variable));
    }

    @Override
    public CalculFloat remove(VarGetter<Float> variable){
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1-int2, variable));
    }

    @Override
    public CalculFloat divide(VarGetter<Float> variable) {
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1/int2, variable));
    }

    @Override
    public CalculFloat multiply(VarGetter<Float> variable) {
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1*int2, variable));
    }

    @Override
    public CalculFloat modulo(VarGetter<Float> variable) {
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1%int2, variable));
    }
}
