package fr.unk.contrainte.nc;

import fr.unk.variable.Variable;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListDiffTest {

    private final Variable<Integer> var1 = new Variable<>("var1");
    private final Variable<Integer> var2 = new Variable<>("var2");
    private final Variable<Integer> var3 = new Variable<>("var3");

    @BeforeAll
    static void setUpBeforeClass() {
        System.out.println ("Avant toutes les executions");
    }

    @AfterAll
    static void tearDownAfterClass() {
        System.out.println ("Apres toutes les executions");
    }

    @BeforeEach
    void setUp() {
        System.out.println ("avant une fonction sous test");
    }

    @AfterEach
    void tearDown() {
        System.out.println ("apres une fonction sous test");
    }

    @Test
    void testSatisfied() {
        // Cas standard : vérifie si la méthode retourne true lorsque toutes les valeurs sont différentes
        ListDiff<Integer> constraint = new ListDiff<>(new ArrayList<>() {{
            add(var1);
            add(var2);
            add(var3);
        }});
        var1.setValue(1);
        var2.setValue(2);
        var3.setValue(3);
        assertTrue(constraint.satisfied());

        // Cas où la liste de variables est vide : vérifie si la méthode retourne true dans ce cas
        ListDiff<Integer> constraintEmpty = new ListDiff<>(Collections.emptyList ());
        assertTrue(constraintEmpty.satisfied());

        // Cas où la liste de variables contient un seul élément : vérifie si la méthode retourne true dans ce cas
        ListDiff<Integer> constraintSingleVar = new ListDiff<>(new ArrayList<>() {{
            add(var1);
        }});
        assertTrue(constraintSingleVar.satisfied());

        // Cas où les valeurs associées aux variables sont null : vérifie si la méthode retourne false dans ce cas
        Variable<Integer> varNull = new Variable<>("varNull");
        ListDiff<Integer> constraintWithNull = new ListDiff<>(new ArrayList<>() {{
            add(var1);
            add(varNull);
        }});
        assertFalse(constraintWithNull.satisfied());
    }

}