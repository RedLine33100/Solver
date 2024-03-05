package fr.unk.contrainte.vc;

import fr.unk.variable.VarGetter;
import fr.unk.variable.Variable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.engine.Constants;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DiffTest {

    @Test
    void testsatisfied() {
        Random random = new Random();
        int int1 = random.nextInt();
        int int2;
        do {
            int2 = random.nextInt();
        } while (int2 == int1);
        Map<String,Object> objectMap = new HashMap<>();
        VarGetter v0_int = new VarGetter(int1);
        VarGetter v1_int = new VarGetter(int2);
        VarGetter v2_int = new VarGetter(int1);
        Diff diff_2_var_var = new Diff(v1_int,v0_int);
        Diff diff_1_var_var = new Diff(v2_int,v0_int);
        Diff diff_1_var_int = new Diff(v0_int,int1);
        Diff diff_1_int_var = new Diff(int1,v1_int);
        assertTrue(diff_2_var_var.satisfied(objectMap));
        assertFalse(diff_1_var_var.satisfied(objectMap));
        assertTrue(diff_1_int_var.satisfied(objectMap));
        assertFalse(diff_1_var_int.satisfied(objectMap));

        float float1 = random.nextFloat();
        float float2;
        do {
            float2 = random.nextFloat();
        } while (float2 == float1);
        VarGetter v0_float = new VarGetter(float1);
        VarGetter v1_float = new VarGetter(float2);
        VarGetter v2_float = new VarGetter(float1);
        Diff diff_2_var_varf = new Diff(v1_float,v0_float);
        Diff diff_1_var_varf = new Diff(v2_float,v0_float);
        Diff diff_1_var_float = new Diff(v0_float,float1);
        Diff diff_1_float_var = new Diff(float1,v1_float);
        assertTrue(diff_2_var_varf.satisfied(objectMap));
        assertFalse(diff_1_var_varf.satisfied(objectMap));
        assertTrue(diff_1_float_var.satisfied(objectMap));
        assertFalse(diff_1_var_float.satisfied(objectMap));

        double double1 = random.nextDouble();
        double double2;
        do {
            double2 = random.nextDouble();
        } while (double2 == double1);
        VarGetter v0_double = new VarGetter(double1);
        VarGetter v1_double = new VarGetter(double2);
        VarGetter v2_double = new VarGetter(double1);
        Diff diff_2_var_vard = new Diff(v1_double,v0_double);
        Diff diff_1_var_vard = new Diff(v2_double,v0_double);
        Diff diff_1_var_double = new Diff(v0_double,double1);
        Diff diff_1_double_var = new Diff(double1,v1_double);
        assertTrue(diff_2_var_vard.satisfied(objectMap));
        assertFalse(diff_1_var_vard.satisfied(objectMap));
        assertTrue(diff_1_double_var.satisfied(objectMap));
        assertFalse(diff_1_var_double.satisfied(objectMap));

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
