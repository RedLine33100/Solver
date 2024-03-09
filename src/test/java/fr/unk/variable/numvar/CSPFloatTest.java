package fr.unk.variable.numvar;

import fr.unk.variable.VarGetter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CSPFloatTest {

    @Test
    void testadd() {
        CSPFloat cspFloat = new CSPFloat("myVar") {};
        cspFloat.setValue(10.5f);
        cspFloat = cspFloat.add(new VarGetter<>(5.2f));
        assertEquals(15.7f, cspFloat.getValue());
    }

    @Test
    void testremove() {
        CSPFloat cspFloat = new CSPFloat("myVar") {};
        cspFloat.setValue(20.5f);
        cspFloat = cspFloat.remove(new VarGetter<>(5.2f));
        assertEquals(15.3f, cspFloat.getValue());
    }

    @Test
    void testdivide() {
        CSPFloat cspFloat = new CSPFloat("myVar") {};
        cspFloat.setValue(20.4f);
        cspFloat = cspFloat.divide(new VarGetter<>(5.1f));
        assertEquals(4.0f, cspFloat.getValue());
    }

    @Test
    void testmultiply() {
        CSPFloat cspFloat = new CSPFloat("myVar") {};
        cspFloat.setValue(20.4f);
        cspFloat = cspFloat.multiply(new VarGetter<>(5.0f));
        assertEquals(102.0f, cspFloat.getValue());
    }

    @Test
    void testmodulo() {
        CSPFloat cspFloat = new CSPFloat("myVar") {};
        cspFloat.setValue(21.0f);
        cspFloat = cspFloat.modulo(new VarGetter<>(5.0f));
        //quand il y a des chiffres aprés la virgule cela ne mais pas les valeurs exacte exemple si le resultat attendu est 0.4 cela retourne 0.38668
        assertEquals(1.0f, cspFloat.getValue());
    }

    @Test
    void testgetValue() {
        CSPFloat cspFloat = new CSPFloat("myVar") {};
        cspFloat.setValue(21.586f);
        assertEquals(21.586f, cspFloat.getValue());
    }
}