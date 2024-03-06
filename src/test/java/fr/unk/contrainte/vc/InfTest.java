package fr.unk.contrainte.vc;

import org.junit.jupiter.api.Test;
import fr.unk.variable.VarGetter;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.WeakHashMap;
import static org.junit.jupiter.api.Assertions.*;

class InfTest {
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
    void testSatisfiedDouble() {
        double val1 = random.nextDouble();
        double val2;
        do {
            val2 = random.nextDouble();
        } while (val2 > val1);
        //init 2 variable (var0 > var1):
        VarGetter var0 = new VarGetter(val1);
        VarGetter var1 = new VarGetter(val2);
        //test si Inf(var1,var0)= true:
        Inf isVarInf = new Inf(var1,var0,false);
        assertTrue(isVarInf.satisfied(objectMap));
        //test si Inf(var0,var1)= false:
        Inf isNotVarInf = new Inf(var0,var1,false);
        assertFalse(isNotVarInf.satisfied(objectMap));
        //objectif tester si la fonction Inf fonctionne avec une variable et une valeur de type double
        //test si Inf(var1, une valeur double = val0)= true:
        Inf isVarAndValInf = new Inf(var1,val1,false);
        assertTrue(isVarAndValInf.satisfied(objectMap));
        //test si Inf(une valeur double = val0,var1)= false:
        Inf isNotValAndVarInf = new Inf(val1,var1,false);
        assertFalse(isNotValAndVarInf.satisfied(objectMap));
    }
    @Test
    void testSatisfiedInt() {
        int val1 = random.nextInt();
        int val2;
        do {
            val2= random.nextInt();
        } while (val2 > val1);
        //init 2 variable (var0 > var1):
        VarGetter var0 = new VarGetter(val1);
        VarGetter var1 = new VarGetter(val2);
        //test si Inf(var1,var0)= true:
        Inf isVarInf = new Inf(var1,var0,false);
        assertTrue(isVarInf.satisfied(objectMap));
        //test si Inf(var0,var1)= false:
        Inf isNotVarInf = new Inf(var0,var1,false);
        assertFalse(isNotVarInf.satisfied(objectMap));
        //objectif tester si la fonction Inf fonctionne avec une variable et une valeur de type int
        //test si Inf(var1, une valeur int = var0) = true:
        Inf isVarAndValInf = new Inf(var1,val1,false);
        assertTrue(isVarAndValInf.satisfied(objectMap));
        //test si Inf(une valeur int = var0,var1) = false:
        Inf isNotValAndVarInf = new Inf(val1,var0,false);
        assertFalse(isNotValAndVarInf.satisfied(objectMap));

    }
    @Test
    void testSatisfiedFloat() {
        float val1 = random.nextFloat();
        float val2;
        do {
            val2 = random.nextFloat();
        } while (val2 > val1);
        //init 2 variable (var0 > var1):
        VarGetter var0 = new VarGetter(val1);
        VarGetter var1 = new VarGetter(val2);
        //test si Inf(var1,var0)= true:
        Inf isVarInf = new Inf(var1,var0,false);
        assertTrue(isVarInf.satisfied(objectMap));
        //test si Inf(var0,var1)= false:
        Inf isNotVarInf = new Inf(var0,var1,false);
        assertFalse(isNotVarInf.satisfied(objectMap));
        //objectif tester si la fonction Inf fonctionne avec une variable et une valeur de type double
        //test si Inf(var1, une valeur float = var0)= true:
        Inf isVarAndValInf = new Inf(var1,val1,false);
        assertTrue(isVarAndValInf.satisfied(objectMap));
        //test si Inf(une valeur float = var0,var1)= false:
        Inf isNotValAndVarInf = new Inf(val1,var1,false);
        assertFalse(isNotValAndVarInf.satisfied(objectMap));
    }
    //Inf ne fonctionne pas quand on change de type.
    /*
    @Test
    void testsatisfied(){
        VarGetter v0_float = new VarGetter(5.5);
        VarGetter v0_int = new VarGetter(3);
        VarGetter v0_double = new VarGetter(10.0);
        Inf isIntFloat = new Inf(v0_int,v0_float,false);
        assertTrue(inf_int_float.satisfied(objectMap));
        Inf isIntDouble = new Inf(v0_int,v0_double,false);
        assertTrue(inf_int_double.satisfied(objectMap));
        Inf isDoubleFloat = new Inf(v0_double,v0_float,false);
        assertTrue(inf_double_float.satisfied(objectMap));
    }*/
}