package fr.unk.domaine.number;

import fr.unk.domaine.Domain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class IntDomain implements Domain<Integer> {

    final int min, max, jump;

    public IntDomain(int min, int max, int jump){
        this.min = min;
        this.max = max;
        this.jump = jump;
    }

    @Override
    public List<Integer> getPossibility() {
        List<Integer> doubles = new ArrayList<>();
        if (jump == 0){
            return null;
        }
        int current = min;
        while (current <= max){
            doubles.add(current);
            current += this.jump;
        }
        return doubles;
    }
}
