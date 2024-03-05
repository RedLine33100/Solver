package fr.unk.contrainte.nc;

import fr.unk.variable.Variable;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ListEqualsTest {

    private static Variable<Integer> singleVariable = new Variable<>("x", Integer.class);
    private static Variable<Integer> var1 = new Variable<>("x", Integer.class);
    private static Variable<Integer> var2 = new Variable<>("y", Integer.class);
    private static Variable<Integer> diffVar1 = new Variable<>("x", Integer.class);
    private static Variable<Integer> diffVar2 = new Variable<>("y", Integer.class);

    private static List<Variable<Integer>> emptyList;
    private static ListEquals<Integer> emptyListEquals;
    private static List<Variable<Integer>> singleList;
    private static ListEquals<Integer> singleListEquals;
    private static List<Variable<Integer>> equalList;
    private static  ListEquals<Integer> equalListEquals;
    private static List<Variable<Integer>> diffList;
    private static ListEquals<Integer> diffListEquals;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        System.out.println ("Avant toutes les executions");
        emptyList = new ArrayList<>();
        emptyListEquals = new ListEquals<>(emptyList);
        singleList = List.of(singleVariable);
        singleListEquals = new ListEquals<>(singleList);
        equalList = List.of(var1, var2);
        equalListEquals = new ListEquals<>(equalList);
        diffList = List.of(diffVar1, diffVar2);
        diffListEquals = new ListEquals<>(diffList);
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
        // Cas où la liste de variables est vide : vérifie si la méthode satisfied() renvoie true dans ce cas
        assertTrue(emptyListEquals.satisfied(new HashMap<>()));

        // Cas où la liste de variables contient une seule variable : vérifie si la méthode satisfied() renvoie true dans ce cas
        assertTrue(singleListEquals.satisfied(Map.of("x", 5)));

        // Cas où la liste de variables contient plusieurs variables avec des valeurs égales : vérifie si la méthode satisfied() renvoie true dans ce cas
        assertTrue(equalListEquals.satisfied(Map.of("x", 5, "y", 5)));

        // Cas où la liste de variables contient plusieurs variables avec des valeurs différentes : vérifie si la méthode satisfied() renvoie false dans ce cas
        assertFalse(diffListEquals.satisfied(Map.of("x", 5, "y", 10)));
    }

}