package org.trash_hunter;

import org.trash_hunter.trashes.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {
    private BufferedImage backgroundImage;
    private int score;
    final private Diver myDiver;
    private final Trash[] trashset;
    private final Random randomNbr;

    public Game(){
        try{
            this.backgroundImage= ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("fond_marin_1440x780.png")));
        }catch (IOException ex){
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE,null,ex);
        }
        this.score=0;
        this.myDiver=new Diver();
        this.trashset=new Trash[30];
        this.randomNbr=new Random();
        initTrashes();
    }
    public void rending(Graphics2D contexte){
        contexte.drawImage(this.backgroundImage,0,0,null);
        contexte.drawString("Score : "+ score,10,20);
        this.myDiver.rendering(contexte);
        for (Trash trash:this.trashset) {
            trash.rendering(contexte);
        }
    }
    public void update(){
        this.myDiver.update();
        checkCollisionWithPanel();
        CollisionResult collisionResult = checkSimpleCollisionDiverTrash();
        if(collisionResult.getCollision()){
            this.score += trashset[collisionResult.getIndex()].getNbPoints();
        }
        addNewTrash();
    }
    public Diver getDiver(){return this.myDiver;}
    public boolean isFinished() {return false;} //le jeu n'a pas de fin
    public void checkCollisionWithPanel(){
        if (myDiver.getX() > backgroundImage.getWidth() - myDiver.getWidth()) {myDiver.setX(0);}  // collision avec le bord droit de la scene
        if (myDiver.getX() < 0) {myDiver.setX(backgroundImage.getWidth()-myDiver.getWidth());}  // collision avec le bord gauche de la scene
        if (myDiver.getY() > backgroundImage.getHeight() - myDiver.getHeight()) {myDiver.setY(backgroundImage.getHeight()-myDiver.getWidth());}  // collision avec le bord bas de la scene
        if (myDiver.getY() < 0) {myDiver.setY(0);}  // collision avec le bord haut de la scene
    }

    /** Vérifie les collisions entre le plongeur et les déchets de la manière la plus simple.
     * Ainsi, pour chaque déchet dans l'ensemble, elle teste s'il y a une collision.
     * Si une collision est détectée, le déchet devient invisible et la méthode
     * renvoie un résultat de collision avec un indicateur de succès et l'index du déchet.
     * Si aucune collision n'est détectée, elle renvoie un résultat de collision avec
     * un indicateur d'échec et -1 comme index.
     * @return CollisionResult
     */
    public CollisionResult checkSimpleCollisionDiverTrash() {
        for (int i = 0; i < trashset.length; i++) {
            Trash trash = trashset[i];
            if (isColliding(trash, myDiver)) {
                trash.setVisible(false);
                return new CollisionResult(true, i);
            }
        }
        return new CollisionResult(false, -1);
    }

    private boolean isColliding(Trash bottle, Diver diver) {
        return bottle.getX() < diver.x + diver.getWidth() &&
                bottle.getX() + bottle.getWidth() > diver.x &&
                bottle.getY() < diver.y + diver.getHeight() &&
                bottle.getY() + bottle.getHeight() > diver.y;
    }

    /**
     * Initialise l'ensemble des déchets avec une certaine proportion et une sélection aléatoire de
     * l'objet en fonction de sa taille
     * Nb total de déchets : 30 (15 petits,10 moyens, 5 gros)
     */
    public void initTrashes() {
        for (int i = 0; i < this.trashset.length; i++) {
            int randomNumber = randomNbr.nextInt(1,3);
            int x = randomNbr.nextInt(1440);
            int y;

            if (i <= 15) {
                if (randomNumber == 1) {
                    trashset[i] = new Bottle(x, randomNbr.nextInt(780));
                } else if (randomNumber == 2) {
                    trashset[i] = new Can(x, randomNbr.nextInt(780));
                }
            } else if (i <= 25) {
                if (randomNumber == 1) {
                    trashset[i] = new PlasticBag(x, randomNbr.nextInt(300));
                } else if (randomNumber == 2) {
                    trashset[i] = new Tire(x, randomNbr.nextInt(300));
                }
            } else {
                y = randomNbr.nextInt(500, 780);
                if (randomNumber == 1) {
                    trashset[i] = new OilContainer(x, y);
                } else if (randomNumber == 2) {
                    trashset[i] = new Boat(x, y);
                }
            }
        }
    }

    /**
     * Renvoie true si il y a collision entre deux déchets
     * @param trash1 premier déchet
     * @param trash2 second déchet
     * @return true if collision
     */
    public static boolean checkCollisionBetweenTrashes (Trash trash1,Trash trash2) {
        return(trash2.getX() <= trash1.getX() + trash1.getWidth() +10 &&
                trash1.getX() <= trash2.getX() + trash2.getWidth() +10 &&
                trash1.getY() <= trash2.getY() + trash2.getHeight() +10 &&
                trash2.getY() <= trash1.getY() + trash1.getHeight() +10);
    }

    public void addNewTrash() {
        for (Trash trash : this.trashset) {
            if (!trash.isVisible()) {
                trash.resetPosition(1440, 780);
                break;
            }
        }
    }

    public Trash[] getTrashset(){
        return this.trashset;
    }
}
