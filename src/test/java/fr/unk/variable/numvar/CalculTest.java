package fr.unk.variable.numvar;

import fr.unk.variable.Getter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CalculTest {

    @Test
    void testadd() {
        Calcul<Integer> calcul = new Calcul<>("myVar") {
            @Override
            Calcul<Integer> add(Getter<Integer> varGetter) {
                return this;
            }

            @Override
            Calcul<Integer> remove(Getter<Integer> varGetter) {
                return this;
            }

            @Override
            public Calcul<Integer> divide(Getter<Integer> variable) {
                return this;
            }

            @Override
            public Calcul<Integer> multiply(Getter<Integer> variable) {
                return this;
            }

            @Override
            public Calcul<Integer> modulo(Getter<Integer> variable) {
                return this;
            }

            @Override
            Calcul<Integer> newCopy(Operation<Integer> addedOperation) {
                return this;
            }
        };
        calcul.setValue(5);
        calcul = calcul.add(new Getter<>(5));
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