package fr.redline.contrainte.vc;

import fr.redline.contrainte.Constraint;
import fr.redline.contrainte.ConstraintResult;
import fr.redline.utils.Pair;
import fr.redline.value.Variable;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class EqualsConstraint<T extends Comparable<T>> implements Constraint<T> {

    private final Variable<T> fv, sv;
    private final LinkedHashSet<Variable<T>> unknownVariables = new LinkedHashSet<>();

    public EqualsConstraint(Variable<T> constraint1, Variable<T> constraint2) {
        this.fv = constraint1;
        this.sv = constraint2;
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

        return vf.compareTo(vs) == 0 ? ConstraintResult.TRUE : ConstraintResult.FALSE;
    }

    @Override
    public LinkedHashSet<Variable<T>> getUnknownVariables() {
        return unknownVariables;
    }

    @Override
    public Pair<List<Variable<T>>, Integer> reverseVariables(T reversedValue) {
        return new Pair<>(new ArrayList<>(), 0);
    }

    @Override
    public Pair<List<Variable<T>>, Integer> tryReverse() {
        return new Pair<>(new ArrayList<>(), 0);
    }

}
