package fr.redline.value.constraint;

import fr.redline.contrainte.nc.ListEquals;
import fr.redline.contrainte.reduction.ReductionResult;
import fr.redline.domaine.number.FastIntDomain;
import fr.redline.domaine.number.IntDomain;
import fr.redline.value.Variable;
import fr.redline.value.numvar.SolverInt;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListEqualsTest {

    @org.junit.jupiter.api.Test
    void testListEquals() {

        Variable<Integer>[] varArray = new Variable[10];
        int uknVarCount = 2;

        for (int i = 1; i < varArray.length - 1; i++) {
            varArray[i] = new Variable<>("var_" + i, new FastIntDomain(0, 1000));
            uknVarCount++;
        }

        SolverInt set = new SolverInt("set", 10);

        varArray[0] = set;

        SolverInt search = new SolverInt("seek", new FastIntDomain(0, 1000));

        varArray[9] = search.add(9);

        ListEquals<Integer> listEquals = new ListEquals<>(varArray);

        listEquals.reduce(new ReductionResult<>(uknVarCount));

        assertEquals(1, search.getValue());

        assertEquals(10, varArray[1].getValue());

    }
}
