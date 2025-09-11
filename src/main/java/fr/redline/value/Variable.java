package fr.redline.value;

import fr.redline.contrainte.Constraint;
import fr.redline.domaine.Domain;

import java.util.LinkedHashSet;

public class Variable<T> {

    private final String name;
    private final VarType varType;
    private final LinkedHashSet<Variable<T>> linkedVars = new LinkedHashSet<>();
    private final LinkedHashSet<Constraint<T>> linkedConstraints = new LinkedHashSet<>();
    private T value = null;
    private Domain<T> domain = null;

    protected Variable(String name) {
        this.name = name;
        this.varType = VarType.UNKNOWN;
    }

    public Variable(String name, T value) {
        this.name = name;
        this.value = value;
        this.varType = VarType.ALREADY_CALCULATED;
    }

    public Variable(String name, Domain<T> domain) {
        this.name = name;
        this.domain = domain;
        this.varType = VarType.UNKNOWN;
    }

    public Variable(T value) {
        this.name = "name";
        this.value = value;
        this.varType = VarType.ALREADY_CALCULATED;
    }

    public Variable(Domain<T> domain) {
        this.name = "name";
        this.domain = domain;
        this.varType = VarType.UNKNOWN;
    }

    public T getValue() {
        return this.value;
    }

    public boolean setValue(T value) {

        if (value == this.value)
            return false;

        if (value != null && domain != null)
            if (!domain.inDomain(value))
                return false;

        this.value = value;
        for (Variable<T> v : linkedVars) {
            v.setValue(null);
        }

        return true;
    }

    public VarType getType() {
        return this.varType;
    }

    public LinkedHashSet<Variable<T>> getLinkedValue() {
        return linkedVars;
    }

    public void registerLinkedValue(Variable<T> variable) {
        this.linkedVars.add(variable);
    }

    public void addLinkedConstraint(Constraint<T> constraint) {
        linkedConstraints.add(constraint);
    }

    public LinkedHashSet<Constraint<T>> getLinkedConstraints() {
        return linkedConstraints;
    }

    public LinkedHashSet<Variable<T>> getUnknownVariables() {
        if (varType == VarType.UNKNOWN) {
            LinkedHashSet<Variable<T>> vars = new LinkedHashSet<>();
            vars.add(this);
            return vars;
        }
        return new LinkedHashSet<>();
    }

    public Domain<T> getDomain() {
        return this.domain;
    }

    public String getName() {
        return name;
    }
}
