package fr.unk.variable;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class VarGetterTest {

    @Test
    void isVar() {
        Random random = new Random();
        int int1 = random.nextInt();
        float float1 = random.nextFloat();
        double double1 = random.nextDouble();
        VarGetter<String> varGetter = new VarGetter<>("Test");
        assertFalse(varGetter.isVar());
        VarGetter<Integer> varGetter0 = new VarGetter<>(int1);
        assertFalse(varGetter0.isVar());
        VarGetter<Double> varGetter01 = new VarGetter<>(double1);
        assertFalse(varGetter01.isVar());
        VarGetter<Float> varGetter02 = new VarGetter<>(float1);
        assertFalse(varGetter02.isVar());
        VarGetter<String> varGetter1 = new VarGetter<>(null);
        assertTrue(varGetter1.isVar());
    }

    @Test
    void getValue() {
        Random random = new Random();
        int int1 = random.nextInt();
        float float1 = random.nextFloat();
        double double1 = random.nextDouble();
        VarGetter<Integer> varGetter = new VarGetter<>(int1);
        VarGetter<Float> varGetter1 = new VarGetter<>(float1);
        VarGetter<Double> varGetter2 = new VarGetter<>(double1);
        Map<String, Object> objectMap = new HashMap<>();
        assertEquals(int1,varGetter.getValue(objectMap));
        assertEquals(float1,varGetter1.getValue(objectMap));
        assertEquals(double1,varGetter2.getValue(objectMap));
    }
}