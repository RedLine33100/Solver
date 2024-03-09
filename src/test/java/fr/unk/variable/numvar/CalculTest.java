package fr.unk.variable.numvar;

import fr.unk.variable.VarGetter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CalculTest {

    @Test
    void testadd() {
        Calcul<Integer> calcul = new Calcul<>("myVar") {
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

            @Override
            Calcul<Integer> newCopy(Operation<Integer> addedOperation) {
                return this;
            }
        };
        calcul.setValue(5);
        calcul = calcul.add(new VarGetter<>(5));
        assertNotNull(calcul.getValue());
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