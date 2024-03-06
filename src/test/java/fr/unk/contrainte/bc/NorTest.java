package fr.unk.contrainte.bc;

import org.junit.jupiter.api.Test;
import fr.unk.contrainte.Constraint;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class NorTest {
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
        //test si Nor(true,true) = false:
        Nor testtrue = new Nor(trueContraint,trueContraint);
        assertFalse(testtrue.satisfied(objectMap));
        //test si Nor(false,false) = true:
        Nor testfalse= new Nor(falseContraint,falseContraint);
        assertFalse(testfalse.satisfied(objectMap));
        //test si Nor(false,true) = true:
        Nor testfalsetrue= new Nor(falseContraint,trueContraint);
        assertTrue(testfalsetrue.satisfied(objectMap));
        //test si Nor(true,false) = true:
        Nor testtruefalse= new Nor(trueContraint,falseContraint);
        assertTrue(testtruefalse.satisfied(objectMap));
    }
}