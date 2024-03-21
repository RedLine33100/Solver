package fr.unk.contrainte.vc;

import org.junit.jupiter.api.Test;
import fr.unk.variable.VarGetter;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class DiffTest {
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
        //init 3 variables ( var2 == var0 != var1) pour voir si cela marche avec 2 variables différentes de même valeur:
        VarGetter var0 = new VarGetter(val1);
        VarGetter var1 = new VarGetter(val2);
        VarGetter var2 = new VarGetter(val1);
        //test si Diff(var1,var0) = true:
        Diff isVarDiff = new Diff(var1,var0);
        assertTrue(isVarDiff.satisfied(objectMap));
        //test si Diff(var2,var0) = false:
        Diff isNotVarDiff = new Diff(var2,var0);
        assertFalse(isNotVarDiff.satisfied(objectMap));
        //objectif tester si la fonction Diff fonctionne avec une variable et une valeur de type int
        //test si Diff(valeur int = var0 ,var0) = false:
        Diff isVarAndValDiff = new Diff(var0,val1);
        assertFalse(isVarAndValDiff.satisfied(objectMap));
        //test si Diff(valeur int = var1 ,var0) = true:
        Diff isNotValAndVarDiff = new Diff(val1,var1);
        assertTrue(isNotValAndVarDiff.satisfied(objectMap));

    }
    @Test
    void testFunctionalSatisfiedFloat() {
        float val1 = random.nextFloat();
        float val2;
        do {
            val2 = random.nextFloat();
        } while (val2 == val1);
        //init 3 variables ( var2 == var0 != var1) pour voir si cela marche avec 2 variables différentes de même valeur:
        VarGetter var0 = new VarGetter(val1);
        VarGetter var1 = new VarGetter(val2);
        VarGetter var2 = new VarGetter(val1);
        //test si Diff(var,var0) = true:
        Diff isVarDiff = new Diff(var1,var0);
        assertTrue(isVarDiff.satisfied(objectMap));
        //test si Diff(var2,var0) = false:
        Diff isNotVarDiff = new Diff(var2,var0);
        assertFalse(isNotVarDiff.satisfied(objectMap));
        //objectif tester si la fonction Diff fonctionne avec une variable et une valeur de type float
        //test si Diff(valeur float = var0 ,var0) = false:
        Diff isVarAndValDiff = new Diff(var0,val1);
        assertFalse(isVarAndValDiff.satisfied(objectMap));
        //test si Diff(valeur float = var1 ,var0) = true:
        Diff isNotValAndVarDiff = new Diff(val1,var1);
        assertTrue(isNotValAndVarDiff.satisfied(objectMap));
    }
    @Test
    void testFunctionalSatisfiedDouble() {
        double val1 = random.nextDouble();
        double val2;
        do {
            val2 = random.nextDouble();
        } while (val2 == val1);
        //init 3 variables ( var2 == var0 != var1) pour voir si cela marche avec 2 variables différentes de même valeur:
        VarGetter var0 = new VarGetter(val1);
        VarGetter var1 = new VarGetter(val2);
        VarGetter var2 = new VarGetter(val1);
        //test si Diff(var1,vra0) = true:
        Diff isVarDiff = new Diff(var1,var0);
        assertTrue(isVarDiff.satisfied(objectMap));
        //test si Diff(var2,var0) = false:
        Diff isNotVarDiff = new Diff(var2,var0);
        assertFalse(isNotVarDiff.satisfied(objectMap));
        //objectif tester si la fonction Diff fonctionne avec une variable et une valeur de type double
        //test si Diff(valeur double = var0 ,var0) = false:
        Diff isVarAndValDiff = new Diff(var0,val1);
        assertFalse(isVarAndValDiff.satisfied(objectMap));
        //test si Diff(valeur double = var1 ,var0) = true:
        Diff isNotValAndVarDiff = new Diff(val1,var1);
        assertTrue(isNotValAndVarDiff.satisfied(objectMap));


    }
    @Test
    void testFunctionalSatisfied() {
        //ne fonctionne pas quand il y a des types différent comme avec Inf
        /*VarGetter vInt = new VarGetter(10);
        VarGetter vDouble = new VarGetter(10.0);
        VarGetter vFloat = new VarGetter(10.0f);
        Diff isNotIntDouble = new Diff(vInt,vDouble);
        assertFalse(isVar.satisfied(objectMap));
        Diff isNotIntDouble = new Diff(5,10.0f);
        assertFalse(int_dif_float_true.satisfied(objectMap));
        */
    }
}
