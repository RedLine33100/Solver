package fr.unk.variable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VariableTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @Test
    public void testFunctionalVariableConstruction() {
        Variable<Integer> variable = new Variable<>("myVar");
        assertEquals("myVar", variable.getVarName());
    }

    @Test
    void getStructuralValue() {
        /*
        Variable<Integer> intVar = new Variable<>("testVar1");

        assertNull(intVar.getValue(new HashMap<>()));
        assertEquals(10, intVar.getValue(new HashMap<>(){{put("testVar1", 10);}}));
        assertNotEquals(10, intVar.getValue(new HashMap<>(){{put("testVar1", 20);}}));


        Calcul<Integer> intCalc = new CSPInt("testVar");

        assertNull(intCalc.getValue(new HashMap<>()));
        assertEquals(20, intCalc.getValue(new HashMap<>(){{put("testVar", 20);}}));

        intCalc = intCalc.add(5);

        assertNotEquals(15, intCalc.getValue(new HashMap<>()));
        assertEquals(25, intCalc.getValue(new HashMap<>(){{put("testVar", 20);}}));

        intCalc = intCalc.divide(2);

        assertEquals(12, intCalc.getValue(new HashMap<>(){{put("testVar", 20);}}));

        intCalc = intCalc.multiply(3);

        assertEquals(36, intCalc.getValue(new HashMap<>(){{put("testVar", 20);}}));
        */
    }
}
