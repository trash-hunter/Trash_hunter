package org.trash_hunter.trashes;

public class PlasticBag extends Trash {
    public PlasticBag (double x,double y){
        super(x, y);
        super.nbPoints= 5;
        super.name="Plastic Bag";
        super.time = 2000;       //temps de récupération en ms
    }
}
