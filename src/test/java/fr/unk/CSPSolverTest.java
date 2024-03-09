package fr.unk;

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

        CSPFloat cspInt = new CSPFloat("fe");
        cspInt.setCalculatedValue(8f);
        CSPFloat cspInt2 = new CSPFloat("de");

        System.out.println(cspInt.add(cspInt2).getRevert(10f));

    }
}