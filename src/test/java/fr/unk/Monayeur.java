package fr.unk;

import fr.unk.contrainte.vc.Equals;
import fr.unk.domaine.number.IntDomain;
import fr.unk.variable.numvar.CSPInt;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class Monayeur {

    @Test
    public void main() {

        double price = 10.0;
        double enteredAmount = 15.0D;

        int targetPrice = (int) (price * 10);
        int enteredMoney = (int) (enteredAmount * 10);
        int neededMoney = enteredMoney - targetPrice;
        System.out.println("prix :" + targetPrice);
        System.out.println("argent entrée :" + enteredMoney);
        System.out.println("rendu :" + neededMoney);

        CSPSolver<Integer> cspSolver = new CSPSolver<>();

        CSPInt[] coins = new CSPInt[5];
        for (int i = 0; i < 5; i++) {
            CSPInt coin = new CSPInt("coin_" + i);
            cspSolver.addUnknownVariable(coin, new IntDomain(0, 1000, 1));
            coins[i] = coin;
        }


        int[] coinValues = {1, 2, 5, 10, 20};

        for (int i = 0; i < 5; i++) {
            //cspSolver.addConstraint(new Equals<>(coins[i].multiply(coinValues[i]), neededMoney));
            cspSolver.addConstraint(new Equals<>(coins[i], coinValues[i]));
        }

        Map<String, Integer> solution = cspSolver.trySolve();


        if (solution != null) {
            System.out.println("Solution:");
            for (int i = 0; i < 5; i++) {
                int numberOfCoins = solution.get("coin_" + i);
                System.out.println("Coin " + i + ": " + numberOfCoins);
                System.out.println(coins[i].getValue());
            }
        } else {
            System.out.println("No solution found.");
        }

    }

}
