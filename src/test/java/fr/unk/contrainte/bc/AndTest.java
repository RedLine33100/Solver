package fr.unk.contrainte.bc;

import fr.unk.contrainte.Constraint;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AndTest {
    Map<String, Object> objectMap = new HashMap<>();
    Constraint trueContraint = new Constraint() {
        @Override
        public boolean satisfied(Map<String, Object> objectMap) {
            return true;
        }
    };
    Constraint falseContraint = new Constraint() {
        @Override
        public boolean satisfied(Map<String, Object> objectMap) {
            return false;
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
    void satisfied() {
        //test si And(true,true) = true:
        And testtrue = new And(trueContraint,trueContraint);
        assertTrue(testtrue.satisfied(objectMap));
        //test si And(true,false) = false:
        And testtruefalse = new And(trueContraint,falseContraint);
        assertFalse(testtruefalse.satisfied(objectMap));
        //test si And(false,false) = false:
        And testfalse = new And(falseContraint,falseContraint);
        assertFalse(testfalse.satisfied(objectMap));
    }
}