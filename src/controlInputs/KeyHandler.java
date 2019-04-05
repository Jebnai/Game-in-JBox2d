package controlInputs;

import Levels.LevelLoader;
import city.cs.engine.*;
import main.Projectile;
import org.jbox2d.common.Vec2;
import main.TheWalker;
import StepEvents.Tracker;
import java.lang.Math.*;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * @author jenai beyene
 * Class used to set key controls for the main character.
 */
public class KeyHandler extends KeyAdapter
{
    //declaring a view of type UserView
    private UserView view;

    //initializing a final velocity for walker so it can't be changed
    public static final float velocity = 12.0f;

    //declaring world, walker and fire
    private LevelLoader world;
    private TheWalker walker;
    private Projectile fire;
    private Boolean isPause = false;
    private Boolean isShot = false;
    private Boolean allowCreation = true;
    private Boolean jumpRelease = true;



    public Boolean getAllowCreation() {
        return allowCreation;
    }

    /**
     * Used to enable the creation of a fireball.
     * @param allowCreation Boolean to decide if a fire ball can be created.
     */
    public void setAllowCreation(Boolean allowCreation) {
        this.allowCreation = allowCreation;
    }


    /**
     * Constructor used to make key controls.
     * @param world Stores details of the world.
     * @param view The view of the game.
     * @param walker The main character.
     */
    public KeyHandler(LevelLoader world, UserView view, TheWalker walker)
    {
        //initializing the world and view
        this.world = world;
        this.view = view;
        this.walker = world.getWalker();



    }

    /**
     * Used to move the main character based on what key is pressed.
     * @param e The KeyEvent.
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_D && allowCreation)
        {
            walker.startWalking(velocity);
            walker.setLinearVelocity(new Vec2(walker.getLinearVelocity().x, -3f));
        }
        else if(key == KeyEvent.VK_A && allowCreation)
        {
            walker.startWalking(-velocity);
            walker.setLinearVelocity(new Vec2(walker.getLinearVelocity().x, -3f));

        }
        else if(key == KeyEvent.VK_W && allowCreation)
        {
            walker.jump(17.0f);
        }
        else if(key == KeyEvent.VK_P && allowCreation)
        {

            if(!isShot && world.returnNo() > 1)
            {
                fire = new Projectile(world);
                fire.setPosition(new Vec2(walker.getPosition().x + 2.3f, (walker.getPosition().y - 1.5f)));
                fire.setLinearVelocity(new Vec2(40, 0));
                if(walker.getLinearVelocity().x < 0 || walker.getState() == 1)
                {
                    fire.fireFlip();
                    fire.setLinearVelocity(new Vec2(-40, 0));
                    fire.setPosition(new Vec2(walker.getPosition().x -1.94f, walker.getPosition().y - 1.5f));

                }
                isShot = true;
            }

        }
        if(key == KeyEvent.VK_ESCAPE)
        {
            if(!isPause)
            {
                world.stop();
                isPause = true;
                allowCreation = false;
            }
            else if(isPause)
            {
                world.start();
                isPause = false;
                allowCreation = true;
            }
        }


    }


    /**
     * Used to stop the main character's motion depending on which key is released.
     * @param e The KeyEvent.
     */
    @Override
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        {
            if(key == KeyEvent.VK_D)
            {
                walker.stopWalking();
                walker.setLinearVelocity(new Vec2(walker.getLinearVelocity().x, 0f));

            }
            else if(key == KeyEvent.VK_A)
            {
                walker.stopWalking();
                walker.setLinearVelocity(new Vec2(walker.getLinearVelocity().x, 0f));
            }
            if(key == KeyEvent.VK_P)
            {
                isShot = false;
            }
            if (key == KeyEvent.VK_W)
            {
                walker.setLinearVelocity(walker.getLinearVelocity());
            }
        }
    }

    /**
     * Used to bind the main character to key controls when it's destroyed and recreated.
     * @param walker
     */
    public void setBody(TheWalker walker)
    {
        this.walker = walker;
    }
}
