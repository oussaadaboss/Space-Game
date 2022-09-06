package tp2_space;

import java.util.Random;
import org.newdawn.slick.Graphics;

public class Etoile {

    private int posx;
    private int posy;
    private int speedy;
    private int largeur;
    private int longueur;
    private int width_place;
    private int height_place;

    public Etoile(int width_place, int height_place) {
        this.width_place = width_place;
        this.height_place = height_place;
        Random rand = new Random();
        this.posx = rand.nextInt(this.width_place);
        this.posy = rand.nextInt(700);
        this.speedy = rand.nextInt(6) + 2;
        this.largeur = 6;       
       this.longueur = 9;
       
    }

    public void fall(Graphics g) {
        //faire en sorte que les étoiles tombent
        this.speedy += 0.1;
        this.posy += this.speedy;

        if (this.posy > this.height_place) {
            Random rand = new Random();
            this.posx = rand.nextInt(this.width_place);            
            this.posy = 0;
            this.speedy = rand.nextInt(6) + 2;
     
             this.largeur = 6;
        this.longueur = 9;
        
        }
        g.setColor(new org.newdawn.slick.Color(255, 220, 220));
        //g.fillRect(this.posx,this.posy ,this.largeur ,this.longueur );
        g.fillOval(this.posx, this.posy, this.largeur, this.longueur);
    }
}
