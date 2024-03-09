package fr.unk.variable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Variable<T> extends VarGetter<T>{
    private final String varName;
    private T calculatedValue = null;
    protected final List<Variable<T>> depend = new ArrayList<>();

    public Variable(String varName){
        super(null);
        this.varName = varName;
    }

    public String getVarName(){
        return this.varName;
    }

    @Override
    public T getValue(Map<String, T> maps) {
        if(this.calculatedValue != null)
            return this.calculatedValue;
        if(this.varName == null)
            return null;
        this.calculatedValue = maps.get(this.varName);
        return this.calculatedValue;
    }

    public List<Variable<T>> getVariableList(){
        Variable<T> variable = this;
        return new ArrayList<>() {{
            add(variable);
        }};
    }

    public void invalidate(){
        this.depend.forEach(Variable::invalidate);
        this.calculatedValue = null;
    }

    public T getCalculatedValue(){
        return this.calculatedValue;
    }

    public void setCalculatedValue(T t){
        this.calculatedValue = t;
    }

    public void addDepend(Variable<T> variable){
        this.depend.add(variable);
    }

}
