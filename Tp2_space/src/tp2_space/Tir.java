package tp2_space;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Tir {

    private float posx;
    private float posy;
    private int speedy;
    private int largeur;
    private int longueur;

    public Tir(float posx, float posy) {

        this.posx = posx;
        this.posy = posy;
        this.speedy = 15;
        this.largeur = 10;
        this.longueur = 20;

    }

    public void shoot(Graphics g) {
        //faire en sorte que les missiles avancent
        this.speedy += 0.1;
        this.posy -= this.speedy;

        g.setColor(new org.newdawn.slick.Color(0, 220, 220));

        g.fillOval(this.posx, this.posy, this.largeur, this.longueur);
    }

    public float getPosy() {
        return posy;
    }
    public float getPosx() {
        return posx;
    }
    public void TestCollison(Graphics g) {
        g.fillRect(this.posx , this.posy , this.largeur, this.longueur);
    }

    public Rectangle bounds() {
        return (new Rectangle(this.posx , this.posy , this.largeur, this.longueur));
    }

}
