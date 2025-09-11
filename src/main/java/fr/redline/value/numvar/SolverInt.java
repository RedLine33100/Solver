package fr.redline.value.numvar;

import fr.redline.domaine.Domain;
import fr.redline.utils.Triplet;
import fr.redline.value.Variable;

import java.util.function.BinaryOperator;

public class SolverInt extends Calcul<Integer> {

    public SolverInt(Variable<Integer> prev) {
        super(prev, null);
    }

    public SolverInt(String name, Integer actual) {
        super(name, actual);
    }

    public SolverInt(String name, Domain<Integer> actual) {
        super(name, actual);
    }

    SolverInt(Variable<Integer> previous, Triplet<BinaryOperator<Integer>, BinaryOperator<Integer>, Variable<Integer>> calcul) {
        super(previous, calcul);
    }

    private SolverInt copyAddCalc(Triplet<BinaryOperator<Integer>, BinaryOperator<Integer>, Variable<Integer>> calcul) {
        return new SolverInt(this, calcul);
    }

    @Override
    public SolverInt add(Variable<Integer> variable) {
        return this.copyAddCalc(new Triplet<>(Integer::sum, (int1, int2) -> int1 - int2, variable));
    }

    @Override
    public SolverInt remove(Variable<Integer> variable) {
        return this.copyAddCalc(new Triplet<>((int1, int2) -> int1 - int2, Integer::sum, variable));
    }

    @Override
    public SolverInt divide(Variable<Integer> variable) {
        return this.copyAddCalc(new Triplet<>((int1, int2) -> int1 / int2, (int1, int2) -> int1 * int2, variable));
    }

    @Override
    public SolverInt multiply(Variable<Integer> variable) {
        return this.copyAddCalc(new Triplet<>((int1, int2) -> int1 * int2, (int1, int2) -> int1 / int2, variable));
    }

    @Override
    public SolverInt modulo(Variable<Integer> variable) {
        return this.copyAddCalc(new Triplet<>((int1, int2) -> int1 % int2, (int1, int2) -> null, variable));
    }

}
