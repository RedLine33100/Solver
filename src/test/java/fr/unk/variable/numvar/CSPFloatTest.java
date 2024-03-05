package fr.unk.variable.numvar;

import fr.unk.variable.VarGetter;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CSPFloatTest {
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
        CSPFloat cspFloat = new CSPFloat("myVar") {};
        cspFloat = cspFloat.add(new VarGetter(5.2f));
        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("myVar",10.5f);
        assertEquals(15.7f, cspFloat.getValue(objectMap));
    }

    @Test
    void testremove() {
        CSPFloat cspFloat = new CSPFloat("myVar") {};
        cspFloat = cspFloat.remove(new VarGetter(5.2f));
        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("myVar",20.5f);
        assertEquals(15.3f, cspFloat.getValue(objectMap));
    }

    @Test
    void testdivide() {
        CSPFloat cspFloat = new CSPFloat("myVar") {};
        cspFloat = cspFloat.divide(new VarGetter(5.1f));
        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("myVar",20.4f);
        assertEquals(4.0f, cspFloat.getValue(objectMap));
    }

    @Test
    void testmultiply() {
        CSPFloat cspFloat = new CSPFloat("myVar") {};
        cspFloat = cspFloat.multiply(new VarGetter(5.0f));
        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("myVar",20.4f);
        assertEquals(102.0f, cspFloat.getValue(objectMap));
    }

    @Test
    void testmodulo() {
        CSPFloat cspFloat = new CSPFloat("myVar") {};
        cspFloat = cspFloat.modulo(new VarGetter(5.0f));
        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("myVar",21.0f);
        //quand il y a des chiffres aprés la virgule cela ne mais pas les valeurs exacte exemple si le resultat attendu est 0.4 cela retourne 0.38668
        assertEquals(1.0f, cspFloat.getValue(objectMap));
    }

    @Test
    void testgetValue() {
        CSPFloat cspFloat = new CSPFloat("myVar") {};
        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("myVar",21.586f);
        assertEquals(21.586f, cspFloat.getValue(objectMap));
    }
}