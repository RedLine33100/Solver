package fr.unk.util;

/**
 * Permit to stock two value of different type
 * @param <L> first value type
 * @param <R> second value type
 */
public class Pair <L,R>{

    final L l;
    final R r;

    public Pair(L l, R r){
        this.l = l;
        this.r = r;
    }

    public R getR() {
        return r;
    }

    public L getL() {
        return l;
    }

    @Override
    public String toString(){
        return "L: "+l+" R: "+r;
    }

}
