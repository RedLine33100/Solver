package fr.unk.domaine.number;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DoubleDomainTest {
    @Test
    void testgetPossibility() {
        Random random = new Random();
        double double1;
        double double2;
        double double3 = 0.0;
        double double4 = 100.0;
        double double5;
        do {
            double1 = random.nextInt();
        } while (double1 < double3);
        do {
            double2 = random.nextInt();
        } while (double2 > double4);
        do {
            double5 = random.nextInt();
        } while (double5 > double4 || double5 < double3);
        DoubleDomain doubleDomain = new DoubleDomain(double3,double4,1.0);
        List<Double> list = doubleDomain.getPossibility();
        assertEquals(((double4 - double3)) + 1, list.size());
        assertFalse(list.contains(double1));
        assertFalse(list.contains(double2));
        assertTrue(list.contains(double3));
        assertTrue(list.contains(double4));
        assertTrue(list.contains(double5));
    }
}