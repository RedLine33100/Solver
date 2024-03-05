package fr.unk.variable.numvar;

import fr.unk.variable.VarGetter;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CSPIntTest {
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
        CSPInt cspInt = new CSPInt("myVar") {};
        cspInt = cspInt.add(new VarGetter(5));
        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("myVar",10);
        assertEquals(15, cspInt.getValue(objectMap));
    }

    @Test
    void testremove() {
        CSPInt cspInt = new CSPInt("myVar") {};
        cspInt = cspInt.remove(new VarGetter(5));
        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("myVar",20);
        assertEquals(15, cspInt.getValue(objectMap));
    }

    @Test
    void testdivide() {
        CSPInt cspInt = new CSPInt("myVar") {};
        cspInt = cspInt.divide(new VarGetter(5));
        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("myVar",20);
        assertEquals(4, cspInt.getValue(objectMap));
    }
    @Test
    void testmultiply() {
        CSPInt cspInt = new CSPInt("myVar") {};
        cspInt = cspInt.multiply(new VarGetter(5));
        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("myVar",20);
        assertEquals(100, cspInt.getValue(objectMap));
    }


    @Test
    void testmodulo() {
        CSPInt cspInt = new CSPInt("myVar") {};
        cspInt = cspInt.modulo(new VarGetter(5));
        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("myVar",24);
        assertEquals(4, cspInt.getValue(objectMap));
    }

    @Test
    void testgetValue() {
        CSPInt cspInt = new CSPInt("myVar") {};
        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("myVar",24);
        assertEquals(24, cspInt.getValue(objectMap));
    }
}