package tp2_space;

import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import tp2_space.Model.Model;
import tp2_space.View.View;

public class Controler {

    private Model model;
    private View view;

    public Controler(Model model, View view) {
        this.model = model;
        this.view = view;

    }

    public void setModelposx(float posx) {
        model.setPosx(posx);
    }

    public void setModelposy(float posy) {
        model.setPosy(posy);
    }

    public void setModelStockage(int x) {
        model.setStockage(x);
    }

    public void setModelMars_Res(int x) {
        model.setMars_Res(x);
    }

    public void setModelVieRestant(int x) {
        model.setVieLeft(x);
    }

    public float getModelposx() {
        return model.getPosx();
    }

    public float getModelposy() {
        return model.getPosy();
    }

    public void setModelImage(Image image) {
        model.setImage(image);
    }

    public Image getModelImage() {
        return model.getImage();
    }

    public void setModelImage2(Image image) {
        model.setImage2(image);
    }

    public Image getModelImage2() {
        return model.getImage2();
    }

    public void updateView() {
        view.afficher(model.getPosx(), model.getPosy(), model.getImage());

    }

    public void RenderView(Graphics g) {
        view.afficherCargaison(model.getStockage(), g);
        view.afficherCargaisonMars(model.getMars_Res(), g);
        view.afficherVies(model.getVieLeft(), g, model.getImage2());

    }

    public Rectangle bounds() {
        return (new Rectangle(getModelposx() + 35, getModelposy() + 10, 60, 90));
    }

    public void TestCollison(Graphics g) {
        g.fillRect(getModelposx() + 35, getModelposy() + 10, 60, 90);
    }
}
