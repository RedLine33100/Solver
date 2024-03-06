package fr.unk.domaine.number;

import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class IntDomainTest {
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
    void testGetPossibilityStructurel() {
        // Cas standard : valeurs positives avec un pas positif
        IntDomain domain1 = new IntDomain(0, 10, 2);
        List<Integer> possibilities1 = domain1.getPossibility();
        assertEquals(6, possibilities1.size());
        assertEquals(0, possibilities1.get(0));
        assertEquals(2, possibilities1.get(1));
        assertEquals(4, possibilities1.get(2));
        assertEquals(6, possibilities1.get(3));
        assertEquals(8, possibilities1.get(4));
        assertEquals(10, possibilities1.get(5));

        /*
        // Cas où le pas est négatif : valeurs positives avec un pas négatif
        IntDomain domain2 = new IntDomain(0, 10, -2);
        List<Integer> possibilities2 = domain2.getPossibility();
        assertEquals(1, possibilities2.size());
        assertEquals(0, possibilities2.get(0));

        // Cas où les valeurs minimale, maximale et le pas sont négatifs : valeurs négatives avec un pas négatif
        IntDomain domain3 = new IntDomain(-10, -2, -2);
        List<Integer> possibilities3 = domain3.getPossibility();
        //assertEquals(5, possibilities3.size());
        assertEquals(-10, possibilities3.get(0));
        assertEquals(-8, possibilities3.get(1));
        assertEquals(-6, possibilities3.get(2));
        assertEquals(-4, possibilities3.get(3));
        assertEquals(-2, possibilities3.get(4));
        */
        /*
        // Cas où les valeurs minimale, maximale et le pas sont négatifs : valeurs négatives avec un pas positif
        IntDomain domain4 = new IntDomain(-10, -2, 2);
        List<Integer> possibilities4 = domain4.getPossibility();
        assertEquals(5, possibilities4.size());

        // Cas où le pas est zéro : devrait renvoyer une liste vide
        IntDomain domain5 = new IntDomain(0, 10, 0);
        List<Integer> possibilities5 = domain5.getPossibility();
        assertNull(possibilities5);
        */

    }


    @Test
    void testGetPossibilityFonctionel() {
        //génération aléatoire
        int val1;
        int val2;
        int val3 = 0;
        int val4 = 100;
        int val5;
        do {
            val1 = random.nextInt();
        } while (val1 < val3);
        do {
            val2 = random.nextInt();
        } while (val2 > val4);
        do {
            val5 = random.nextInt();
        } while (val5 < val3 || val5 > val4);
        //init 5 valeurs , une valeur int5 compris dans un intervalle int3 et int4 ainsi qu'une valeur supérieur int2 et une valeur inférieur int1 le tout aléatoirement
        IntDomain intDomain = new IntDomain(val3,val4,1);
        List<Integer> list = intDomain.getPossibility();
        //Test si cela créer bien le bon nombre de possibilités dans le domaine
        assertEquals(((val4-val3)/1)+1, list.size());
        //test si int1 et int2 sont bien exclu du domaine
        assertFalse(list.contains(val1));
        assertFalse(list.contains(val2));
        //test si int3,4,5 sont bien compris dans le domaine
        assertTrue(list.contains(val3));
        assertTrue(list.contains(val4));
        assertTrue(list.contains(val5));
    }
}