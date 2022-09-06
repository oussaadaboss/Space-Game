package tp2_space;

import java.util.ArrayList;
import java.util.Random;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import tp2_space.Model.Model;
import tp2_space.View.View;

public class Game extends BasicGame implements KeyListener {

    private float x = 436, y = 650;

    private boolean movingXa = false;
    private boolean movingXd = false;
    private boolean movingYw = false;
    private boolean movingYs = false;
    private boolean permDeTir;
    private Image background = null;
    private Image vaisseau;
    private Image vies;
    private Controler controler;
    private Model info;
    private View view;
    private ArrayList keylist;
    private Etoile[] etoile = null;
    private ArrayList<Tir> tir;
    private final int drop_num = 50;
    private SpriteSheet asteroidSprite;
    private ArrayList<Asteroid> asteroid;
    private ArrayList<Asteroid> asteroid2;
    private ArrayList<Asteroid> asteroid3;
    private int stockage = 0;
    private int mars_res = 0;
    private int pv = 3;
    private Image explosion;

    public Game(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        
        explosion = new Image("Images/explosion.png");
        background = new Image("Images/background.jpg");
        vaisseau = new Image("Images/lighter.gif");
        vies = new Image("Images/vie.png");
        info = dataBase();
        view = new View();
        
        controler = new Controler(info, view);
        controler.setModelposx(x);
        controler.setModelposy(y);
        controler.setModelImage(vaisseau);

        controler.setModelImage2(vies);
        controler.setModelStockage(stockage);
        controler.setModelMars_Res(mars_res);
        controler.setModelVieRestant(pv);//vie Restant

        keylist = new ArrayList();
        
        etoile = new Etoile[drop_num];
        for (int i = 0; i < etoile.length; i++) {
            etoile[i] = new Etoile(1000, 800);
        }
        
        tir = new ArrayList<Tir>();
        
        asteroidSprite = new SpriteSheet("Images/asteroids1.png", 72, 72);
        asteroid = new ArrayList<Asteroid>();
        for (int i = 0; i < 3; i++) {
            Random rand = new Random();
            asteroid.add(new Asteroid(256, 256, rand.nextInt(690), rand.nextInt(256) - 500, asteroidSprite));
        }
        
        asteroid2 = new ArrayList<Asteroid>();
        asteroid3 = new ArrayList<Asteroid>();
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        if (controler.getModelposx() < 0) {
            this.movingXa = false;
        }
        if (controler.getModelposx() > 870) {
            this.movingXd = false;
        }
        if (controler.getModelposy() < 70) {
            this.movingYw = false;
        }
        if (controler.getModelposy() > 670) {
            this.movingYs = false;
        }
        if (this.movingXa) {
            if (keylist.contains("a")) {
                x -= 12;
                controler.setModelposx(x);
            }
        }
        // mouvement part
        if (this.movingXd) {
            if (keylist.contains("d")) {
                x += 12;
                controler.setModelposx(x);
            }
        }

        if (this.movingYw) {
            if (keylist.contains("w")) {
                y -= 12;
                controler.setModelposy(y);
            }
        }

        if (this.movingYs) {
            if (keylist.contains("s")) {
                y += 12;
                controler.setModelposy(y);
            }
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {

        background.draw();
        //----Stars falling-----
        for (int i = 0; i < etoile.length; i++) {
            etoile[i].fall(g);
        }

        if (permDeTir) {
            for (int i = 0; i < tir.size(); i++) {
                tir.get(i).shoot(g);
                if (tir.get(i).getPosy() < 0) {
                    tir.remove(tir.get(i));
                }
            }
        }
//----------Big Asteroid Collision(and making them fall)------
        for (int i = 0; i < asteroid.size(); i++) {
            asteroid.get(i).falling();
            if (asteroid.get(i).bounds(-25, -25).intersects(controler.bounds())) {
                explosion.draw(controler.getModelposx(), controler.getModelposy() - 59);
                asteroid.remove(asteroid.get(i));
                Random rand = new Random();
                asteroid.add(new Asteroid(256, 256, rand.nextInt(730), rand.nextInt(256) - 500, asteroidSprite));
                pv -= 1;
                controler.setModelVieRestant(pv);
            }
            for (int j = 0; j < tir.size(); j++) {
                if (asteroid.get(i).bounds(0, 0).intersects(tir.get(j).bounds())) {
                    explosion.draw(tir.get(j).getPosx() - 50, tir.get(j).getPosy() - 200);
                    asteroid.remove(asteroid.get(i));
                    asteroid2.add(new Asteroid(128, 128, (int) tir.get(j).getPosx() - 100, (int) tir.get(j).getPosy() - 100, asteroidSprite));
                    asteroid2.add(new Asteroid(128, 128, (int) tir.get(j).getPosx() + 20, (int) tir.get(j).getPosy() - 100, asteroidSprite));
                    tir.remove(tir.get(j));
                    Random rand = new Random();
                    asteroid.add(new Asteroid(256, 256, rand.nextInt(730), rand.nextInt(256) - 500, asteroidSprite));
                }
            }
            if (asteroid.get(i).getposy() > 800) {
                Random rand = new Random();
                asteroid.get(i).setPosy(-500);
                asteroid.get(i).setPosx(rand.nextInt(690));
            }
        }
//-----Collison with Medium Asteroids(and making them fall))------
        for (int i = 0; i < asteroid2.size(); i++) {
            asteroid2.get(i).falling();
            if (asteroid2.get(i).bounds(-18, 18).intersects(controler.bounds())) {
                explosion.draw(controler.getModelposx(), controler.getModelposy() - 59);
                asteroid2.remove(asteroid2.get(i));
                pv -= 1;
                controler.setModelVieRestant(pv);
            }
            for (int j = 0; j < tir.size(); j++) {
                if (asteroid2.get(i).bounds(0, 0).intersects(tir.get(j).bounds())) {
                    explosion.draw(tir.get(j).getPosx() - 50, tir.get(j).getPosy() - 100);
                    asteroid2.remove(asteroid2.get(i));
                    asteroid3.add(new Asteroid(64, 64, (int) tir.get(j).getPosx(), (int) tir.get(j).getPosy() - 100, asteroidSprite));
                    asteroid3.add(new Asteroid(64, 64, (int) tir.get(j).getPosx() + 60, (int) tir.get(j).getPosy() - 100, asteroidSprite));
                    tir.remove(tir.get(j));
                }
            }
        }
//------Small Asteroids Falling-----
        for (int k = 0; k < asteroid3.size(); k++) {
            asteroid3.get(k).falling();
        }
//---------colision avec les petits asteroides-------------
        for (int i = 0; i < asteroid3.size(); i++) {
            if (asteroid3.get(i).bounds(0, 0).intersects(controler.bounds())) {
                if (stockage >= 16384) {
                    explosion.draw(controler.getModelposx(), controler.getModelposy() - 59);
                    asteroid3.remove(asteroid3.get(i));
                    pv -= 1;
                    controler.setModelVieRestant(pv);
                } else {
                    asteroid3.remove(asteroid3.get(i));
                    stockage = stockage + 2048;
                    controler.setModelStockage(stockage);
                }
            }
        }

        controler.updateView();
        controler.RenderView(g);

//------Game Over Screen-----
        Image gameOverBackgroud = new Image("Images/GameOverBackground.png");
        Image play = new Image("Images/Play.png");
        Image quit = new Image("Images/Quit.png");

        int posX = Mouse.getX();
        int posY = Mouse.getY();

        if (pv <= 0) {
            gameOverBackgroud.draw();
            play.draw(400, 300, 180, 112);
            quit.draw(400, 550, 180, 112);
            if ((posX >= 400 && posX <= 580) && (posY >= 390 && posY <= 500)) {
                if (Mouse.isButtonDown(0)) {
                    play.destroy();
                    quit.destroy();
                    gameOverBackgroud.destroy();
                    pv = 3;
                    stockage = 0;
                    mars_res = 0;
                    controler.setModelVieRestant(pv);
                    controler.setModelStockage(stockage);
                    controler.setModelMars_Res(mars_res);
                }
            }
            if ((posX >= 400 && posX <= 580) && (posY >= 140 && posY <= 250)) {
                if (Mouse.isButtonDown(0)) {
                    System.exit(0);
                }
            }
        }
    }

    @Override
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_W) {
            keylist.add("w");

            this.movingYw = true;

        }
        if (key == Input.KEY_A) {
            keylist.add("a");

            this.movingXa = true;

        }
        if (key == Input.KEY_S) {
            keylist.add("s");

            this.movingYs = true;

        }
        if (key == Input.KEY_D) {
            keylist.add("d");

            this.movingXd = true;
        }

        if (key == Input.KEY_SPACE) {
            tir.add(new Tir(controler.getModelposx() + 59, controler.getModelposy()));
            permDeTir = true;
        }
        if (key == Input.KEY_E) {
            mars_res += (stockage * (64 * 64));
            controler.setModelMars_Res(mars_res);
            stockage = 0;
            controler.setModelStockage(stockage);
        }

    }

    @Override
    public void keyReleased(int key, char c) {
        if (key == Input.KEY_W) {
            keylist.remove("w");

        }
        if (key == Input.KEY_A) {
            keylist.remove("a");

        }
        if (key == Input.KEY_S) {
            keylist.remove("s");

        }
        if (key == Input.KEY_D) {
            keylist.remove("d");

        }

        if (keylist == null) {
            this.movingXa = false;
            this.movingYw = false;
            this.movingXd = false;
            this.movingYs = false;
        }
    }

    public Model dataBase() {
        Model model = new Model();
        return model;
    }

}
