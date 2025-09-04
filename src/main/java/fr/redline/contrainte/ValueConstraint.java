package fr.redline.contrainte;

import fr.redline.value.ValueGetter;
import fr.redline.value.variable.Variable;

import java.util.LinkedHashSet;
import java.util.function.BiFunction;

public class ValueConstraint<T extends Comparable<T>> implements Constraint<T>{

    public static <T extends Comparable<T>> ValueConstraint<T> diff(ValueGetter<T> co1, ValueGetter<T> co2) {
        return new ValueConstraint<>(co1, co2, (fv, sv) -> {
            T vf = fv.getValue();
            if(vf == null)
                return ConstraintResult.UNKNOWN;

            T vs = sv.getValue();
            if(vs == null)
                return ConstraintResult.UNKNOWN;

            return vf.compareTo(vs) != 0 ? ConstraintResult.TRUE : ConstraintResult.FALSE;
        });
    }

    public static <T extends Comparable<T>> ValueConstraint<T> equals(ValueGetter<T> co1, ValueGetter<T> co2) {
        return new ValueConstraint<>(co1, co2, (fv, sv) -> {
            T vf = fv.getValue();
            if(vf == null)
                return ConstraintResult.UNKNOWN;

            T vs = sv.getValue();
            if(vs == null)
                return ConstraintResult.UNKNOWN;

            return vf.compareTo(vs) == 0 ? ConstraintResult.TRUE : ConstraintResult.FALSE;
        });
    }

    public static <T extends Comparable<T>> ValueConstraint<T> inf(ValueGetter<T> co1, ValueGetter<T> co2, boolean equals) {
        return new ValueConstraint<>(co1, co2, (fv, sv) -> {
            T vf = fv.getValue();
            if(vf == null)
                return ConstraintResult.UNKNOWN;
            T vs = sv.getValue();
            if(vs == null)
                return ConstraintResult.UNKNOWN;

            int val = vf.compareTo(vs);
            boolean res = equals ? val <= 0 : val < 0;

            return res ? ConstraintResult.TRUE : ConstraintResult.FALSE;
        });
    }

    public static <T extends Comparable<T>> ValueConstraint<T> sup(ValueGetter<T> co1, ValueGetter<T> co2, boolean equals) {
        return new ValueConstraint<>(co1, co2, (fv, sv) -> {
            T vf = fv.getValue();
            if(vf == null)
                return ConstraintResult.UNKNOWN;
            T vs = sv.getValue();
            if(vs == null)
                return ConstraintResult.UNKNOWN;

            int val = vf.compareTo(vs);
            boolean res = equals ? val >= 0 : val > 0;

            return res ? ConstraintResult.TRUE : ConstraintResult.FALSE;
        });
    }


    private final BiFunction<ValueGetter<T>, ValueGetter<T>, ConstraintResult> biFunction;
    private final ValueGetter<T> constraint1, constraint2;
    private LinkedHashSet<Variable<T>> unknownVariables = new LinkedHashSet<>();

    private ValueConstraint(ValueGetter<T> constraint1, ValueGetter<T> constraint2, BiFunction<ValueGetter<T>, ValueGetter<T>, ConstraintResult> biFunction) {
        this.constraint1 = constraint1;
        this.constraint2 = constraint2;
        this.biFunction = biFunction;
        this.unknownVariables.addAll(constraint1.getUnknownVariables());
        this.unknownVariables.addAll(constraint2.getUnknownVariables());
    }

    @Override
    public ConstraintResult evaluate() {
        return biFunction.apply(constraint1, constraint2);
    }

    @Override
    public LinkedHashSet<Variable<T>> getUnknownVariables() {
        return unknownVariables;
    }
}
