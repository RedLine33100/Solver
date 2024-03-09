package fr.unk.contrainte.bc;

import fr.unk.contrainte.Constraint;
import fr.unk.domaine.DomainMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NorTest {

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
        Nor<Object> testtrue = new Nor<>(trueContraint,trueContraint);
        assertFalse(testtrue.satisfied());
        Nor<Object> testfalse= new Nor<>(falseContraint,falseContraint);
        assertFalse(testfalse.satisfied());
        Nor<Object> testfalsetrue= new Nor<>(falseContraint,trueContraint);
        assertTrue(testfalsetrue.satisfied());
        Nor<Object> testtruefalse= new Nor<>(trueContraint,falseContraint);
        assertTrue(testtruefalse.satisfied());
    }
}