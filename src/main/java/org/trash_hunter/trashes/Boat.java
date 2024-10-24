package org.trash_hunter.trashes;

public class Boat extends Trash{
    public Boat(double x,double y){
        super(x, y);
        super.nbPoints = 10;
        super.name = "Boat";
        super.time = 5000;        //temps de récupération en ms
    }
}
