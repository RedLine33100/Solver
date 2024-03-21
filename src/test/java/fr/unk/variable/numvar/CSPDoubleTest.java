package fr.unk.variable.numvar;

import org.junit.jupiter.api.Test;
import fr.unk.variable.VarGetter;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class CSPDoubleTest {
    private Map<String,Object> objectMap = new HashMap<>();
    private CSPDouble cspDouble = new CSPDouble("myVar") {};
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        System.out.println ("Avant toutes les executions");
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        System.out.println ("Apres toutes les executions");
    }

    @BeforeEach
    void setUp() throws Exception {
        System.out.println ("avant une fonction sous test");
    }

    @AfterEach
    void tearDown() throws Exception {
        System.out.println ("apres une fonction sous test");
    }

    @Test
    void testFunctionalAdd() {
        cspDouble = cspDouble.add(new VarGetter(5.0));
        objectMap.put("myVar",10.0);
        assertEquals(15, cspDouble.getValue(objectMap));
    }

    @Test
    void testFunctionalRemove() {
        cspDouble = cspDouble.remove(new VarGetter(5.5));
        objectMap.put("myVar",10.5);
        assertEquals(5, cspDouble.getValue(objectMap));
    }

    @Test
    void testFunctionalDivide() {
        cspDouble = cspDouble.divide(new VarGetter(5.5));
        objectMap.put("myVar",11.0);
        assertEquals(2, cspDouble.getValue(objectMap));
    }

    @Test
    void testFunctionalMultiply() {
        cspDouble = cspDouble.multiply(new VarGetter(5.5));
        objectMap.put("myVar",2.0);
        assertEquals(11, cspDouble.getValue(objectMap));
    }

    @Test
    void testFunctionalModulo() {
        cspDouble = cspDouble.modulo(new VarGetter(5.0));
        objectMap.put("myVar",21.5);
        assertEquals(1.5, cspDouble.getValue(objectMap));
    }

    @Test
    void testFunctionalGetValue() {
        objectMap.put("myVar",2.0);
        assertEquals(2.0, cspDouble.getValue(objectMap));
    }
}
