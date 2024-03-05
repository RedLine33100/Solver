package fr.unk.variable;

import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class VarGetterTest {
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
    void isVar() {
        Random random = new Random();
        Variable<Integer> intVar = new Variable<>("testVar1", Integer.class);
        VarGetter<Integer> varGettervar = new VarGetter(intVar);
        assertTrue(varGettervar.isVar());

        VarGetter<String> varGetter = new VarGetter<>("Test");
        assertFalse(varGetter.isVar());

        int int1 = random.nextInt();
        VarGetter<Integer> varGetter0 = new VarGetter<>(int1);
        assertFalse(varGetter0.isVar());

        double double1 = random.nextDouble();
        VarGetter<Double> varGetter01 = new VarGetter<>(double1);
        assertFalse(varGetter01.isVar());

        float float1 = random.nextFloat();
        VarGetter<Float> varGetter02 = new VarGetter<>(float1);
        assertFalse(varGetter02.isVar());

        VarGetter<String> varGetter1 = new VarGetter<>(null);
        assertTrue(varGetter1.isVar());
    }

    @Test
    void getValue() {
        Random random = new Random();
        Map<String, Object> objectMap = new HashMap<>();
        //test si la fonction get renvoie bien la bonne valeur d'un int:
        int int1 = random.nextInt();
        VarGetter<Integer> varGetter = new VarGetter<>(int1);
        assertEquals(int1,varGetter.getValue(objectMap));

        //test si la fonction get renvoie bien la bonne valeur d'un le bon float:
        float float1 = random.nextFloat();
        VarGetter<Float> varGetter1 = new VarGetter<>(float1);
        assertEquals(float1,varGetter1.getValue(objectMap));

        //test si la fonction get renvoie bien la bonne valeur d'un le bon double
        double double1 = random.nextDouble();
        VarGetter<Double> varGetter2 = new VarGetter<>(double1);
        assertEquals(double1,varGetter2.getValue(objectMap));
    }
}