package fr.unk.contrainte.vc;

import fr.unk.variable.VarGetter;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.WeakHashMap;

import static org.junit.jupiter.api.Assertions.*;

class InfTest {

    @Test
    void testsatisfied() {
        Random random = new Random();
        int int1 = random.nextInt();
        int int2;
        do {
            int2 = random.nextInt();
        } while (int2 > int1);
        Map<String,Object> objectMap = new HashMap<>();
        VarGetter v0_int = new VarGetter(int1);
        VarGetter v1_int = new VarGetter(int2);
        Inf inf_2_var_var = new Inf(v1_int,v0_int,false);
        Inf inf_1_var_var = new Inf(v0_int,v1_int,false);
        Inf inf_1_var_int = new Inf(v1_int,int1,false);
        Inf inf_1_int_var = new Inf(int1,v1_int,false);
        assertTrue(inf_2_var_var.satisfied(objectMap));
        assertFalse(inf_1_var_var.satisfied(objectMap));
        assertFalse(inf_1_int_var.satisfied(objectMap));
        assertTrue(inf_1_var_int.satisfied(objectMap));

        float float1 = random.nextFloat();
        float float2;
        do {
            float2 = random.nextFloat();
        } while (float2 > float1);
        VarGetter v0_float = new VarGetter(float1);
        VarGetter v1_float = new VarGetter(float2);
        Inf inf_2_var_varf = new Inf(v1_float,v0_float,false);
        Inf inf_1_var_varf = new Inf(v0_float,v1_float,false);
        Inf inf_1_var_float = new Inf(v1_float,float1,false);
        Inf inf_1_float_var = new Inf(float1,v1_float,false);
        assertTrue(inf_2_var_varf.satisfied(objectMap));
        assertFalse(inf_1_var_varf.satisfied(objectMap));
        assertFalse(inf_1_float_var.satisfied(objectMap));
        assertTrue(inf_1_var_float.satisfied(objectMap));

        double double1 = random.nextDouble();
        double double2;
        do {
            double2 = random.nextDouble();
        } while (double2 > double1);
        VarGetter v0_double = new VarGetter(double1);
        VarGetter v1_double = new VarGetter(double2);
        Inf inf_2_var_vard = new Inf(v1_double,v0_double,false);
        Inf inf_1_var_vard = new Inf(v0_double,v1_double,false);
        Inf inf_1_var_double = new Inf(v1_double,double1,false);
        Inf inf_1_double_var = new Inf(double1,v1_double,false);
        assertTrue(inf_2_var_vard.satisfied(objectMap));
        assertFalse(inf_1_var_vard.satisfied(objectMap));
        assertFalse(inf_1_double_var.satisfied(objectMap));
        assertTrue(inf_1_var_double.satisfied(objectMap));

    }
}