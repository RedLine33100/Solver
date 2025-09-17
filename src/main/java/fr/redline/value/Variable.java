package fr.redline.value;

import fr.redline.contrainte.Constraint;
import fr.redline.domaine.Domain;
import fr.redline.utils.OptimizedList;

public class Variable<T> {

    private final String name;
    private final VarType varType;
    private final OptimizedList<Variable<T>> linkedVars = new OptimizedList<>();
    private final OptimizedList<Constraint<T>> linkedConstraints = new OptimizedList<>();
    private int varSolverID;
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
        for (Variable<T> v : linkedVars.activeValues()) {
            v.setValue(null);
        }

        return true;
    }

    public VarType getType() {
        return this.varType;
    }

    public OptimizedList<Variable<T>> getLinkedValue() {
        return linkedVars;
    }

    public void registerLinkedValue(Variable<T> variable) {
        this.linkedVars.add(variable);
    }

    public void addLinkedConstraint(Constraint<T> constraint) {
        linkedConstraints.add(constraint);
    }

    public OptimizedList<Constraint<T>> getLinkedConstraints() {
        return linkedConstraints;
    }

    public OptimizedList<Variable<T>> getUnknownVariables() {
        if (varType == VarType.UNKNOWN) {
            OptimizedList<Variable<T>> vars = new OptimizedList<>();
            vars.add(this);
            return vars;
        }
        return new OptimizedList<>();
    }

    public Domain<T> getDomain() {
        return this.domain;
    }

    public String getName() {
        return name;
    }

    public int getVarSolverID() {
        return varSolverID;
    }

    public void setVarSolverID(int varSolverID) {
        this.varSolverID = varSolverID;
    }
}
