package fr.redline.value;

import fr.redline.domaine.number.FastIntDomain;
import fr.redline.value.numvar.Calcul;
import fr.redline.value.numvar.SolverInt;

import static org.junit.jupiter.api.Assertions.*;

class VariableTest {

    @org.junit.jupiter.api.Test
    void getValue() {
        Variable<Integer> intVar = new Variable<>("testVar1", new FastIntDomain(0, 1));

        assertNull(intVar.getValue());
        intVar.setValue(10);
        assertEquals(10, intVar.getValue());
        intVar.setValue(20);
        assertNotEquals(10, intVar.getValue());

        Calcul<Integer> var = new SolverInt("testVar", new FastIntDomain(0, 1));

        assertNull(var.getValue());
        var.setValue(20);
        assertEquals(20, var.getValue());

        var = var.add(5);

        assertNotEquals(15, var.getValue());
        assertEquals(25, var.getValue());

        var = var.divide(2);

        assertEquals(12, var.getValue());

        var = var.multiply(3);

        assertEquals(36, var.getValue());


    }
}