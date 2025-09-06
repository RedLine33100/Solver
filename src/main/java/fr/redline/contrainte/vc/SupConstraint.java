package fr.redline.contrainte.vc;

import fr.redline.contrainte.Constraint;
import fr.redline.contrainte.ConstraintResult;
import fr.redline.utils.Pair;
import fr.redline.value.Value;
import fr.redline.value.variable.Variable;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class SupConstraint<T extends Comparable<T>> implements Constraint<T> {

    private final Value<T> fv, sv;
    private final LinkedHashSet<Variable<T>> unknownVariables = new LinkedHashSet<>();
    private final boolean equals;

    public SupConstraint(Value<T> constraint1, Value<T> constraint2, boolean equals) {
        this.fv = constraint1;
        this.sv = constraint2;
        this.equals = equals;
        this.unknownVariables.addAll(constraint1.getUnknownVariables());
        this.unknownVariables.addAll(constraint2.getUnknownVariables());
    }

    @Override
    public ConstraintResult evaluate() {
        T vf = fv.getValue();
        if(vf == null)
            return ConstraintResult.UNKNOWN;
        T vs = sv.getValue();
        if(vs == null)
            return ConstraintResult.UNKNOWN;

        int val = vf.compareTo(vs);
        boolean res = equals ? val >= 0 : val > 0;

        return res ? ConstraintResult.TRUE : ConstraintResult.FALSE;
    }

    @Override
    public LinkedHashSet<Variable<T>> getUnknownVariables() {
        return unknownVariables;
    }

    @Override
    public Pair<List<Value<T>>, Integer> reverseVariables(T reversedValue) {
        return new Pair<>(new ArrayList<>(), 0);
    }

    @Override
    public Pair<List<Value<T>>, Integer> tryReverse() {
        return new Pair<>(new ArrayList<>(), 0);
    }

}
