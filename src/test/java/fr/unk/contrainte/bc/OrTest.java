package fr.unk.contrainte.bc;

import fr.unk.contrainte.Constraint;
import fr.unk.domaine.DomainMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrTest {
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

        Or<Object> testtrue = new Or<>(trueContraint,trueContraint);
        assertTrue(testtrue.satisfied());
        Or<Object> testtruefalse = new Or<>(trueContraint,falseContraint);
        assertTrue(testtruefalse.satisfied());
        Or<Object> testfalse = new Or<>(falseContraint,falseContraint);
        assertFalse(testfalse.satisfied());
    }

}