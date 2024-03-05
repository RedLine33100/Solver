package fr.unk.utils;

import org.junit.jupiter.api.*;

import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class PairTest <L,R>{
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
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
        Random random = new Random();
        //init différente variable:
        int L_int = random.nextInt();
        int R_int = random.nextInt();
        double L_double = random.nextDouble();
        double R_double = random.nextDouble();
        float L_float = random.nextFloat();
        float R_float = random.nextFloat();
        //objectif testé si on arrive bien à récupérer les bonnes valeurs des 3 domaines est que les valeurs L et R n'ont pas besoin d'être dans le même domaine
        Pair<L, R> double_pair = (Pair<L, R>) new Pair<>(L_double, R_float);
        L doubleactuel = double_pair.getL();
        //test si la paire n'est pas null, puis test si la valeur récupérer est bien la valeur attendue (un double)
        assertNotNull(doubleactuel);
        assertEquals(doubleactuel,L_double);
        Pair<L, R> float_pair = (Pair<L, R>) new Pair<>(L_float, R_int);
        L floatactuel = float_pair.getL();
        //Même test pour un float
        assertNotNull(floatactuel);
        assertEquals(floatactuel,L_float);
        Pair<L, R> int_pair = (Pair<L, R>) new Pair<>(L_int, R_double);
        L intactuel = int_pair.getL();
        //Même test pour un int
        assertNotNull(intactuel);
        assertEquals(intactuel,L_int);
    }

    @Test
    void testgetR() {
        //pareil que test Left mais pour la paire Right
        Random random = new Random();
        int L_int = random.nextInt();
        int R_int = random.nextInt();
        double L_double = random.nextDouble();
        double R_double = random.nextDouble();
        float L_float = random.nextFloat();
        float R_float = random.nextFloat();
        //test pour les float
        Pair<L, R> float_pair = (Pair<L, R>) new Pair<>(L_double, R_float);
        R floatactuel = float_pair.getR();
        assertNotNull(floatactuel);
        assertEquals(floatactuel,R_float);
        //test pour les int
        Pair<L, R> int_pair = (Pair<L, R>) new Pair<>(L_float, R_int);
        R intactuel = int_pair.getR();
        assertNotNull(intactuel);
        assertEquals(intactuel,R_int);
        //test pour les double
        Pair<L, R> double_pair = (Pair<L, R>) new Pair<>(L_int, R_double);
        R doubleactuel = double_pair.getR();
        assertNotNull(doubleactuel);
        assertEquals(doubleactuel,R_double);

    }
}