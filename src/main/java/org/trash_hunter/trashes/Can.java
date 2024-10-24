package org.trash_hunter.trashes;

public class Can extends Trash {
    public Can(double x,double y){
        super(x, y);
        super.nbPoints= 1;
        super.name="Can";
        super.time = 1;          // récupération quasi instantanée
    }
}
