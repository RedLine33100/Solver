package fr.unk;

import fr.unk.contrainte.vc.Equals;
import fr.unk.domaine.number.IntDomain;
import fr.unk.variable.numvar.CSPInt;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

class CSPSolverTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void trySolve() {

        CSPSolver<Integer> cspSolver = new CSPSolver<>();
        CSPInt cspInt = new CSPInt("fe");
        CSPInt cspInt2 = new CSPInt("de");

        cspSolver.addConstraint(new Equals<>(cspInt, 3));
        cspSolver.addConstraint(new Equals<>(cspInt2.add(2), cspInt));

        cspSolver.addUnknownVariable(cspInt, new IntDomain(0,3,1));
        cspSolver.addUnknownVariable(cspInt2, new IntDomain(0,3,1));
        Map<String, Object> result = cspSolver.trySolve();

        if(result == null){
            System.out.println("None");
            return;
        }

        for(Map.Entry<String, Object> entry : result.entrySet())
            System.out.println(entry.getKey()+": "+entry.getValue());

    }
}