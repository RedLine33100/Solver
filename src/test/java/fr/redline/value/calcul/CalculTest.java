package fr.redline.value.calcul;

import fr.redline.domaine.number.IntDomain;
import fr.redline.value.Variable;
import fr.redline.value.numvar.Calcul;
import fr.redline.value.numvar.SolverInt;

import static org.junit.jupiter.api.Assertions.*;

class CalculTest {

    @org.junit.jupiter.api.Test
    void getValue() {
        Variable<Integer> intVar = new Variable<>("testVar1", new IntDomain(0, 1, 1));

        assertNull(intVar.getValue());
        intVar.setValue(10);
        assertEquals(10, intVar.getValue());
        intVar.setValue(20);
        assertNotEquals(10, intVar.getValue());

        Variable<Integer> var = new Variable<>("testVar", new IntDomain(0, 1, 1));
        Calcul<Integer> intCalc = new SolverInt(var);

        assertNull(intCalc.getValue());
        var.setValue(20);
        assertEquals(20, intCalc.getValue());

        intCalc = intCalc.add(5);

        assertNotEquals(15, intCalc.getValue());
        assertEquals(25, intCalc.getValue());

        intCalc = intCalc.divide(2);

        assertEquals(12, intCalc.getValue());

        intCalc = intCalc.multiply(3);

        assertEquals(36, intCalc.getValue());


    }
}