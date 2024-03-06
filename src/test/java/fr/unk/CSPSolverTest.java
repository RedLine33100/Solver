package fr.unk;

import fr.unk.contrainte.Constraint;
import fr.unk.contrainte.nc.ListEquals;
import fr.unk.contrainte.vc.Equals;
import fr.unk.domaine.number.IntDomain;
import fr.unk.variable.VarGetter;
import fr.unk.variable.Variable;
import fr.unk.variable.numvar.CSPInt;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CSPSolverTest {
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        System.out.println ("Avant toutes les executions");
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        System.out.println ("Apres toutes les executions");
    }

    @BeforeEach
    void setUp() throws Exception {
        System.out.println ("avant une fonction sous test");
    }

    @AfterEach
    void tearDown() throws Exception {
        System.out.println ("apres une fonction sous test");
    }

    @Test
    void testSolveStructurel() {
        // Cas où les variables inconnues et les contraintes sont vides : vérifie si la méthode solve() renvoie null dans ce cas
        CSPSolver<Integer> solver1 = new CSPSolver<>();
        assertNull(solver1.solve(new ArrayList<> (), new HashMap<> ()));

        // Cas où une seule variable inconnue sans contrainte : vérifie si la méthode solve() renvoie la bonne solution dans ce cas
        Variable<Integer> var1 = new Variable<>("x", Integer.class);
        IntDomain domain1 = new IntDomain(0, 10, 1);
        solver1.addConstraint (new Equals<> (var1, 5));
        solver1.addUnknownVariable(var1, domain1);
        Map<String, Object> solution1 = solver1.trySolve();
        assertNotNull(solution1);
        assertEquals(1, solution1.size());
        assertEquals(5, solution1.get("x"));


        /*
        // Cas où une variable inconnue avec une contrainte : vérifie si la méthode solve() renvoie null dans ce cas
        Variable<Integer> var3 = new Variable<>("z", Integer.class);
        IntDomain domain3 = new IntDomain(0, 10, 1);
        solver1.addUnknownVariable(var3, domain3);
        ListEquals<Integer> constraint1 = new ListEquals<> (var1, var3);
        solver1.addConstraint(constraint1);
        assertNull(solver1.trySolve());
         */

        /*
        // Cas où une variable inconnue avec une contrainte et une valeur incompatible : vérifie si la méthode solve() renvoie null dans ce cas
        Variable<Integer> var3 = new Variable<>("z", Integer.class);
        IntDomain domain3 = new IntDomain(0, 10, 1);
        var3 = new Variable<>("z", Integer.class);
        solver1.addUnknownVariable(var3, domain3);
        assertNull(solver1.trySolve());

        // Cas où une variable inconnue avec une contrainte et une valeur compatible : vérifie si la méthode solve() renvoie la bonne solution dans ce cas
        var3 = new Variable<>("z", Integer.class);
        solver1.addUnknownVariable(var3, domain3);
        Map<String, Object> solution3 = solver1.trySolve();
        assertNotNull(solution3);
        assertEquals(3, solution3.size());
        assertEquals(5, solution3.get("z"));
         */
    }

    @Test
    void testaddConstraint(){
        CSPSolver<Integer> solver = new CSPSolver<>();
        Constraint constraint = new Constraint() {
            @Override
            public boolean satisfied(Map<String, Object> objectMap) {
                return false;
            }
        };
        solver.addConstraint(constraint);
        assertEquals(1, solver.constraintList.size());
        assertEquals(constraint, solver.constraintList.get(0));
    }
    @Test
    public void testAddUnknownVariable() {
        CSPSolver<Integer> solver = new CSPSolver<>();

        Variable<Integer> variable = new Variable<>("myvar",Integer.class);
        IntDomain domain = new IntDomain(1,100,1);
        solver.addUnknownVariable(variable, domain);
        assertEquals(1, solver.uknVariables.size());
        assertEquals(variable, solver.uknVariables.get(0).getL());
        assertEquals(domain, solver.uknVariables.get(0).getR());
    }

    @Test
    void trySolve() {

        CSPSolver<Integer> cspSolver = new CSPSolver<>();
        CSPInt cspInt = new CSPInt("fe");
        CSPInt cspInt2 = new CSPInt("de");

        cspSolver.addConstraint(new Equals<>(cspInt, 3));
        cspSolver.addConstraint(new Equals<>(cspInt2.add(2), cspInt));

        cspSolver.addUnknownVariable(cspInt, new IntDomain(0,3,1));
        cspSolver.addUnknownVariable(cspInt2, new IntDomain(0,3,1));
        Map<String, Object> result = cspSolver.trySolve();

        if(result == null){
            System.out.println("None");
            return;
        }

        for(Map.Entry<String, Object> entry : result.entrySet())
            System.out.println(entry.getKey()+": "+entry.getValue());

    }
}