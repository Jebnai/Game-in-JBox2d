package Backgrounds;
import Levels.LevelLoader;
import StepEvents.Tracker;
import city.cs.engine.UserView;
import city.cs.engine.World;
import main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author jenai beyene
 * Class to display a view for the game.
 */
public class Background1 extends UserView
{
    private Image background;
    private Image background2;
    private Image background3;
    private Image background4;
    private Image background5;
    private Image background6;
    private LevelLoader world;
    private JFrame window;
    private Tracker tracker;
    private Main main;
    private boolean isPause = false;

    private long setter;

    /**
     * Constructor to create a view.
     * @param world Stores details of the world.
     * @param width Width of the view.
     * @param height Height of the view.
     * @param window The frame.
     * @param main The game.
     */
    public Background1(LevelLoader world, int width, int height, JFrame window, Main main)
    {
        super(world, width, height);
        background = new ImageIcon("data/backgrounds/level2.gif").getImage();
        background2 = new ImageIcon("data/backgrounds/rainy.gif").getImage();
        background3 = new ImageIcon("data/hearts/three hearts.png").getImage();
        background4 = new ImageIcon("data/hearts/two hearts.png").getImage();
        background5 = new ImageIcon("data/hearts/one heart.png").getImage();
        background6 = new  ImageIcon("data/backgrounds/level3.gif").getImage();
        this.world = world;
        this.window = window;
        this.main = main;

    }

    /**
     * Used to display the background of the game.
     * @param g Graphics2D object.
     */
    @Override
    protected void paintBackground(Graphics2D g)
    {
        g.drawImage(background, 0, 0, 1280, 720, this);
        if(world.returnNo() == 2)
        {
            g.drawImage(background2, 0, 0, 1280, 720, this);
        }
        else if(world.returnNo() == 3)
        {
            g.drawImage(background6, 0, 0, 1280, 720, this);
        }
    }

    /**
     * Used to display different information to player e.g player hearts, level number.
     * @param g Graphics 2D object.
     */
    @Override
    protected void paintForeground(Graphics2D g)
    {

        Graphics2D g2 = g;
        Graphics2D g3 = g;
        Graphics2D g4 = g;
        g.setColor(Color.RED);
        if(world.getWalker().getHealth() == 3)
        {
            drawHearts(background3, g, 0);
        }
        else if(world.getWalker().getHealth() == 2)
        {
            drawHearts(background4, g, 30);
        }
        else if(world.getWalker().getHealth() == 1)
        {
            drawHearts(background5, g, 65);
        }
        else if(world.getWalker().getHealth() < 1)
        {
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g.drawString("Health: ", 1, 20);
        }
        if(main.returnPause())
        {
            g3.setColor(Color.white);
            g3.setFont(new Font("TimesRoman", Font.PLAIN, 40));
            g3.drawString("PAUSED", 570, 340);
        }
        if(world.getWalker().getHealth() <= 0 || world.getWalker().getPosition().y < -19)
        {
            g4.setColor(Color.red);
            g4.setFont(new Font("TimesRoman", Font.PLAIN, 40));
            g4.drawString("YOU DIED", 570, 340);
            world.getWalker().setHealth(0);
        }
        if(world.returnNo() == 3)
        {
            if(xDistances() < 3 && xDistances()> 0 && yDistances() < 5 && yDistances() > 0)
            {
                g4.setColor(Color.green);
                g4.setFont(new Font("TimesRoman", Font.PLAIN, 40));
                g4.drawString("YOU WIN", 570, 340);
                world.stop();
            }
        }
        g2.setColor(Color.GREEN);
        g2.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g2.drawString("Level: " + world.returnNo(), 1, 40);

    }

    /**
     * Used to return the x distance between the main character and the highest platform that is randomly generated.
     * @return Returns the x distance between the main character and the highest platform that is randomly generated.
     */
    public float xDistances()
    {
        return world.getWalker().getPosition().x - world.getLastPlatform().x;
    }

    /**
     * Used to return the y distance between the main character and the highest platform that is randomly generated.
     * @return Returns the y distance between the main character and the highest platform that is randomly generated.
     */
    public float yDistances()
    {
        return world.getWalker().getPosition().y - world.getLastPlatform().y;
    }

    /**
     * Used to display the amount of health the main character has through hearts.
     * @param background The heart images.
     * @param g Graphics2D object.
     * @param i Number to position the hearts on the screen
     */
    public void drawHearts(Image background, Graphics2D g, int i)
    {
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.drawString("Health: " , 1, 20);
        g.drawImage(background, 70,  -3, 100- i, 35, this);
    }


}

