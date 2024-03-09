package fr.unk.variable.numvar;

import fr.unk.variable.VarGetter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CSPIntTest {

    @Test
    void testadd() {
        CSPInt cspInt = new CSPInt("myVar") {};
        cspInt.setValue(10);
        cspInt = cspInt.add(new VarGetter<>(5));
        assertEquals(15, cspInt.getValue());
    }

    @Test
    void testremove() {
        CSPInt cspInt = new CSPInt("myVar") {};
        cspInt.setValue(20);
        cspInt = cspInt.remove(new VarGetter<>(5));
        assertEquals(15, cspInt.getValue());
    }

    @Test
    void testdivide() {
        CSPInt cspInt = new CSPInt("myVar") {};
        cspInt.setValue(20);
        cspInt = cspInt.divide(new VarGetter<>(5));
        assertEquals(4, cspInt.getValue());
    }
    @Test
    void testmultiply() {
        CSPInt cspInt = new CSPInt("myVar") {};
        cspInt.setValue(20);
        cspInt = cspInt.multiply(new VarGetter<>(5));
        assertEquals(100, cspInt.getValue());
    }


    @Test
    void testmodulo() {
        CSPInt cspInt = new CSPInt("myVar") {};
        cspInt.setValue(24);
        cspInt = cspInt.modulo(new VarGetter<>(5));
        assertEquals(4, cspInt.getValue());
    }

    @Test
    void testgetValue() {
        CSPInt cspInt = new CSPInt("myVar") {};
        cspInt.setValue(24);
        assertEquals(24, cspInt.getValue());
    }
}