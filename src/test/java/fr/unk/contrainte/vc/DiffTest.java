package fr.unk.contrainte.vc;

import fr.unk.variable.Getter;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DiffTest {

    @Test
    void testsatisfied() {
        Random random = new Random();
        int int1 = random.nextInt();
        int int2;
        do {
            int2 = random.nextInt();
        } while (int2 == int1);
        Getter<Integer> v0_int = new Getter<>(int1);
        Getter<Integer> v1_int = new Getter<>(int2);
        Getter<Integer> v2_int = new Getter<>(int1);
        Diff<Integer> diff_2_var_var = new Diff<>(v1_int,v0_int);
        Diff<Integer> diff_1_var_var = new Diff<>(v2_int,v0_int);
        Diff<Integer> diff_1_var_int = new Diff<>(v0_int,int1);
        Diff<Integer> diff_1_int_var = new Diff<>(int1,v1_int);
        assertTrue(diff_2_var_var.satisfied());
        assertFalse(diff_1_var_var.satisfied());
        assertTrue(diff_1_int_var.satisfied());
        assertFalse(diff_1_var_int.satisfied());

        float float1 = random.nextFloat();
        float float2;
        do {
            float2 = random.nextFloat();
        } while (float2 == float1);
        Getter<Float> v0_float = new Getter<>(float1);
        Getter<Float> v1_float = new Getter<>(float2);
        Getter<Float> v2_float = new Getter<>(float1);
        Diff<Float> diff_2_var_varf = new Diff<>(v1_float,v0_float);
        Diff<Float> diff_1_var_varf = new Diff<>(v2_float,v0_float);
        Diff<Float> diff_1_var_float = new Diff<>(v0_float,float1);
        Diff<Float> diff_1_float_var = new Diff<>(float1,v1_float);
        assertTrue(diff_2_var_varf.satisfied());
        assertFalse(diff_1_var_varf.satisfied());
        assertTrue(diff_1_float_var.satisfied());
        assertFalse(diff_1_var_float.satisfied());

        double double1 = random.nextDouble();
        double double2;
        do {
            double2 = random.nextDouble();
        } while (double2 == double1);
        Getter<Double> v0_double = new Getter<>(double1);
        Getter<Double> v1_double = new Getter<>(double2);
        Getter<Double> v2_double = new Getter<>(double1);
        Diff<Double> diff_2_var_vard = new Diff<>(v1_double,v0_double);
        Diff<Double> diff_1_var_vard = new Diff<>(v2_double,v0_double);
        Diff<Double> diff_1_var_double = new Diff<>(v0_double,double1);
        Diff<Double> diff_1_double_var = new Diff<>(double1,v1_double);
        assertTrue(diff_2_var_vard.satisfied());
        assertFalse(diff_1_var_vard.satisfied());
        assertTrue(diff_1_double_var.satisfied());
        assertFalse(diff_1_var_double.satisfied());

        //*A modifier voir avec le groupe*//
        //erreur donc il faut rajouter une condition.
        /*VarGetter v3_int = new VarGetter(10);
        VarGetter v3_double = new VarGetter(10.0);
        VarGetter v3_float = new VarGetter(10.0f);
        Diff diff_type_var = new Diff(v3_int,v3_double);
        Diff diff_type = new Diff(5,10.0f);
        assertFalse(diff_type.satisfied(objectMap));
        */
    }
}
