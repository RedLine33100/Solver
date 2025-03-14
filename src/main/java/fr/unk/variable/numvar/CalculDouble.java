package fr.unk.variable.numvar;

import fr.unk.utils.Pair;
import fr.unk.variable.VarGetter;

import java.util.function.BinaryOperator;

public class CalculDouble extends Calcul<Double> {

    public CalculDouble(VarGetter<Double> prev){
        super(prev, null);
    }

    CalculDouble(VarGetter<Double> previous, Pair<BinaryOperator<Double>, VarGetter<Double>> calcul){
        super(previous, calcul);
    }

    private CalculDouble copyAddCalc(Pair<BinaryOperator<Double>, VarGetter<Double>> calc){
        return new CalculDouble(this, calc);
    }

    @Override
    public CalculDouble add(VarGetter<Double> variable){
        return this.copyAddCalc(new Pair<>(Double::sum, variable));
    }

    @Override
    public CalculDouble remove(VarGetter<Double> variable){
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1-int2, variable));
    }

    @Override
    public CalculDouble divide(VarGetter<Double> variable) {
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1/int2, variable));
    }

    @Override
    public CalculDouble multiply(VarGetter<Double> variable) {
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1*int2, variable));
    }

    @Override
    public CalculDouble modulo(VarGetter<Double> variable) {
        return this.copyAddCalc(new Pair<>((int1, int2) -> int1%int2, variable));
    }

}
