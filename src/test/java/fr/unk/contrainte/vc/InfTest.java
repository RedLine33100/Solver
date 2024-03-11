package fr.unk.contrainte.vc;

import fr.unk.variable.Getter;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InfTest {

    @Test
    void testsatisfied() {
        Random random = new Random();
        int int1 = random.nextInt();
        int int2;
        do {
            int2 = random.nextInt();
        } while (int2 > int1);
        Getter<Integer> v0_int = new Getter<>(int1);
        Getter<Integer> v1_int = new Getter<>(int2);
        Inf<Integer> inf_2_var_var = new Inf<>(v1_int, v0_int, false);
        Inf<Integer> inf_1_var_var = new Inf<>(v0_int, v1_int, false);
        Inf<Integer> inf_1_var_int = new Inf<>(v1_int, int1, false);
        Inf<Integer> inf_1_int_var = new Inf<>(int1, v1_int, false);
        assertTrue(inf_2_var_var.satisfied());
        assertFalse(inf_1_var_var.satisfied());
        assertFalse(inf_1_int_var.satisfied());
        assertTrue(inf_1_var_int.satisfied());

        float float1 = random.nextFloat();
        float float2;
        do {
            float2 = random.nextFloat();
        } while (float2 > float1);
        Getter<Float> v0_float = new Getter<>(float1);
        Getter<Float> v1_float = new Getter<>(float2);
        Inf<Float> inf_2_var_varf = new Inf<>(v1_float, v0_float, false);
        Inf<Float> inf_1_var_varf = new Inf<>(v0_float, v1_float, false);
        Inf<Float> inf_1_var_float = new Inf<>(v1_float, float1, false);
        Inf<Float> inf_1_float_var = new Inf<>(float1, v1_float, false);
        assertTrue(inf_2_var_varf.satisfied());
        assertFalse(inf_1_var_varf.satisfied());
        assertFalse(inf_1_float_var.satisfied());
        assertTrue(inf_1_var_float.satisfied());

        double double1 = random.nextDouble();
        double double2;
        do {
            double2 = random.nextDouble();
        } while (double2 > double1);
        Getter<Double> v0_double = new Getter<>(double1);
        Getter<Double> v1_double = new Getter<>(double2);
        Inf<Double> inf_2_var_vard = new Inf<>(v1_double, v0_double, false);
        Inf<Double> inf_1_var_vard = new Inf<>(v0_double, v1_double, false);
        Inf<Double> inf_1_var_double = new Inf<>(v1_double, double1, false);
        Inf<Double> inf_1_double_var = new Inf<>(double1, v1_double, false);
        assertTrue(inf_2_var_vard.satisfied());
        assertFalse(inf_1_var_vard.satisfied());
        assertFalse(inf_1_double_var.satisfied());
        assertTrue(inf_1_var_double.satisfied());

    }
}