package fr.redline;

import fr.redline.contrainte.ValueConstraint;
import fr.redline.domaine.number.IntDomain;
import fr.redline.value.Value;
import fr.redline.value.numvar.SolverInt;
import fr.redline.value.variable.Variable;
import org.junit.jupiter.api.Test;

class CSPSolverTest {

    @Test
    void trySolve() {

        CSPSolver<Integer> cspSolver = new CSPSolver<>();

        SolverInt plaqueUse = new SolverInt(new Value<>(30));
        SolverInt visUse = new SolverInt(new Value<>(60));

        SolverInt plaqueInput = new SolverInt(new Value<>(160));
        SolverInt visInput = new SolverInt(new Value<>(240));

        Variable<Integer> inputMultiplierVis = new Variable<>("inpMultVis", new IntDomain(1,1000,1));
        Variable<Integer> inputMultiplierScrew = new Variable<>("inpMultScrew", new IntDomain(1,1000,1));
        Variable<Integer> useMultiplier = new Variable<>("useMult", new IntDomain(1,1000,1));

        cspSolver.addConstraint(ValueConstraint.equals(plaqueUse.multiply(useMultiplier), plaqueInput.multiply(inputMultiplierScrew)));
        cspSolver.addConstraint(ValueConstraint.equals(visUse.multiply(useMultiplier), visInput.multiply(inputMultiplierVis)));

        if(!cspSolver.trySolve()){
            System.out.println("None");
            return;
        }

        for(Variable<Integer> entry : cspSolver.getUnknownVariables())
            System.out.println(entry.getVarName()+": "+entry.getValue());

    }
}