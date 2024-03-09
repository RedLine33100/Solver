package fr.unk.contrainte.bc;

import fr.unk.contrainte.Constraint;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AndTest {
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

        And testtrue = new And(trueContraint,trueContraint);
        Map<String, Object> objectMap = new HashMap<>();
        assertTrue(testtrue.satisfied(objectMap));
        And testtruefalse = new And(trueContraint,falseContraint);
        assertFalse(testtruefalse.satisfied(objectMap));
        And testfalse = new And(falseContraint,falseContraint);
        assertFalse(testfalse.satisfied(objectMap));
    }
}