package fr.unk.contrainte.nc;

import fr.unk.variable.Getter;
import fr.unk.variable.Variable;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListEqualsTest {

    private static final Variable<Integer> singleVariable = new Variable<>("x");
    private static final Variable<Integer> var1 = new Variable<>("x");
    private static final Variable<Integer> var2 = new Variable<>("y");
    private static final Variable<Integer> diffVar1 = new Variable<>("x");
    private static final Variable<Integer> diffVar2 = new Variable<>("y");

    private static ListEquals<Integer> emptyListEquals;
    private static ListEquals<Integer> singleListEquals;
    private static  ListEquals<Integer> equalListEquals;
    private static ListEquals<Integer> diffListEquals;

    @BeforeAll
    static void setUpBeforeClass() {
        System.out.println ("Avant toutes les executions");
        List<Getter<Integer>> emptyList = new ArrayList<>();
        emptyListEquals = new ListEquals<>(emptyList);
        List<Getter<Integer>> singleList = List.of(singleVariable);
        singleListEquals = new ListEquals<>(singleList);
        List<Getter<Integer>> equalList = List.of(var1, var2);
        equalListEquals = new ListEquals<>(equalList);
        List<Getter<Integer>> diffList = List.of(diffVar1, diffVar2);
        diffListEquals = new ListEquals<>(diffList);
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
    void satisfied() {
        // Cas où la liste de variables est vide : vérifie si la méthode satisfied() renvoie true dans ce cas
        assertTrue(emptyListEquals.satisfied());

        // Cas où la liste de variables contient une seule variable : vérifie si la méthode satisfied() renvoie true dans ce cas
        singleVariable.setValue(5);
        assertTrue(singleListEquals.satisfied());

        // Cas où la liste de variables contient plusieurs variables avec des valeurs égales : vérifie si la méthode satisfied() renvoie true dans ce cas
        var1.setValue(5);
        var2.setValue(5);
        assertTrue(equalListEquals.satisfied());

        // Cas où la liste de variables contient plusieurs variables avec des valeurs différentes : vérifie si la méthode satisfied() renvoie false dans ce cas
        diffVar1.setValue(5);
        diffVar2.setValue(10);
        assertFalse(diffListEquals.satisfied());
    }

}