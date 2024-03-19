package fr.unk.variable;

import fr.unk.contrainte.Constraint;

import java.util.ArrayList;
import java.util.List;

public class Variable<T> extends Getter<T> {

    private final String varName;
    private T calculatedValue = null;
    protected final List<Variable<T>> depend = new ArrayList<>();
    protected final List<Constraint<T>> constraints = new ArrayList<>();

    public Variable(String varName) {
        super(null);
        this.varName = varName;
    }

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

    /**
     * Get all var which value influence its own value
     * @return list of var
     */
    public List<Variable<T>> getVariableList(){
        Variable<T> variable = this;
        return new ArrayList<>() {{
            add(variable);
        }};
    }

    /**
     * Invalidate the current value and repeat to all linked var
     * @return the current var
     */
    public Variable<T> invalidate(){
        if(this.calculatedValue == null)
            return this;
        this.depend.forEach(Variable::invalidate);
        this.calculatedValue = null;
        return this;
    }

    /**
     * Set the var value
     * @param t the new value
     */
    public void setValue(T t) {
        if(t == this.calculatedValue)
            return;
        this.invalidate();
        this.calculatedValue = t;
    }

    /**
     * Add var which depend on the current var
     * @param variable variable to add
     * @return the current var
     */
    public void addDepend(Variable<T> variable){
        this.depend.add(variable);
    }

    /**
     * List of all the constraint linked to this var
     * @return list of all the constraint
     */
    public List<Constraint<T>> getConstrainst(){
        return this.constraints;
    }

}
