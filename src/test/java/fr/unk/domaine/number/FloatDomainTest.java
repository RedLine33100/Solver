package fr.unk.domaine.number;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class FloatDomainTest {

    @Test
    void testgetPossibility() {
        Random random = new Random();
        float float1;
        float float2;
        float float3 = 0.0f;
        float float4 = 100.0f;
        float float5;
        do {
            float1 = random.nextFloat();
        } while (float1 < float3);
        do {
            float2 = random.nextFloat();
        } while (float2 > float4);
        do {
            float5 = random.nextFloat();
        } while (float5 > float4 || float5 < float3 || float5 % 1.0f != 0);
        FloatDomain floatDomain = new FloatDomain(float3,float4,1.0f);
        List<Float> list = floatDomain.getPossibility();
        assertEquals(((float4-float3)/1.0f)+1, list.size());
        assertFalse(list.contains(float1));
        assertFalse(list.contains(float2));
        assertTrue(list.contains(float3));
        assertTrue(list.contains(float4));
        assertTrue(list.contains(float5));
    }
}