package fr.unk.utils;

import fr.unk.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PairTest <L,R>{
//Voir si dans paire il peut y avoir des Opérateur//
    @Test
    void testgetL() {
        Random random = new Random();
        int L_int = random.nextInt();
        int R_int = random.nextInt();
        double L_double = random.nextDouble();
        double R_double = random.nextDouble();
        float L_float = random.nextFloat();
        float R_float = random.nextFloat();
        //objectif test que on arrive bien a récupérer les bonnes valeur des 3 domaines est que les valeurs L et R n'ont pas besoin d'être dans le meme domaine
        Pair<L, R> double_pair = (Pair<L, R>) new Pair<>(L_double, R_float);
        Pair<L, R> float_pair = (Pair<L, R>) new Pair<>(L_float, R_int);
        Pair<L, R> int_pair = (Pair<L, R>) new Pair<>(L_int, R_double);
        L doubleactuel = double_pair.getL();
        L floatactuel = float_pair.getL();
        L intactuel = int_pair.getL();
        assertNotNull(doubleactuel);
        assertNotNull(floatactuel);
        assertNotNull(intactuel);
        assertEquals(doubleactuel,L_double);
        assertEquals(floatactuel,L_float);
        assertEquals(intactuel,L_int);
    }

    @Test
    void testgetR() {
        Random random = new Random();
        int L_int = random.nextInt();
        int R_int = random.nextInt();
        double L_double = random.nextDouble();
        double R_double = random.nextDouble();
        float L_float = random.nextFloat();
        float R_float = random.nextFloat();

        Pair<L, R> float_pair = (Pair<L, R>) new Pair<>(L_double, R_float);
        Pair<L, R> int_pair = (Pair<L, R>) new Pair<>(L_float, R_int);
        Pair<L, R> double_pair = (Pair<L, R>) new Pair<>(L_int, R_double);

        R doubleactuel = double_pair.getR();
        R floatactuel = float_pair.getR();
        R intactuel = int_pair.getR();

        assertNotNull(doubleactuel);
        assertNotNull(floatactuel);
        assertNotNull(intactuel);
        assertEquals(doubleactuel,R_double);
        assertEquals(floatactuel,R_float);
        assertEquals(intactuel,R_int);
    }
}