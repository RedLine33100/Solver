package fr.redline.value.variable;

import fr.redline.domaine.Domain;
import fr.redline.value.ValueGetter;

public class Variable<T> implements ValueGetter<T> {

    String varName;
    Domain<T> domain;
    T value = null;

    public Variable(String varName, Domain<T> domain) {
        this.varName = varName;
        this.domain = domain;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public T getValue() {
        return value;
    }

    public Domain<T> getDomain() {
        return domain;
    }

    public String getVarName() {
        return varName;
    }

    @Override
    public VarType getType() {
        return VarType.UNKNOWN;
    }
}
