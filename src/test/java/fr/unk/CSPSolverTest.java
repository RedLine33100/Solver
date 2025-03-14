package fr.unk;

import fr.unk.contrainte.vc.Equals;
import fr.unk.domaine.number.IntDomain;
import fr.unk.variable.Variable;
import fr.unk.variable.numvar.CalculInt;
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
        Variable<Integer> calculInt = new Variable<>("fe", new IntDomain(0,3,1));
        Variable<Integer> calculInt2 = new Variable<>("de", new IntDomain(0,3,1));

        cspSolver.addConstraint(new Equals<>(calculInt, 3));
        cspSolver.addConstraint(new Equals<>(new CalculInt(calculInt2).add(2), calculInt));

        cspSolver.addUnknownVariable(calculInt);
        cspSolver.addUnknownVariable(calculInt2);
        Map<String, Integer> result = cspSolver.trySolve();

        if(result == null){
            System.out.println("None");
            return;
        }

        for(Map.Entry<String, Integer> entry : result.entrySet())
            System.out.println(entry.getKey()+": "+entry.getValue());

    }
}