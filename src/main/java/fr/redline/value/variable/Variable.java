package fr.redline.value.variable;

import fr.redline.domaine.Domain;
import fr.redline.value.Value;

import java.util.LinkedHashSet;

public class Variable<T> extends Value<T> {

    String varName;
    Domain<T> domain;

    public Variable(String varName, Domain<T> domain) {
        this.varName = varName;
        this.domain = domain;
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

    @Override
    public LinkedHashSet<Variable<T>> getUnknownVariables() {
        LinkedHashSet<Variable<T>> vars = new LinkedHashSet<>();
        vars.add(this);
        return vars;
    }
}
