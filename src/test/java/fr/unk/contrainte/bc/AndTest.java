package fr.unk.contrainte.bc;

import fr.unk.contrainte.Constraint;
import fr.unk.domaine.DomainMap;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AndTest {
    @Test
    void testsatisfied() {
        Constraint<Object> trueContraint = new Constraint<>() {
            @Override
            public boolean satisfied() {
                return true;
            }

            @Override
            public void reduceDomain(DomainMap<Object> domainMap) {

            }
        };
        Constraint<Object> falseContraint = new Constraint<>() {
            @Override
            public boolean satisfied() {
                return false;
            }

            @Override
            public void reduceDomain(DomainMap<Object> domainMap) {

            }
        };

        And<Object> testtrue = new And<>(trueContraint,trueContraint);
        assertTrue(testtrue.satisfied());
        And<Object> testtruefalse = new And<>(trueContraint,falseContraint);
        assertFalse(testtruefalse.satisfied());
        And<Object> testfalse = new And<>(falseContraint,falseContraint);
        assertFalse(testfalse.satisfied());
    }
}