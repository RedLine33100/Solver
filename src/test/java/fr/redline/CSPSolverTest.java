package fr.redline;

import fr.redline.contrainte.nc.ListDiff;
import fr.redline.domaine.number.IntDomain;
import fr.redline.value.Variable;
import org.junit.jupiter.api.Test;

class CSPSolverTest {

    @Test
    void trySolve() {

        // Exemple de grille Sudoku (0 = case vide)
        int[][] grid = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        CSPSolver<Integer> model = new CSPSolver<>();
        Variable<Integer>[][] vars = new Variable[9][9];

        // Création des variables (1..9)
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] != 0) {
                    vars[i][j] = new Variable<>("cell_" + i + "_" + j, grid[i][j]);
                } else {
                    vars[i][j] = new Variable<>("cell_" + i + "_" + j, new IntDomain(1, 9, 1));
                }
            }
        }

        // Contraintes de lignes et colonnes
        for (int i = 0; i < 9; i++) {
            model.addConstraint(new ListDiff<>(vars[i]));
            Variable<Integer>[] col = new Variable[9];
            for (int j = 0; j < 9; j++) {
                col[j] = vars[j][i];
            }

            model.addConstraint(new ListDiff<>(col)); // Colonne
        }

        // Contraintes des blocs 3x3
        for (int blockRow = 0; blockRow < 3; blockRow++) {
            for (int blockCol = 0; blockCol < 3; blockCol++) {
                Variable<Integer>[] block = new Variable[9];
                int idx = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        block[idx++] = vars[blockRow * 3 + i][blockCol * 3 + j];
                    }
                }
                model.addConstraint(new ListDiff<>(block));
            }
        }

        // Résolution
        if (model.trySolve()) {
            System.out.println("Solution trouvée :");
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(vars[i][j].getValue() + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("Pas de solution.");
        }

    }
}