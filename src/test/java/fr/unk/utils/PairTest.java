package fr.unk.utils;

import org.junit.jupiter.api.*;

import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class PairTest <L,R>{
    private static Random random = new Random();
    private static int leftInt ;
    private static int rightInt ;
    private static double leftDouble ;
    private static double rightDouble ;
    private static float leftFloat ;
    private static float rightFloat ;
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        leftInt = random.nextInt();
        rightInt = random.nextInt();
        leftDouble = random.nextDouble();
        rightDouble = random.nextDouble();
        leftFloat = random.nextFloat();
        rightFloat = random.nextFloat();
        System.out.println ("Avant toutes les executions");
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
    void testgetL() {
        //objectif testé si on arrive bien à récupérer les bonnes valeurs des 3 domaines est que les valeurs L et R n'ont pas besoin d'être dans le même domaine
        Pair<L, R> double_pair = (Pair<L, R>) new Pair<>(leftDouble, rightFloat);
        L doubleactuel = double_pair.getL();
        //test si la paire n'est pas null, puis test si la valeur récupérer est bien la valeur attendue (un double)
        assertNotNull(doubleactuel);
        assertEquals(doubleactuel,leftDouble);
        Pair<L, R> float_pair = (Pair<L, R>) new Pair<>(leftFloat, rightInt);
        L floatactuel = float_pair.getL();
        //Même test pour un float
        assertNotNull(floatactuel);
        assertEquals(floatactuel,leftFloat);
        Pair<L, R> int_pair = (Pair<L, R>) new Pair<>(leftInt, rightDouble);
        L intactuel = int_pair.getL();
        //Même test pour un int
        assertNotNull(intactuel);
        assertEquals(intactuel,leftInt);
    }

    @Test
    void testgetR() {
        //pareil que test Left mais pour la paire Right
        //test pour les float
        Pair<L, R> float_pair = (Pair<L, R>) new Pair<>(leftDouble, rightFloat);
        R floatactuel = float_pair.getR();
        assertNotNull(floatactuel);
        assertEquals(floatactuel,rightFloat);
        //test pour les int
        Pair<L, R> int_pair = (Pair<L, R>) new Pair<>(leftFloat, rightInt);
        R intactuel = int_pair.getR();
        assertNotNull(intactuel);
        assertEquals(intactuel,rightInt);
        //test pour les double
        Pair<L, R> double_pair = (Pair<L, R>) new Pair<>(leftInt, rightDouble);
        R doubleactuel = double_pair.getR();
        assertNotNull(doubleactuel);
        assertEquals(doubleactuel,rightDouble);

    }
}