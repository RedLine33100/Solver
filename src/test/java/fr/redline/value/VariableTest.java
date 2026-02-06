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
        intVar.setValue(1);
        assertEquals(1, intVar.getValue());
        intVar.setValue(20);
        assertNotEquals(20, intVar.getValue());

        Calcul<Integer> var = new SolverInt("testVar", new FastIntDomain(0, 1));

        assertNull(var.getValue());
        var.setValue(0);

        var = var.add(10);

        assertNotEquals(0, var.getValue());
        assertEquals(10, var.getValue());

        var = var.divide(2);

        assertEquals(5, var.getValue());

        var = var.multiply(3);

        assertEquals(15, var.getValue());


    }
}