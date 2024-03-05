package fr.unk.contrainte.nc;

import fr.unk.variable.VarGetter;
import fr.unk.variable.Variable;
import fr.unk.variable.numvar.CSPInt;
import org.junit.jupiter.api.*;
import org.junit.jupiter.engine.Constants;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ListDiffTest {

    private Variable<Integer> var1 = new Variable<> ("var1", Integer.class);
    private Variable<Integer> var2 = new Variable<> ("var2", Integer.class);
    private Variable<Integer> var3 = new Variable<> ("var3", Integer.class);

    private static Map<String, Object> map;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        System.out.println ("Avant toutes les executions");
        map = new HashMap<> ();
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
    void testSatisfied() {
        // Cas standard : vérifie si la méthode retourne true lorsque toutes les valeurs sont différentes
        ListDiff<Integer> constraint = new ListDiff<>(var1, var2, var3);
        map.put ("var1", 1);
        map.put ("var2", 2);
        map.put ("var3", 3);
        assertTrue(constraint.satisfied(map));

        // Cas où la liste de variables est vide : vérifie si la méthode retourne true dans ce cas
        ListDiff<Integer> constraintEmpty = new ListDiff<>(Collections.emptyList ());
        assertTrue(constraintEmpty.satisfied(map));

        // Cas où la liste de variables contient un seul élément : vérifie si la méthode retourne true dans ce cas
        ListDiff<Integer> constraintSingleVar = new ListDiff<> (var1);
        assertTrue(constraintSingleVar.satisfied(map));

        // Cas où les valeurs associées aux variables sont null : vérifie si la méthode retourne false dans ce cas
        Variable<Integer> varNull = new Variable<>("varNull", null);
        ListDiff<Integer> constraintWithNull = new ListDiff<>(var1, varNull);
        assertFalse(constraintWithNull.satisfied(map));
    }

}