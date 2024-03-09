package fr.unk.contrainte.vc;

import fr.unk.variable.VarGetter;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EqualsTest {

    @Test
    void testsatisfied() {
        Random random = new Random();
        int int1 = random.nextInt();
        int int2;
        do {
            int2 = random.nextInt();
        } while (int2 == int1);
        VarGetter<Integer> v0_int = new VarGetter<>(int1);
        VarGetter<Integer> v1_int = new VarGetter<>(int2);
        VarGetter<Integer> v2_int = new VarGetter<>(int1);
        Equals<Integer> equals_2_var_var = new Equals<>(v1_int,v0_int);
        Equals<Integer> equals_1_var_var = new Equals<>(v2_int,v0_int);
        Equals<Integer> equals_1_var_int = new Equals<>(v0_int,int1);
        Equals<Integer> equals_1_int_var = new Equals<>(int1,v1_int);
        assertFalse(equals_2_var_var.satisfied());
        assertTrue(equals_1_var_var.satisfied());
        assertFalse(equals_1_int_var.satisfied());
        assertTrue(equals_1_var_int.satisfied());

        float float1 = random.nextFloat();
        float float2;
        do {
            float2 = random.nextFloat();
        } while (float2 == float1);
        VarGetter<Float> v0_float = new VarGetter<>(float1);
        VarGetter<Float> v1_float = new VarGetter<>(float2);
        VarGetter<Float> v2_float = new VarGetter<>(float1);
        Equals<Float> equals_2_var_varf = new Equals<>(v1_float,v0_float);
        Equals<Float> equals_1_var_varf = new Equals<>(v2_float,v0_float);
        Equals<Float> equals_1_var_float = new Equals<>(v0_float,float1);
        Equals<Float> equals_1_float_var = new Equals<>(float1,v1_float);
        assertFalse(equals_2_var_varf.satisfied());
        assertTrue(equals_1_var_varf.satisfied());
        assertFalse(equals_1_float_var.satisfied());
        assertTrue(equals_1_var_float.satisfied());

        double double1 = random.nextDouble();
        double double2;
        do {
            double2 = random.nextDouble();
        } while (double2 == double1);
        VarGetter<Double> v0_double = new VarGetter<>(double1);
        VarGetter<Double> v1_double = new VarGetter<>(double2);
        VarGetter<Double> v2_double = new VarGetter<>(double1);
        Equals<Double> equals_2_var_vard = new Equals<>(v1_double,v0_double);
        Equals<Double> equals_1_var_vard = new Equals<>(v2_double,v0_double);
        Equals<Double> equals_1_var_double = new Equals<>(v0_double,double1);
        Equals<Double> equals_1_double_var = new Equals<>(double1,v1_double);
        assertFalse(equals_2_var_vard.satisfied());
        assertTrue(equals_1_var_vard.satisfied());
        assertFalse(equals_1_double_var.satisfied());
        assertTrue(equals_1_var_double.satisfied());

    }
}