package fr.unk.variable;

import fr.unk.utils.Pair;

import java.util.*;
import java.util.function.BinaryOperator;

public class Calcul<T extends Number> implements Variable<T> {

    final List<Pair<BinaryOperator<T>, Variable<T>>> operatorList = new ArrayList<>();
    final Variable<T> startVal;

    public Calcul(Variable<T> startVal){
        this.startVal = startVal;
    }

    public void addCalcul(BinaryOperator<T> binaryOperator, Variable<T> variable){
        this.operatorList.add(new Pair<>(binaryOperator, variable));
    }

    public T getValue(Map<String, Object> maps){

        T curVal = this.startVal.getValue(maps);

        for (Pair<BinaryOperator<T>, Variable<T>> pair : this.operatorList) {
            curVal = pair.getL().apply(curVal, pair.getR().getValue(maps));
        }

        return curVal;

    }

}
