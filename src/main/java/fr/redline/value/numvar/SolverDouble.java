package fr.redline.value.numvar;

import fr.redline.domaine.Domain;
import fr.redline.utils.Triplet;
import fr.redline.value.Variable;

import java.util.function.BinaryOperator;

public class SolverDouble extends Calcul<Double> {

    public SolverDouble(String name, Double actual) {
        super(name, actual);
    }

    public SolverDouble(String name, Domain<Double> actual) {
        super(name, actual);
    }

    SolverDouble(Calcul<Double> previous, Triplet<BinaryOperator<Double>, BinaryOperator<Double>, Variable<Double>> calcul) {
        super(previous, calcul);
    }

    private SolverDouble copyAddCalc(Triplet<BinaryOperator<Double>, BinaryOperator<Double>, Variable<Double>> calcul) {
        return new SolverDouble(this, calcul);
    }

    @Override
    public SolverDouble add(Variable<Double> variable) {
        return this.copyAddCalc(new Triplet<>(Double::sum, (int1, int2) -> int1 - int2, variable));
    }

    @Override
    public SolverDouble remove(Variable<Double> variable) {
        return this.copyAddCalc(new Triplet<>((int1, int2) -> int1 - int2, Double::sum, variable));
    }

    @Override
    public SolverDouble divide(Variable<Double> variable) {
        return this.copyAddCalc(new Triplet<>((int1, int2) -> int1 / int2, (int1, int2) -> int1 * int2, variable));
    }

    @Override
    public SolverDouble multiply(Variable<Double> variable) {
        return this.copyAddCalc(new Triplet<>((int1, int2) -> int1 * int2, (int1, int2) -> int1 / int2, variable));
    }

    @Override
    public SolverDouble modulo(Variable<Double> variable) {
        return this.copyAddCalc(new Triplet<>((int1, int2) -> int1 % int2, (int1, int2) -> null, variable));
    }

}
