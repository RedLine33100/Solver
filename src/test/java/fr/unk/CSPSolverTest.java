package fr.unk;

import fr.unk.contrainte.vc.Equals;
import fr.unk.domaine.number.FloatDomain;
import fr.unk.variable.numvar.CSPFloat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CSPSolverTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void trySolve() {

        CSPSolver<Float> cspSolver = new CSPSolver<>();

        CSPFloat cspInt = new CSPFloat("fe");
        cspInt.setCalculatedValue(8f);
        CSPFloat cspInt2 = new CSPFloat("de");

        cspSolver.addUnknownVariable(cspInt2, new FloatDomain(10f,15f, 1f));
        cspSolver.addConstraint(new Equals<>(cspInt, cspInt2));

        if(cspSolver.trySolve()) {
            cspSolver.getDomainMap().getDomainMap().forEach((variable, domain) -> System.out.println(variable.getVarName()+": "+variable.getValue()));
        }else {
            System.out.println("NO SOLUTION");
        }

    }
}