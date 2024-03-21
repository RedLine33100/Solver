package fr.unk.contrainte.vc;

import org.junit.jupiter.api.Test;
import fr.unk.variable.VarGetter;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class SupTest {
    Random random = new Random();
    Map<String,Object> objectMap = new HashMap<>();
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
    void testFunctionalSatisfiedFloat() {
        float val1 = random.nextFloat();
        float val2;
        do {
            val2 = random.nextFloat();
        } while (val2 < val1);
        //init 2 variable (v0 < v1):
        VarGetter var0 = new VarGetter(val1);
        VarGetter var1 = new VarGetter(val2);
        //test si Sup(var1,var0)= true:
        Sup isVarSup = new Sup(var1,var0,false);
        assertTrue(isVarSup.satisfied(objectMap));
        //test si Sup(var0,var1)= false:
        Sup isNotVarSup = new Sup(var0,var1,false);
        assertFalse(isNotVarSup.satisfied(objectMap));
        //objectif tester si la fonction Sup fonctionne avec une variable et une valeur de type double
        //test si Sup(var1, une valeur float = var0)= true:
        Sup isVarAndValSup = new Sup(var1,val1,false);
        assertTrue(isVarAndValSup.satisfied(objectMap));
        //test si Inf(une valeur float = var0,var1)= false:
        Sup isNotValAndVarSup = new Sup(val1,var1,false);
        assertFalse(isNotValAndVarSup.satisfied(objectMap));
    }
    @Test
    void testFunctionalSatisfiedDouble() {
        double val1 = random.nextDouble();
        double val2;
        do {
            val2 = random.nextDouble();
        } while (val2 < val1);
        //init 2 variable (v0 < v1):
        VarGetter var0 = new VarGetter(val1);
        VarGetter var1 = new VarGetter(val2);
        //test si Sup(var1,var0)= true:
        Sup isVarSup = new Sup(var1,var0,false);
        assertTrue(isVarSup.satisfied(objectMap));
        //test si Sup(var0,var1)= false:
        Sup isNotVarSup = new Sup(var0,var1,false);
        assertFalse(isNotVarSup.satisfied(objectMap));
        //objectif tester si la fonction Sup fonctionne avec une variable et une valeur de type double
        //test si Sup(var1, une valeur double = var0)= true:
        Sup isVarAndValSup = new Sup(var1,val1,false);
        assertTrue(isVarAndValSup.satisfied(objectMap));
        //test si Inf(une valeur double = var0,var1)= false:
        Sup isNotValAndVarSup = new Sup(val1,var1,false);
        assertFalse(isNotValAndVarSup.satisfied(objectMap));

    }

    @Test
    void testFunctionalSatisfiedInt() {
        int val1 = random.nextInt();
        int val2;
        do {
            val2 = random.nextInt();
        } while (val2 < val1);
        //init 2 variable (v0 < v1):
        VarGetter var0 = new VarGetter(val1);
        VarGetter var1 = new VarGetter(val2);
        //test si Sup(var1,var0)= true:
        Sup isVarSup = new Sup(var1,var0,false);
        assertTrue(isVarSup.satisfied(objectMap));
        //test si Sup(var0,var1)= false:
        Sup isNotVarSup = new Sup(var0,var1,false);
        assertFalse(isNotVarSup.satisfied(objectMap));
        //objectif tester si la fonction Sup fonctionne avec une variable et une valeur de type int
        //test si Sup(var1, une valeur int = var0)= true:
        Sup isVarAndValSup = new Sup(var1,val1,false);
        assertTrue(isVarAndValSup.satisfied(objectMap));
        //test si Sup(var1, une valeur int = var0)= true:
        Sup isNotValAndVarSup = new Sup(val1,var1,false);
        assertFalse(isNotValAndVarSup.satisfied(objectMap));
    }
    //pareil que pour Inf, il ne peut pas y avoir différent type.
}
