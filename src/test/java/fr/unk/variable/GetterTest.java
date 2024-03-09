package fr.unk.variable;

import fr.unk.variable.numvar.CSPInt;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GetterTest {

    @Test
    void isVar() {
        Random random = new Random();
        int int1 = random.nextInt();
        float float1 = random.nextFloat();
        double double1 = random.nextDouble();
        Getter<String> getter = new Getter<>("Test");
        assertFalse(getter.isVar());
        Getter<Integer> getter0 = new Getter<>(int1);
        assertFalse(getter0.isVar());
        Getter<Double> getter01 = new Getter<>(double1);
        assertFalse(getter01.isVar());
        Getter<Float> getter02 = new Getter<>(float1);
        assertFalse(getter02.isVar());
        Getter<String> getter1 = new Getter<>(null);
        assertFalse(getter1.isVar());
        Getter<Integer> getter2 = new CSPInt("testVar");
        assertTrue(getter2.isVar());
    }

    @Test
    void getValue() {
        Random random = new Random();
        int int1 = random.nextInt();
        float float1 = random.nextFloat();
        double double1 = random.nextDouble();
        Getter<Integer> getter = new Getter<>(int1);
        Getter<Float> getter1 = new Getter<>(float1);
        Getter<Double> getter2 = new Getter<>(double1);
        assertEquals(int1, getter.getValue());
        assertEquals(float1, getter1.getValue());
        assertEquals(double1, getter2.getValue());
    }
}