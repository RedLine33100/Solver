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
    void testSatisfiedInt() {
        int int1 = random.nextInt();
        int int2;
        do {
            int2 = random.nextInt();
        } while (int2 == int1);
        //init 3 variables ( v2 == v0 != v1) pour voir si cela marche avec 2 variables différentes de même valeur:
        VarGetter v0_int = new VarGetter(int1);
        VarGetter v1_int = new VarGetter(int2);
        VarGetter v2_int = new VarGetter(int1);
        //test si Equal(v1,v0) = false:
        Equals equals_2_var_var = new Equals(v1_int,v0_int);
        assertFalse(equals_2_var_var.satisfied(objectMap));
        //test si Equal(v2,v0) = true:
        Equals equals_1_var_var = new Equals(v2_int,v0_int);
        assertTrue(equals_1_var_var.satisfied(objectMap));
        //objectif testé si la fonction Equal fonctionne avec une variable et un int
        //test si Equal(valeur int = v0 ,v0) = true:
        Equals equals_1_var_int = new Equals(v0_int,int1);
        assertTrue(equals_1_var_int.satisfied(objectMap));
        //test si Equal(valeur int = v1 ,v0) = false:
        Equals equals_1_int_var = new Equals(int1,v1_int);
        assertFalse(equals_1_int_var.satisfied(objectMap));
    }
    @Test
    void testSatisfiedFloat() {
        float float1 = random.nextFloat();
        float float2;
        do {
            float2 = random.nextFloat();
        } while (float2 == float1);
        //init 3 variables ( v2 == v0 != v1) pour voir si cela marche avec 2 variables différentes de même valeur:
        VarGetter v0_float = new VarGetter(float1);
        VarGetter v1_float = new VarGetter(float2);
        VarGetter v2_float = new VarGetter(float1);
        //test si Equal(v1,v0) = false:
        Equals equals_2_var_varf = new Equals(v1_float,v0_float);
        assertFalse(equals_2_var_varf.satisfied(objectMap));
        //test si Equal(v2,v0) = true:
        Equals equals_1_var_varf = new Equals(v2_float,v0_float);
        assertTrue(equals_1_var_varf.satisfied(objectMap));
        //objectif testé si la fonction Equal fonctionne avec une variable et un float
        //test si Equal(valeur float = v0 ,v0) = true:
        Equals equals_1_var_float = new Equals(v0_float,float1);
        assertTrue(equals_1_var_float.satisfied(objectMap));
        //test si Equal(valeur float = v1 ,v0) = false:
        Equals equals_1_float_var = new Equals(float1,v1_float);
        assertFalse(equals_1_float_var.satisfied(objectMap));

    }
    @Test
    void testSatisfiedDouble() {
        double double1 = random.nextDouble();
        double double2;
        do {
            double2 = random.nextDouble();
        } while (double2 == double1);
        //init 3 variables ( v2 == v0 != v1) pour voir si cela marche avec 2 variables différentes de même valeur:
        VarGetter v0_double = new VarGetter(double1);
        VarGetter v1_double = new VarGetter(double2);
        VarGetter v2_double = new VarGetter(double1);
        //test si Equal(v1,v0) = false:
        Equals equals_2_var_vard = new Equals(v1_double,v0_double);
        assertFalse(equals_2_var_vard.satisfied(objectMap));
        //test si Equal(v2,v0) = true:
        Equals equals_1_var_vard = new Equals(v2_double,v0_double);
        assertTrue(equals_1_var_vard.satisfied(objectMap));
        //objectif testé si la fonction Equal fonctionne avec une variable et un double
        //test si Equal(valeur double = v0 ,v0) = true:
        Equals equals_1_var_double = new Equals(v0_double,double1);
        assertTrue(equals_1_var_double.satisfied(objectMap));
        //test si Equal(valeur double = v1 ,v0) = false:
        Equals equals_1_double_var = new Equals(double1,v1_double);
        assertFalse(equals_1_double_var.satisfied(objectMap));
    }
    //pareil que pour Inf, il ne peut pas y avoir différent type.
}