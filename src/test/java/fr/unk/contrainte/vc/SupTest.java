package fr.unk.contrainte.vc;

import fr.unk.variable.Getter;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SupTest {

    @Test
    void testsatisfied() {
        Random random = new Random();
        int int1 = random.nextInt();
        int int2;
        do {
            int2 = random.nextInt();
        } while (int2 < int1);
        Getter<Integer> v0_int = new Getter<>(int1);
        Getter<Integer> v1_int = new Getter<>(int2);
        Sup<Integer> sup_2_var_var = new Sup<>(v1_int, v0_int, false);
        Sup<Integer> sup_1_var_var = new Sup<>(v0_int, v1_int, false);
        Sup<Integer> sup_1_var_int = new Sup<>(v1_int, int1, false);
        Sup<Integer> sup_1_int_var = new Sup<>(int1, v1_int, false);
        assertTrue(sup_2_var_var.satisfied());
        assertFalse(sup_1_var_var.satisfied());
        assertFalse(sup_1_int_var.satisfied());
        assertTrue(sup_1_var_int.satisfied());

        float float1 = random.nextFloat();
        float float2;
        do {
            float2 = random.nextFloat();
        } while (float2 < float1);
        Getter<Float> v0_float = new Getter<>(float1);
        Getter<Float> v1_float = new Getter<>(float2);
        Sup<Float> sup_2_var_varf = new Sup<>(v1_float, v0_float, false);
        Sup<Float> sup_1_var_varf = new Sup<>(v0_float, v1_float, false);
        Sup<Float> sup_1_var_float = new Sup<>(v1_float, float1, false);
        Sup<Float> sup_1_float_var = new Sup<>(float1, v1_float, false);
        assertTrue(sup_2_var_varf.satisfied());
        assertFalse(sup_1_var_varf.satisfied());
        assertFalse(sup_1_float_var.satisfied());
        assertTrue(sup_1_var_float.satisfied());

        double double1 = random.nextDouble();
        double double2;
        do {
            double2 = random.nextDouble();
        } while (double2 < double1);
        Getter<Double> v0_double = new Getter<>(double1);
        Getter<Double> v1_double = new Getter<>(double2);
        Sup<Double> sup_2_var_vard = new Sup<>(v1_double, v0_double, false);
        Sup<Double> sup_1_var_vard = new Sup<>(v0_double, v1_double, false);
        Sup<Double> sup_1_var_double = new Sup<>(v1_double, double1, false);
        Sup<Double> sup_1_double_var = new Sup<>(double1, v1_double, false);
        assertTrue(sup_2_var_vard.satisfied());
        assertFalse(sup_1_var_vard.satisfied());
        assertFalse(sup_1_double_var.satisfied());
        assertTrue(sup_1_var_double.satisfied());

    }
}