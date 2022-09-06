package tp2_space.Model;

import org.newdawn.slick.Image;

public class Model {

    private float posx;
    private float posy;
    private Image image1;
    private Image image2;

    private int stockage = 0;
    private int mars_res = 0;
    private int vieRestant = 3;

    public int getStockage() {
        return stockage;
    }

    public void setStockage(int x) {
        this.stockage = x;
    }

    public int getMars_Res() {
        return mars_res;
    }

    public void setMars_Res(int x) {
        this.mars_res = x;
    }

    public int getVieLeft() {
        return vieRestant;
    }

    public void setVieLeft(int x) {
        this.vieRestant = x;
    }

    public void setPosx(float posx) {
        this.posx = posx;
    }

    public void setPosy(float posy) {
        this.posy = posy;
    }

    public float getPosx() {
        return posx;
    }

    public float getPosy() {
        return posy;
    }

    public void setImage(Image image) {
        this.image1 = image;
    }

    public void setImage2(Image image2) {
        this.image2 = image2;
    }

    public Image getImage() {
        return image1;
    }

    public Image getImage2() {
        return image2;
    }
}
