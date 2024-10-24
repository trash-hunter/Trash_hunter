package org.trash_hunter.trashes;

public class OilContainer extends Trash {
    public OilContainer(double x, double y){
        super(x, y);
        super.nbPoints = 10;
        super.name ="OilContainer";
        super.time = 5000;       //temps de récupération en ms
    }
}
