package fr.redline.value.numvar;

import fr.redline.utils.Pair;
import fr.redline.value.ValueGetter;

import java.util.function.BinaryOperator;

public class CalculDouble extends Calcul<Double> {

    public CalculDouble(ValueGetter<Double> prev){
        super(prev, null);
    }

    CalculDouble(ValueGetter<Double> previous, Pair<BinaryOperator<Double>, ValueGetter<Double>> calcul){
        super(previous, calcul);
    }

    private CalculDouble copyAddCalc(Pair<BinaryOperator<Double>, ValueGetter<Double>> calc){
        return new CalculDouble(this, calc);
    }

    @Override
    public CalculDouble add(ValueGetter<Double> variable){
        return this.copyAddCalc(new Pair<>(Double::sum, variable));
    }

    @Override
    public CalculDouble remove(ValueGetter<Double> variable){
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1-int2, variable));
    }

    @Override
    public CalculDouble divide(ValueGetter<Double> variable) {
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1/int2, variable));
    }

    @Override
    public CalculDouble multiply(ValueGetter<Double> variable) {
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1*int2, variable));
    }

    @Override
    public CalculDouble modulo(ValueGetter<Double> variable) {
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1%int2, variable));
    }

}
