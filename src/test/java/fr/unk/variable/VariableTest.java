package fr.unk.variable;

import fr.unk.variable.numvar.CSPInt;
import fr.unk.variable.numvar.Calcul;

import java.util.HashMap;
import java.util.function.BinaryOperator;

import static org.junit.jupiter.api.Assertions.*;

class VariableTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void getValue() {
        Variable<Integer> intVar = new Var<>("testVar1", Integer.class);

        assertNull(intVar.getValue(new HashMap<>()));
        assertEquals(10, intVar.getValue(new HashMap<>(){{put("testVar1", 10);}}));
        assertNotEquals(10, intVar.getValue(new HashMap<>(){{put("testVar1", 20);}}));


        Variable<Integer> intCst = new Var<>(15);

        assertEquals(15, intCst.getValue(new HashMap<>()));
        assertNotEquals(20, intCst.getValue(new HashMap<>(){{put("testVar1", 20);}}));


        CSPInt intCalc = new CSPInt(15);

        assertEquals(15, intCalc.getValue(new HashMap<>()));
        assertNotEquals(20, intCalc.getValue(new HashMap<>(){{put("test2", 20);}}));

        intCalc.add(new CSPInt("test1"));

    }
}