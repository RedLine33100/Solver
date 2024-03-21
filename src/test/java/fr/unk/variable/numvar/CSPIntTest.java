package fr.unk.variable.numvar;

import fr.unk.variable.Getter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void testFunctionalAdd() {
        CSPInt cspInt = new CSPInt("myVar") {
        };
        cspInt.setValue(10);
        cspInt = cspInt.add(new Getter<>(5));
        assertEquals(15, cspInt.getValue());
    }

    @Test
    void testFunctionalRemove() {
        CSPInt cspInt = new CSPInt("myVar") {
        };
        cspInt.setValue(20);
        cspInt = cspInt.remove(new Getter<>(5));
        assertEquals(15, cspInt.getValue());
    }

    @Test
    void testFunctionalDivide() {
        CSPInt cspInt = new CSPInt("myVar") {
        };
        cspInt.setValue(20);
        cspInt = cspInt.divide(new Getter<>(5));
        assertEquals(4, cspInt.getValue());
    }

    @Test
    void testFunctionalMultiply() {
        CSPInt cspInt = new CSPInt("myVar") {
        };
        cspInt.setValue(20);
        cspInt = cspInt.multiply(new Getter<>(5));
        assertEquals(100, cspInt.getValue());
    }


    @Test
    void testFunctionalModulo() {
        CSPInt cspInt = new CSPInt("myVar") {
        };
        cspInt.setValue(24);
        cspInt = cspInt.modulo(new Getter<>(5));
        assertEquals(4, cspInt.getValue());
    }

    @Test
    void testFunctionalGetValue() {
        CSPInt cspInt = new CSPInt("myVar") {
        };
        cspInt.setValue(24);
        assertEquals(24, cspInt.getValue());
    }
}
