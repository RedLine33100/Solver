package fr.unk;

import fr.unk.variable.numvar.CSPFloat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

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
        CSPFloat cspInt2 = new CSPFloat("de");

        System.out.println(cspInt.add(cspInt2).getRevert(new HashMap<>(){{put("fe", 8f);}}, 10f));

    }
}