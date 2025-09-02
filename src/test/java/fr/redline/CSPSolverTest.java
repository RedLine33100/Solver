package fr.redline;

import fr.redline.contrainte.ValueConstraint;
import fr.redline.domaine.number.IntDomain;
import fr.redline.value.Value;
import fr.redline.value.numvar.CalculInt;
import fr.redline.value.variable.Variable;
import org.junit.jupiter.api.Test;

class CSPSolverTest {

    @Test
    void trySolve() {

        CSPSolver<Integer> cspSolver = new CSPSolver<>();
        Variable<Integer> calculInt = new Variable<>("fe", new IntDomain(0,3,1));
        Variable<Integer> calculInt2 = new Variable<>("de", new IntDomain(0,3,1));

        cspSolver.addConstraint(ValueConstraint.equals(calculInt, new Value<>(3)));
        cspSolver.addConstraint(ValueConstraint.equals(new CalculInt(calculInt2).add(2), calculInt));

        if(!cspSolver.trySolve()){
            System.out.println("None");
            return;
        }

        for(Variable<Integer> entry : cspSolver.getUnknownVariables())
            System.out.println(entry.getVarName()+": "+entry.getValue());

    }
}