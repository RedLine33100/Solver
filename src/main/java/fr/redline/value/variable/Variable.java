package fr.redline.value.variable;

import fr.redline.domaine.Domain;
import fr.redline.value.ValueGetter;

import java.util.Map;

public record Variable<T>(String varName, Domain<T> domain) implements ValueGetter<T> {

    @Override
    public T getValue(Map<String, T> maps) {
        if (this.varName == null)
            return null;
        return maps.get(varName);
    }

    @Override
    public VarType getType() {
        return VarType.UNKNOWN;
    }
}
