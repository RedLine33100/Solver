package fr.unk.domaine.number;

import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class IntDomainTest {

    @BeforeAll
    static void setUpBeforeClass() {
        System.out.println("Avant toutes les executions");
    }

    @AfterAll
    static void tearDownAfterClass() {
        System.out.println("Apres toutes les executions");
    }

    @BeforeEach
    void setUp() {
        System.out.println("avant une fonction sous test");
    }

    @AfterEach
    void tearDown() {
        System.out.println("apres une fonction sous test");
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

        // Cas où les valeurs minimale, maximale et le pas sont négatifs : valeurs négatives avec un pas positif
        IntDomain domain4 = new IntDomain(-10, -2, 2);
        List<Integer> possibilities4 = domain4.getPossibility();
        assertEquals(5, possibilities4.size());

        // Cas où le pas est zéro : devrait renvoyer une liste vide
        IntDomain domain5 = new IntDomain(0, 10, 0);
        List<Integer> possibilities5 = domain5.getPossibility();
        assertTrue(possibilities5.isEmpty());
    }


    @Test
    void testGetPossibilityFonctionel() {
        Random random = new Random();
        int int1;
        int int2;
        int int3 = 0;
        int int4 = 100;
        int int5;
        do {
            int1 = random.nextInt();
        } while (int1 < int3);
        do {
            int2 = random.nextInt();
        } while (int2 > int4);
        do {
            int5 = random.nextInt();
        } while (int5 < int3 || int5 > int4);
        IntDomain intDomain = new IntDomain(int3, int4, 1);
        List<Integer> list = intDomain.getPossibility();
        assertEquals(((int4 - int3)) + 1, list.size());
        assertFalse(list.contains(int1));
        assertFalse(list.contains(int2));
        assertTrue(list.contains(int3));
        assertTrue(list.contains(int4));
        assertTrue(list.contains(int5));
    }
}