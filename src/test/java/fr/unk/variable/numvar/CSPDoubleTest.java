package fr.unk.variable.numvar;

import fr.unk.variable.VarGetter;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CSPDoubleTest {
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
    void testadd() {
        CSPDouble cspDouble = new CSPDouble("myVar") {};
        cspDouble = cspDouble.add(new VarGetter(5.0));
        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("myVar",10.0);
        assertEquals(15, cspDouble.getValue(objectMap));
    }

    @Test
    void testremove() {
        CSPDouble cspDouble = new CSPDouble("myVar") {};
        cspDouble = cspDouble.remove(new VarGetter(5.5));
        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("myVar",10.5);
        assertEquals(5, cspDouble.getValue(objectMap));
    }

    @Test
    void testdivide() {
        CSPDouble cspDouble = new CSPDouble("myVar") {};
        cspDouble = cspDouble.divide(new VarGetter(5.5));
        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("myVar",11.0);
        assertEquals(2, cspDouble.getValue(objectMap));
    }

    @Test
    void testmultiply() {
        CSPDouble cspDouble = new CSPDouble("myVar") {};
        cspDouble = cspDouble.multiply(new VarGetter(5.5));
        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("myVar",2.0);
        assertEquals(11, cspDouble.getValue(objectMap));
    }

    @Test
    void testmodulo() {
        CSPDouble cspDouble = new CSPDouble("myVar") {};
        cspDouble = cspDouble.modulo(new VarGetter(5.0));
        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("myVar",21.5);
        assertEquals(1.5, cspDouble.getValue(objectMap));
    }

    @Test
    void testgetValue() {
        CSPDouble cspDouble = new CSPDouble("myVar") {};
        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("myVar",2.0);
        assertEquals(2.0, cspDouble.getValue(objectMap));
    }
}