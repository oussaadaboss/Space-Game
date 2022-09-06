package tp2_space;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
/**
 *
 * @author Rajakanth Kandasamy / Adlane Oussaada
 */
public class Tp2_space {

    public static void main(String[] args) {
        
        try {
            AppGameContainer app = new AppGameContainer(new Game("fenetre"));
            app.setDisplayMode(1000, 800, false);
            app.setShowFPS(true); // true for display the numbers of FPS
            app.setVSync(true); // false for disable the FPS synchronize
            app.start();
        } catch (SlickException ex) {
            
            System.out.println("message" + ex.getMessage());
        }
    }

}
