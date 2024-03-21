package fr.unk.contrainte.vc;

import org.junit.jupiter.api.Test;
import fr.unk.variable.VarGetter;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class EqualsTest {
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
    void testFunctionalSatisfiedInt() {
        int val1 = random.nextInt();
        int val2;
        do {
            val2 = random.nextInt();
        } while (val2 == val1);
        //init 3 variables ( v2 == v0 != v1) pour voir si cela marche avec 2 variables différentes de même valeur:
        VarGetter var0 = new VarGetter(val1);
        VarGetter var1 = new VarGetter(val2);
        VarGetter var2 = new VarGetter(val1);
        //test si Equal(var1,var0) = false:
        Equals isVarEquals = new Equals(var1,var0);
        assertFalse(isVarEquals.satisfied(objectMap));
        //test si Equal(var2,var0) = true:
        Equals isNotVarEquals = new Equals(var2,var0);
        assertTrue(isNotVarEquals.satisfied(objectMap));
        //objectif tester si la fonction Equal fonctionne avec une variable et une valeur de type int
        //test si Equal(valeur int = var0 ,var0) = true:
        Equals isVarAndValEquals = new Equals(var0,val1);
        assertTrue(isVarAndValEquals.satisfied(objectMap));
        //test si Equal(valeur int = var1 ,var0) = false:
        Equals isNotValAndVarEquals = new Equals(val1,var1);
        assertFalse(isNotValAndVarEquals.satisfied(objectMap));
    }
    @Test
    void testFunctionalSatisfiedFloat() {
        float val1 = random.nextFloat();
        float val2;
        do {
            val2 = random.nextFloat();
        } while (val2 == val1);
        //init 3 variables ( var2 == var0 != v1) pour voir si cela marche avec 2 variables différentes de même valeur:
        VarGetter var0 = new VarGetter(val1);
        VarGetter var1 = new VarGetter(val2);
        VarGetter var2 = new VarGetter(val1);
        //test si Equal(var1,var0) = false:
        Equals isVarEquals = new Equals(var1,var0);
        assertFalse(isVarEquals.satisfied(objectMap));
        //test si Equal(var2,var0) = true:
        Equals isNotVarEquals = new Equals(var2,var0);
        assertTrue(isNotVarEquals.satisfied(objectMap));
        //objectif tester si la fonction Equal fonctionne avec une variable et une valeur de type float
        //test si Equal(valeur float = var0 ,var0) = true:
        Equals isVarAndValEquals = new Equals(var0,val1);
        assertTrue(isVarAndValEquals.satisfied(objectMap));
        //test si Equal(valeur float = var1 ,var0) = false:
        Equals isNotValAndVarEquals = new Equals(val1,var1);
        assertFalse(isNotValAndVarEquals.satisfied(objectMap));

    }
    @Test
    void testFunctionalSatisfiedDouble() {
        double val1 = random.nextDouble();
        double val2;
        do {
            val2 = random.nextDouble();
        } while (val2 == val1);
        //init 3 variables ( v2 == v0 != v1) pour voir si cela marche avec 2 variables différentes de même valeur:
        VarGetter var0 = new VarGetter(val1);
        VarGetter var1 = new VarGetter(val2);
        VarGetter var2 = new VarGetter(val1);
        //test si Equal(var1,var0) = false:
        Equals isVarEquals = new Equals(var1,var0);
        assertFalse(isVarEquals.satisfied(objectMap));
        //test si Equal(var2,var0) = true:
        Equals isNotVarEquals = new Equals(var2,var0);
        assertTrue(isNotVarEquals.satisfied(objectMap));
        //objectif tester si la fonction Equal fonctionne avec une variable et une valeur de type double
        //test si Equal(valeur double = var0 ,var0) = true:
        Equals isVarAndValEquals = new Equals(var0,val1);
        assertTrue(isVarAndValEquals.satisfied(objectMap));
        //test si Equal(valeur double = var1 ,var0) = false:
        Equals isNotValAndVarEquals = new Equals(val1,var1);
        assertFalse(isNotValAndVarEquals.satisfied(objectMap));
    }
    //pareil que pour Inf, il ne peut pas y avoir différent type.
}
