package fr.redline.value.numvar;

import fr.redline.utils.Pair;
import fr.redline.value.ValueGetter;

import java.util.function.BinaryOperator;

public class CalculFloat extends Calcul<Float> {

    public CalculFloat(ValueGetter<Float> prev){
        super(prev, null);
    }

    CalculFloat(ValueGetter<Float> previous, Pair<BinaryOperator<Float>, ValueGetter<Float>> pair){
        super(previous, pair);
    }

    private CalculFloat copyAddCalc(Pair<BinaryOperator<Float>, ValueGetter<Float>> calc){
        return new CalculFloat(this, calc);
    }

    @Override
    public CalculFloat add(ValueGetter<Float> variable){
        return this.copyAddCalc(new Pair<>(Float::sum, variable));
    }

    @Override
    public CalculFloat remove(ValueGetter<Float> variable){
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1-int2, variable));
    }

    @Override
    public CalculFloat divide(ValueGetter<Float> variable) {
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1/int2, variable));
    }

    @Override
    public CalculFloat multiply(ValueGetter<Float> variable) {
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1*int2, variable));
    }

    @Override
    public CalculFloat modulo(ValueGetter<Float> variable) {
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1%int2, variable));
    }
}
