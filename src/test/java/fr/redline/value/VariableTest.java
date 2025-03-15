package fr.redline.value;

import fr.redline.domaine.number.IntDomain;
import fr.redline.value.numvar.CalculInt;
import fr.redline.value.numvar.Calcul;
import fr.redline.value.variable.Variable;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class VariableTest {

    @org.junit.jupiter.api.Test
    void getValue() {
        Variable<Integer> intVar = new Variable<>("testVar1", new IntDomain(0,1,1));

        assertNull(intVar.getValue(new HashMap<>()));
        assertEquals(10, intVar.getValue(new HashMap<>(){{put("testVar1", 10);}}));
        assertNotEquals(10, intVar.getValue(new HashMap<>(){{put("testVar1", 20);}}));

        Variable<Integer> var = new Variable<>("testVar", new IntDomain(0,1,1));
        Calcul<Integer> intCalc = new CalculInt(var);

        assertNull(intCalc.getValue(new HashMap<>()));
        assertEquals(20, intCalc.getValue(new HashMap<>(){{put("testVar", 20);}}));

        intCalc = intCalc.add(5);

        assertNotEquals(15, intCalc.getValue(new HashMap<>()));
        assertEquals(25, intCalc.getValue(new HashMap<>(){{put("testVar", 20);}}));

        intCalc = intCalc.divide(2);

        assertEquals(12, intCalc.getValue(new HashMap<>(){{put("testVar", 20);}}));

        intCalc = intCalc.multiply(3);

        assertEquals(36, intCalc.getValue(new HashMap<>(){{put("testVar", 20);}}));

    }
}