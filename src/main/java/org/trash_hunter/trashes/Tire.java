package org.trash_hunter.trashes;

public class Tire extends Trash{
    public Tire(double x,double y){
        super(x, y);
        super.nbPoints= 5;
        super.name="tire";
        super.time = 5000;       //temps de récupération en ms
    }
}
