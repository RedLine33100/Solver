package fr.unk.domaine.number;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class DoubleDomainTest {
    Random random = new Random();
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
    void testFunctionalGetPossibility() {
        //génération aléatoire
        double val1;
        double val2;
        double val3 = 0.0;
        double val4 = 100.0;
        double val5;
        do {
            val1 = random.nextInt();
        } while (val1 < val3);
        do {
            val2 = random.nextInt();
        } while (val2 > val4);
        do {
            val5 = random.nextInt();
        } while (val5 > val4 || val5 < val3);
        //init 5 valeurs , une valeur double5 compris dans un intervalle double3 et double4 ainsi qu'une valeur supérieur double2 et une valeur inférieur double1 le tout aléatoirement
        DoubleDomain doubleDomain = new DoubleDomain(val3,val4,1.0);
        List<Double> list = doubleDomain.getPossibility();
        //Test si cela créer bien le bon nombre de possibilités dans le domaine
        assertEquals(((val4-val3)/1.0)+1, list.size());
        //test si double1 et double2 sont bien exclu du domaine
        assertFalse(list.contains(val1));
        assertFalse(list.contains(val2));
        //test si double3,4,5 sont bien compris dans le domaine
        assertTrue(list.contains(val3));
        assertTrue(list.contains(val4));
        assertTrue(list.contains(val5));
    }
}
