package fr.unk.contrainte.bc;

import fr.unk.contrainte.Constraint;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OrTest {
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

        Or testtrue = new Or(trueContraint,trueContraint);
        Map<String, Object> objectMap = new HashMap<>();
        assertTrue(testtrue.satisfied(objectMap));
        Or testtruefalse = new Or(trueContraint,falseContraint);
        assertTrue(testtruefalse.satisfied(objectMap));
        Or testfalse = new Or(falseContraint,falseContraint);
        assertFalse(testfalse.satisfied(objectMap));
    }

}