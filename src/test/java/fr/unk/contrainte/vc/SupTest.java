package fr.unk.contrainte.vc;

import fr.unk.variable.VarGetter;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SupTest {

    @Test
    void testsatisfied() {
        Random random = new Random();
        int int1 = random.nextInt();
        int int2;
        do {
            int2 = random.nextInt();
        } while (int2 < int1);
        Map<String,Object> objectMap = new HashMap<>();
        VarGetter v0_int = new VarGetter(int1);
        VarGetter v1_int = new VarGetter(int2);
        Sup sup_2_var_var = new Sup(v1_int,v0_int,false);
        Sup sup_1_var_var = new Sup(v0_int,v1_int,false);
        Sup sup_1_var_int = new Sup(v1_int,int1,false);
        Sup sup_1_int_var = new Sup(int1,v1_int,false);
        assertTrue(sup_2_var_var.satisfied(objectMap));
        assertFalse(sup_1_var_var.satisfied(objectMap));
        assertFalse(sup_1_int_var.satisfied(objectMap));
        assertTrue(sup_1_var_int.satisfied(objectMap));

        float float1 = random.nextFloat();
        float float2;
        do {
            float2 = random.nextFloat();
        } while (float2 < float1);
        VarGetter v0_float = new VarGetter(float1);
        VarGetter v1_float = new VarGetter(float2);
        Sup sup_2_var_varf = new Sup(v1_float,v0_float,false);
        Sup sup_1_var_varf = new Sup(v0_float,v1_float,false);
        Sup sup_1_var_float = new Sup(v1_float,float1,false);
        Sup sup_1_float_var = new Sup(float1,v1_float,false);
        assertTrue(sup_2_var_varf.satisfied(objectMap));
        assertFalse(sup_1_var_varf.satisfied(objectMap));
        assertFalse(sup_1_float_var.satisfied(objectMap));
        assertTrue(sup_1_var_float.satisfied(objectMap));

        double double1 = random.nextDouble();
        double double2;
        do {
            double2 = random.nextDouble();
        } while (double2 < double1);
        VarGetter v0_double = new VarGetter(double1);
        VarGetter v1_double = new VarGetter(double2);
        Sup sup_2_var_vard = new Sup(v1_double,v0_double,false);
        Sup sup_1_var_vard = new Sup(v0_double,v1_double,false);
        Sup sup_1_var_double = new Sup(v1_double,double1,false);
        Sup sup_1_double_var = new Sup(double1,v1_double,false);
        assertTrue(sup_2_var_vard.satisfied(objectMap));
        assertFalse(sup_1_var_vard.satisfied(objectMap));
        assertFalse(sup_1_double_var.satisfied(objectMap));
        assertTrue(sup_1_var_double.satisfied(objectMap));

    }
}