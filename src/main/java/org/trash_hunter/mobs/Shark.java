package org.trash_hunter.mobs;


import org.trash_hunter.util.Direction;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Shark extends Mob {
    private final BufferedImage leftShark;
    private final BufferedImage rightShark;
    public Shark(int id,double x, Direction dir){
        super(id,x,600,dir);               // Le requin est a une profondeur fixe -> seul x évolue
        this.id=id;
        this.name ="Shark";
        this.speed = 15;
        try {
            leftShark = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("shark_left.png")));
            rightShark = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("shark_right.png")));
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors du chargement de l'image : " + e.getMessage(), e);
        }
        this.width = 70;
        this.height = 41;
        this.nbPoints = 100;                               //retranche 100 points si il est touché
        this.nbLife = 2;                                  //retranche 2 vies si il est touché
        update();
    }

    /**
     * Le requin se déplace horizontalement à une certaine vitesse
     */
    public void update(){
        switch (direction){
            case LEFT :
                x=x-speed;
                sprite=leftShark;
                break;
            case RIGHT :
                x=x+speed;
                sprite=rightShark;
                break;
            case UP, DOWN:
                break;
        }
    }
}
