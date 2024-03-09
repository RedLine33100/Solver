package fr.unk.variable;

import java.util.ArrayList;
import java.util.List;

public class Variable<T> extends VarGetter<T> {

    private final String varName;
    private T calculatedValue = null;
    protected final List<Variable<T>> depend = new ArrayList<>();

    public Variable(String varName) {
        super(null);
        this.varName = varName;
    }

    @Override
    public String getVarName(){
        return this.varName;
    }

    @Override
    public T getValue() {
        return this.calculatedValue;
    }

    @Override
    public boolean isVar() {
        return true;
    }

    public List<Variable<T>> getVariableList(){
        Variable<T> variable = this;
        return new ArrayList<>() {{
            add(variable);
        }};
    }

    public void invalidate(){
        if(this.calculatedValue == null)
            return;
        this.depend.forEach(Variable::invalidate);
        this.calculatedValue = null;
    }

    public Variable<T> setValue(T t) {
        if(t == this.calculatedValue)
            return this;
        this.invalidate();
        this.calculatedValue = t;
        return this;
    }

    public Variable<T> addDepend(Variable<T> variable){
        this.depend.add(variable);
        return this;
    }

}
