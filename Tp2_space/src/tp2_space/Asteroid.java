package tp2_space;

import java.util.Random;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public class Asteroid {

    private int posx;
    private int posy;
    private int tailleX;
    private int tailleY;
    private int speedy;
    private SpriteSheet asteroidSprite;
    private Image subImage;

    public Asteroid(int tailleX, int tailleY, int posx, int posy, SpriteSheet asteroidSprite) {
        
        this.tailleX = tailleX;
        this.tailleY = tailleY;
        this.posx = posx;
        this.posy = posy;
        
        Random rand = new Random();
        
        this.speedy = rand.nextInt(2) + 1;
        this.asteroidSprite = asteroidSprite;
        this.subImage = asteroidSprite.getSubImage(rand.nextInt(4) * 72, rand.nextInt(3) * 72, 72, 72);
    }

    public void falling() {
        
        this.speedy += 0.1;
        this.posy += this.speedy;

        subImage.draw(posx, posy, this.tailleX, this.tailleY);

    }

    public Image getsubImage() {
        return subImage;
    }

    public int getposy() {
        return posy;
    }

    public int getposx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public void TestCollison(Graphics g, int y, int x) {
        g.fillRect(posx, posy, this.tailleX + x, this.tailleY + y);
    }

    public Rectangle bounds(int x, int y) {
        return (new Rectangle(posx, posy, this.tailleX + x, this.tailleY + y));
    }
}
