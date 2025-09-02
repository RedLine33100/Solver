package fr.redline.contrainte;

import fr.redline.value.variable.Variable;

import java.util.LinkedHashSet;
import java.util.function.BiFunction;

public class BooleanConstraint<T> implements Constraint<T>{

    public static <T> BooleanConstraint<T> or(Constraint<T> co1, Constraint<T> co2) {
        return new BooleanConstraint<>(co1, co2, (c1, c2) -> {
            ConstraintResult res1 = c1.satisfied();
            ConstraintResult res2 = c2.satisfied();
            if(res1==ConstraintResult.UNKNOWN || res2==ConstraintResult.UNKNOWN)
                return ConstraintResult.UNKNOWN;
            return res1 == ConstraintResult.TRUE || res2 == ConstraintResult.TRUE ? ConstraintResult.TRUE : ConstraintResult.FALSE;
        });
    }

    public static <T> BooleanConstraint<T> and(Constraint<T> co1, Constraint<T> co2) {
        return new BooleanConstraint<>(co1, co2, (c1, c2) -> {
            ConstraintResult res1 = c1.satisfied();
            if(res1 != ConstraintResult.TRUE)
                return res1;

            return c2.satisfied();
        });
    }

    public static <T> BooleanConstraint<T> nor(Constraint<T> co1, Constraint<T> co2) {
        return new BooleanConstraint<>(co1, co2, (c1, c2) -> {
            ConstraintResult c1s = c1.satisfied();
            if(c1s == ConstraintResult.UNKNOWN)
                return ConstraintResult.UNKNOWN;

            ConstraintResult c2s = c2.satisfied();
            if(c2s == ConstraintResult.UNKNOWN)
                return ConstraintResult.UNKNOWN;

            return c1s != c2s ? ConstraintResult.TRUE : ConstraintResult.FALSE;
        });
    }

    private final BiFunction<Constraint<T>, Constraint<T>, ConstraintResult> biFunction;
    private final Constraint<T> constraint1, constraint2;
    private LinkedHashSet<Variable<T>> unknownVariables = new LinkedHashSet<>();

    private BooleanConstraint(Constraint<T> constraint1, Constraint<T> constraint2,  BiFunction<Constraint<T>, Constraint<T>, ConstraintResult> biFunction) {
        this.constraint1 = constraint1;
        this.constraint2 = constraint2;
        this.biFunction = biFunction;
        this.unknownVariables.addAll(constraint1.getUnknownVariables());
        this.unknownVariables.addAll(constraint2.getUnknownVariables());
    }

    @Override
    public ConstraintResult satisfied() {
        return biFunction.apply(constraint1, constraint2);
    }

    @Override
    public LinkedHashSet<Variable<T>> getUnknownVariables() {
        return unknownVariables;
    }
}
