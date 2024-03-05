package fr.unk.domaine.number;

import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DoubleDomainTest {
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
    void testgetPossibility() {
        //génération aléatoire
        Random random = new Random();
        double double1;
        double double2;
        double double3 = 0.0;
        double double4 = 100.0;
        double double5;
        do {
            double1 = random.nextInt();
        } while (double1 < double3);
        do {
            double2 = random.nextInt();
        } while (double2 > double4);
        do {
            double5 = random.nextInt();
        } while (double5 > double4 || double5 < double3);
        //init 5 valeurs , une valeur double5 compris dans un intervalle double3 et double4 ainsi qu'une valeur supérieur double2 et une valeur inférieur double1 le tout aléatoirement
        DoubleDomain doubleDomain = new DoubleDomain(double3,double4,1.0);
        List<Double> list = doubleDomain.getPossibility();
        //Test si cela créer bien le bon nombre de possibilités dans le domaine
        assertEquals(((double4-double3)/1.0)+1, list.size());
        //test si double1 et double2 sont bien exclu du domaine
        assertFalse(list.contains(double1));
        assertFalse(list.contains(double2));
        //test si double3,4,5 sont bien compris dans le domaine
        assertTrue(list.contains(double3));
        assertTrue(list.contains(double4));
        assertTrue(list.contains(double5));
    }
}