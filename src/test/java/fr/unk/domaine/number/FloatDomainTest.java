package fr.unk.domaine.number;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class FloatDomainTest {
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
    void testGetPossibility() {
        //génération aléatoire
        float val1;
        float val2;
        float val3 = 0.0f;
        float val4 = 100.0f;
        float val5;
        do {
            val1 = random.nextFloat();
        } while (val1 < val3);
        do {
            val2 = random.nextFloat();
        } while (val2 > val4);
        do {
            val5 = random.nextFloat();
        } while (val5 > val4 || val5 < val3 || val5 % 1.0f != 0);
        //init 5 valeurs , une valeur float5 compris dans un intervalle float3 et float4 ainsi qu'une valeur supérieur float2 et une valeur inférieur float1 le tout aléatoirement
        FloatDomain floatDomain = new FloatDomain(val3,val4,1.0f);
        List<Float> list = floatDomain.getPossibility();
        //Test si cela créer bien le bon nombre de possibilités dans le domaine
        assertEquals(((val4-val3)/1.0f)+1, list.size());
        //test si float1 et float2 sont bien exclu du domaine
        assertFalse(list.contains(val1));
        assertFalse(list.contains(val2));
        //test si float3,4,5 sont bien compris dans le domaine
        assertTrue(list.contains(val3));
        assertTrue(list.contains(val4));
        assertTrue(list.contains(val5));
    }
}