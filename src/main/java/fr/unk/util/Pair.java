package fr.unk.util;

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
