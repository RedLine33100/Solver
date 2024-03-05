package fr.unk.domaine.number;

import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class FloatDomainTest {
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
        float float1;
        float float2;
        float float3 = 0.0f;
        float float4 = 100.0f;
        float float5;
        do {
            float1 = random.nextFloat();
        } while (float1 < float3);
        do {
            float2 = random.nextFloat();
        } while (float2 > float4);
        do {
            float5 = random.nextFloat();
        } while (float5 > float4 || float5 < float3 || float5 % 1.0f != 0);
        //init 5 valeurs , une valeur float5 compris dans un intervalle float3 et float4 ainsi qu'une valeur supérieur float2 et une valeur inférieur float1 le tout aléatoirement
        FloatDomain floatDomain = new FloatDomain(float3,float4,1.0f);
        List<Float> list = floatDomain.getPossibility();
        //Test si cela créer bien le bon nombre de possibilités dans le domaine
        assertEquals(((float4-float3)/1.0f)+1, list.size());
        //test si float1 et float2 sont bien exclu du domaine
        assertFalse(list.contains(float1));
        assertFalse(list.contains(float2));
        //test si float3,4,5 sont bien compris dans le domaine
        assertTrue(list.contains(float3));
        assertTrue(list.contains(float4));
        assertTrue(list.contains(float5));
    }
}