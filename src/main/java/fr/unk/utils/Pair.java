package fr.unk.utils;

public class Pair <L,R>{

    final L l;
    final R r;

    public Pair(L l, R r){
        this.l = l;
        this.r = r;
    }

    public L getL() {
        return l;
    }

    public R getR() {
        return r;
    }

}
