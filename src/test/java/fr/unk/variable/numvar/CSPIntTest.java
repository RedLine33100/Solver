package fr.unk.variable.numvar;

import org.junit.jupiter.api.Test;
import fr.unk.variable.VarGetter;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class CSPIntTest {
    private Map<String,Object> objectMap = new HashMap<>();
    private CSPInt cspInt = new CSPInt("myVar") {};
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
    void testAdd() {
        cspInt = cspInt.add(new VarGetter(5));
        objectMap.put("myVar",10);
        assertEquals(15, cspInt.getValue(objectMap));
    }

    @Test
    void testRemove() {
        cspInt = cspInt.remove(new VarGetter(5));
        objectMap.put("myVar",20);
        assertEquals(15, cspInt.getValue(objectMap));
    }

    @Test
    void testDivide() {
        cspInt = cspInt.divide(new VarGetter(5));
        objectMap.put("myVar",20);
        assertEquals(4, cspInt.getValue(objectMap));
    }

    @Test
    void testMultiply() {
        cspInt = cspInt.multiply(new VarGetter(5));
        objectMap.put("myVar",20);
        assertEquals(100, cspInt.getValue(objectMap));
    }

    @Test
    void testModulo() {
        cspInt = cspInt.modulo(new VarGetter(5));
        objectMap.put("myVar",24);
        assertEquals(4, cspInt.getValue(objectMap));
    }

    @Test
    void testGetValue() {
        objectMap.put("myVar",24);
        assertEquals(24, cspInt.getValue(objectMap));
    }
}