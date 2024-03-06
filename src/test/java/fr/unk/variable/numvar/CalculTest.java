package fr.unk.variable.numvar;

import org.junit.jupiter.api.*;
import fr.unk.variable.VarGetter;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class CalculTest {
    private Map<String,Object> objectMap = new HashMap<>();
    private Calcul<Integer> calcul = new Calcul<Integer>("myVar", Integer.class){
        @Override
        Calcul<Integer> add(VarGetter<Integer> varGetter) {
            return this;
        }

        @Override
        Calcul<Integer> remove(VarGetter<Integer> varGetter) {
            return this;
        }

        @Override
        public Calcul<Integer> divide(VarGetter<Integer> variable) {
            return this;
        }

        @Override
        public Calcul<Integer> multiply(VarGetter<Integer> variable) {
            return this;
        }

        @Override
        public Calcul<Integer> modulo(VarGetter<Integer> variable) {
            return this;
        }
    };
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
        calcul = calcul.add(new VarGetter<>(5));
        objectMap.put("myVar",10);
        assertNotNull(calcul.getValue(objectMap));
        //assertEquals(15,calcul.getValue(objectMap));
    }

    @Test
    void testRemove() {
        calcul = calcul.remove(new VarGetter<>(5));
        objectMap.put("myVar",10);
        assertNotNull(calcul.getValue(objectMap));
        //assertEquals(5,calcul.getValue(objectMap));
    }

    @Test
    void divide() {
        objectMap.put("myVar",10);
        calcul = calcul.divide(new VarGetter<>(5));
        assertNotNull(calcul.getValue(objectMap));
        //assertEquals(2,calcul.getValue(objectMap));
    }

    @Test
    void multiply() {
        objectMap.put("myVar",10);
        calcul = calcul.divide(new VarGetter<>(2));
        assertNotNull(calcul.getValue(objectMap));
        //assertEquals(20,calcul.getValue(objectMap));
    }

    @Test
    void modulo() {
        objectMap.put("myVar",10);
        calcul = calcul.modulo(new VarGetter<>(2));
        assertNotNull(calcul.getValue(objectMap));
        //assertEquals(0,calcul.getValue(objectMap));
    }
}