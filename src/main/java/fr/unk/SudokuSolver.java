package fr.unk;

import fr.unk.contrainte.nc.ListDiff;
import fr.unk.domaine.number.IntDomain;
import fr.unk.variable.Getter;
import fr.unk.variable.numvar.CSPInt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SudokuSolver {

    public static void main(String[] args){

        CSPSolver<Integer> cspSolver = new CSPSolver<>();

        CSPInt[][] cspIntArray = new CSPInt[9][9];

        IntDomain intDomain = new IntDomain(1, 9, 1);

        for (int i = 0; i < 9; i++){
            List<Getter<Integer>> cspIntList = new ArrayList<>();
            for (int j = 0; j < 9; j++){
                CSPInt cspInt = new CSPInt("x "+i+" y "+j);
                cspSolver.addUnknownVariable(cspInt, intDomain);

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
