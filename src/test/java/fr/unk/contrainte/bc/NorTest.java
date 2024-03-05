package fr.unk.contrainte.bc;

import fr.unk.contrainte.Constraint;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class NorTest {

    @Test
    void testsatisfied() {
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
        Map<String, Object> objectMap = new HashMap<>();
        Nor testtrue = new Nor(trueContraint,trueContraint);
        assertFalse(testtrue.satisfied(objectMap));
        Nor testfalse= new Nor(falseContraint,falseContraint);
        assertFalse(testfalse.satisfied(objectMap));
        Nor testfalsetrue= new Nor(falseContraint,trueContraint);
        assertTrue(testfalsetrue.satisfied(objectMap));
        Nor testtruefalse= new Nor(trueContraint,falseContraint);
        assertTrue(testtruefalse.satisfied(objectMap));
    }
}