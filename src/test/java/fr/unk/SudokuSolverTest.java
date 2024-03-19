package fr.unk;

import fr.unk.contrainte.nc.ListDiff;
import fr.unk.domaine.number.IntDomain;
import fr.unk.variable.Getter;
import fr.unk.variable.numvar.CSPInt;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SudokuSolverTest {

    @Test
    public void main(){

        CSPSolver<Integer> cspSolver = new CSPSolver<>();

        CSPInt[][] cspIntArray = new CSPInt[9][9];

        for (int i = 0; i < 9; i++){
            List<Getter<Integer>> cspIntList = new ArrayList<>();
            for (int j = 0; j < 9; j++){
                CSPInt cspInt = new CSPInt("x "+i+" y "+j);
                cspSolver.addUnknownVariable(cspInt, new IntDomain(1, 9, 1));

                cspIntArray[i][j] = cspInt;
                cspIntList.add(cspInt);
            }
            cspSolver.addConstraint(new ListDiff<>(cspIntList));
        }

        for (int i = 0; i < 9; i++){
            List<Getter<Integer>> cspIntList = new ArrayList<>();
            for (int j = 0; j < 9; j++){
                cspIntList.add(cspIntArray[j][i]);
            }
            cspSolver.addConstraint(new ListDiff<>(cspIntList));
        }


        for(int blockX = 0; blockX<3; blockX++){
            for(int blockY = 0; blockY<3; blockY++){
                List<Getter<Integer>> blockCst = new ArrayList<>();

                for (int x = blockX*3; x<(blockX+1)*3; x++){
                    for (int y = blockY*3; y<(blockY+1)*3; y++){
                        blockCst.add(cspIntArray[x][y]);
                    }
                }
                cspSolver.addConstraint(new ListDiff<>(blockCst));
            }
        }

        cspIntArray[7][1].setValue(1);
        cspIntArray[6][1].setValue(2);
        cspIntArray[3][2].setValue(3);
        cspIntArray[7][4].setValue(4);
        cspIntArray[6][8].setValue(5);
        cspIntArray[4][5].setValue(6);
        cspIntArray[1][1].setValue(6);

        Map<String, Integer> map = cspSolver.trySolve();

        System.out.println(" | --- | --- | --- |");
        for(int i = 0; i<9; i++){
            for(int j = 0; j<9; j++){
                if((j)%3 == 0)
                    System.out.print(" | ");
                System.out.print(cspIntArray[i][j].getValue());
            }
            System.out.println(" |");
            if((i+1)%3 == 0)
                System.out.println(" | --- | --- | --- |");
        }

    }

}
