package fr.redline.value.numvar;

import fr.redline.domaine.Domain;
import fr.redline.utils.Triplet;
import fr.redline.value.Variable;

import java.util.function.BinaryOperator;

public class SolverFloat extends Calcul<Float> {

    public SolverFloat(String name, Float actual) {
        super(name, actual);
    }

    public SolverFloat(String name, Domain<Float> actual) {
        super(name, actual);
    }

    SolverFloat(Calcul<Float> previous, Triplet<BinaryOperator<Float>, BinaryOperator<Float>, Variable<Float>> calcul) {
        super(previous, calcul);
    }

    private SolverFloat copyAddCalc(Triplet<BinaryOperator<Float>, BinaryOperator<Float>, Variable<Float>> calcul) {
        return new SolverFloat(this, calcul);
    }

    @Override
    public SolverFloat add(Variable<Float> variable) {
        return this.copyAddCalc(new Triplet<>(Float::sum, (int1, int2) -> int1 - int2, variable));
    }

    @Override
    public SolverFloat remove(Variable<Float> variable) {
        return this.copyAddCalc(new Triplet<>((int1, int2) -> int1 - int2, Float::sum, variable));
    }

    @Override
    public SolverFloat divide(Variable<Float> variable) {
        return this.copyAddCalc(new Triplet<>((int1, int2) -> int1 / int2, (int1, int2) -> int1 * int2, variable));
    }

    @Override
    public SolverFloat multiply(Variable<Float> variable) {
        return this.copyAddCalc(new Triplet<>((int1, int2) -> int1 * int2, (int1, int2) -> int1 / int2, variable));
    }

    @Override
    public SolverFloat modulo(Variable<Float> variable) {
        return this.copyAddCalc(new Triplet<>((int1, int2) -> int1 % int2, (int1, int2) -> null, variable));
    }

}
