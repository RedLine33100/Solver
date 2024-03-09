package fr.unk.variable.numvar;

import fr.unk.variable.VarGetter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CSPDoubleTest {

    @Test
    void testadd() {
        CSPDouble cspDouble = new CSPDouble("myVar");
        cspDouble.setValue(10.0);
        cspDouble = cspDouble.add(new VarGetter<>(5.0));
        assertEquals(15, cspDouble.getValue());
    }

    @Test
    void testremove() {
        CSPDouble cspDouble = new CSPDouble("myVar");
        cspDouble.setValue(10.5);
        cspDouble = cspDouble.remove(new VarGetter<>(5.5));
        assertEquals(5, cspDouble.getValue());
    }

    @Test
    void testdivide() {
        CSPDouble cspDouble = new CSPDouble("myVar") {};
        cspDouble.setValue(11.0);
        cspDouble = cspDouble.divide(new VarGetter<>(5.5));
        assertEquals(2, cspDouble.getValue());
    }

    @Test
    void testmultiply() {
        CSPDouble cspDouble = new CSPDouble("myVar") {};
        cspDouble.setValue(2.0);
        cspDouble = cspDouble.multiply(new VarGetter<>(5.5));
        assertEquals(11, cspDouble.getValue());
    }

    @Test
    void testmodulo() {
        CSPDouble cspDouble = new CSPDouble("myVar") {};
        cspDouble.setValue(21.5);
        cspDouble = cspDouble.modulo(new VarGetter<>(5.0));
        assertEquals(1.5, cspDouble.getValue());
    }

    @Test
    void testgetValue() {
        CSPDouble cspDouble = new CSPDouble("myVar") {};
        cspDouble.setValue(2.0);
        assertEquals(2.0, cspDouble.getValue());
    }
}