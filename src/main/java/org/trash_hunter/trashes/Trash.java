package org.trash_hunter.trashes;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Trash {
    protected String name;                      //nom commun

    protected double x,y;                       //coordonnées
    protected int width, height;                //largeur, hauteur
    protected int nbPoints;                     //nomnbre de points rapportés
    protected BufferedImage sprite;             //image du déchet
    protected boolean isVisible = true;         //déchet visible ou non
    protected long creationTime;                //temps passé après création
    protected int time;                         //temps de récupération en ms
    public Trash(double x,double y){
        this.x=x;
        this.y=y;
    }
    public void rendering (Graphics2D contexte){
        if(this.isVisible){
            contexte.drawImage(this.sprite,(int)x,(int)y,null);
        }
    }
    public void resetPosition(int maxX, int maxY) {
        this.x = (int) (Math.random() * (maxX - this.sprite.getWidth()));
        this.y = (int) (Math.random() * (maxY - this.sprite.getHeight()));
        creationTime = System.currentTimeMillis();
        isVisible = true;
    }

    public String toString (){
        return this.name;
    }
    public void setVisible(boolean visible) {
        isVisible = visible;
    }
    public boolean isExpired() {
        return System.currentTimeMillis() - creationTime > 10000;
    }
    public double getX(){return (this.x);}
    public double getY(){return (this.y);}
    public int getWidth(){return this.width;}
    public int getHeight(){return this.height;}
    public int getNbPoints(){
        return (this.nbPoints);
    }
    public boolean isVisible() {
        return isVisible;
    }
}
