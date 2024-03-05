package fr.unk.contrainte.vc;

import fr.unk.variable.VarGetter;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.WeakHashMap;

import static org.junit.jupiter.api.Assertions.*;

class InfTest {
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
    void testsatisfied_double() {
        Random random = new Random();
        Map<String,Object> objectMap = new HashMap<>();
        double double1 = random.nextDouble();
        double double2;
        do {
            double2 = random.nextDouble();
        } while (double2 > double1);
        //init 2 variable (v0 > v1):
        VarGetter v0_double = new VarGetter(double1);
        VarGetter v1_double = new VarGetter(double2);
        //test si Inf(v1,v0)= true:
        Inf inf_2_var_vard = new Inf(v1_double,v0_double,false);
        assertTrue(inf_2_var_vard.satisfied(objectMap));
        //test si Inf(v0,v1)= false:
        Inf inf_1_var_vard = new Inf(v0_double,v1_double,false);
        assertFalse(inf_1_var_vard.satisfied(objectMap));
        //objectif testé si la fonction Inf fonctionne avec une variable et un double
        //test si Inf(v1, une valeur double = v0)= true:
        Inf inf_1_var_double = new Inf(v1_double,double1,false);
        assertTrue(inf_1_var_double.satisfied(objectMap));
        //test si Inf(une valeur double = v0,v1)= false:
        Inf inf_1_double_var = new Inf(double1,v1_double,false);
        assertFalse(inf_1_double_var.satisfied(objectMap));
    }
    @Test
    void testsatisfied_int() {
        Random random = new Random();
        Map<String,Object> objectMap = new HashMap<>();
        int int1 = random.nextInt();
        int int2;
        do {
            int2 = random.nextInt();
        } while (int2 > int1);
        //init 2 variable (v0 > v1):
        VarGetter v0_int = new VarGetter(int1);
        VarGetter v1_int = new VarGetter(int2);
        //test si Inf(v1,v0)= true:
        Inf inf_2_var_var = new Inf(v1_int,v0_int,false);
        assertTrue(inf_2_var_var.satisfied(objectMap));
        //test si Inf(v0,v1)= false:
        Inf inf_1_var_var = new Inf(v0_int,v1_int,false);
        assertFalse(inf_1_var_var.satisfied(objectMap));
        //objectif testé si la fonction Inf fonctionne avec une variable et un int
        //test si Inf(v1, une valeur int = v0)= true:
        Inf inf_1_var_int = new Inf(v1_int,int1,false);
        assertTrue(inf_1_var_int.satisfied(objectMap));
        //test si Inf(une valeur int = v0,v1)= false:
        Inf inf_1_int_var = new Inf(int1,v1_int,false);
        assertFalse(inf_1_int_var.satisfied(objectMap));

    }
    @Test
    void testsatisfied_float() {
        Random random = new Random();
        Map<String,Object> objectMap = new HashMap<>();
        float float1 = random.nextFloat();
        float float2;
        do {
            float2 = random.nextFloat();
        } while (float2 > float1);
        //init 2 variable (v0 > v1):
        VarGetter v0_float = new VarGetter(float1);
        VarGetter v1_float = new VarGetter(float2);
        //test si Inf(v1,v0)= true:
        Inf inf_2_var_varf = new Inf(v1_float,v0_float,false);
        assertTrue(inf_2_var_varf.satisfied(objectMap));
        //test si Inf(v0,v1)= false:
        Inf inf_1_var_varf = new Inf(v0_float,v1_float,false);
        assertFalse(inf_1_var_varf.satisfied(objectMap));
        //objectif testé si la fonction Inf fonctionne avec une variable et un float
        //test si Inf(v1, une valeur float = v0)= true:
        Inf inf_1_var_float = new Inf(v1_float,float1,false);
        assertTrue(inf_1_var_float.satisfied(objectMap));
        //test si Inf(une valeur float = v0,v1)= false:
        Inf inf_1_float_var = new Inf(float1,v1_float,false);
        assertFalse(inf_1_float_var.satisfied(objectMap));
    }
    //Inf ne fonctionne pas quand on change de type.
    /*
    @Test
    void testsatisfied(){
        Map<String,Object> objectMap = new HashMap<>();
        VarGetter v0_float = new VarGetter(5.5);
        VarGetter v0_int = new VarGetter(3);
        VarGetter v0_double = new VarGetter(10.0);
        Inf inf_int_float = new Inf(v0_int,v0_float,false);
        assertTrue(inf_int_float.satisfied(objectMap));
        Inf inf_int_double = new Inf(v0_int,v0_double,false);
        assertTrue(inf_int_double.satisfied(objectMap));
        Inf inf_double_float = new Inf(v0_double,v0_float,false);
        assertTrue(inf_double_float.satisfied(objectMap));
    }*/
}