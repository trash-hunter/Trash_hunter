package org.trash_hunter.trashes;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Bottle extends Trash {
    public Bottle(double x, double y){
        super(x, y);
        super.nbPoints= 1;
        super.name ="Bottle";
        super.time = 1;           //récupération quasi instantanée
        try {
            super.sprite = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("bouteille20x20.jpg")));
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors du chargement de l'image : " + e.getMessage(), e);
        }
        super.width=super.sprite.getWidth();
        super.height=super.sprite.getHeight();

    }
}
