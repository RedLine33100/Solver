package fr.unk.variable.numvar;

import fr.unk.variable.VarGetter;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CalculTest {

    @Test
    void testadd() {
        Calcul<Integer> calcul = new Calcul<Integer>("myVar", Integer.class){
            @Override
            Calcul<Integer> add(VarGetter<Integer> varGetter) {
                return this;
            }

            @Override
            Calcul<Integer> remove(VarGetter<Integer> varGetter) {
                return this;
            }

            @Override
            public Calcul<Integer> divide(VarGetter<Integer> variable) {
                return this;
            }

            @Override
            public Calcul<Integer> multiply(VarGetter<Integer> variable) {
                return this;
            }

            @Override
            public Calcul<Integer> modulo(VarGetter<Integer> variable) {
                return this;
            }
        };
        Map<String,Object> objectMap = new HashMap<>();
        calcul = calcul.add(new VarGetter<>(5));
        objectMap.put("myVar",10);
        assertNotNull(calcul.getValue(objectMap));
    }

    @Test
    void testremove() {
    }

    @Test
    void testdivide() {
    }

    @Test
    void testmultiply() {
    }

    @Test
    void testmodulo() {
    }
}