package tp2_space.View;

import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class View {

    public void afficher(float posx, float posy, Image image) {

        image.draw(posx, posy, 128, 128);

    }

    public void afficherCargaison(int x, Graphics g) {

        String text = "Cargaison: " + (x * 100 / 16384) + "%";
        g.drawString(text, 20, 50);
        if ((x * 100 / 16384) == 100) {
            g.drawString("Pesez E pour envoyen en Mars!", 20, 70);
            g.setBackground(Color.red);
        }
    }

    public void afficherVies(int x, Graphics g, Image coeur) {
        for (int i = 0; i < x; i++) {
            coeur.draw(50 * i, 100, 50, 50);
        }
    }

    public void afficherCargaisonMars(int x, Graphics g) {
        String text = "Score: " + x;
        g.drawString(text, 825, 50);
    }

}
