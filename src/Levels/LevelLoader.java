package Levels;
import Backgrounds.Background1;
import Collisions.PortalCollisions;
import Collisions.SpinnerCollisions;
import Platforms.MovingPlatforms;
import Platforms.NormalPlatform;
import StepEvents.SpinnerMovement;
import StepEvents.Tracker;
import city.cs.engine.*;
import controlInputs.KeyHandler;
import main.*;
import org.jbox2d.common.Vec2;
import java.lang.Math.*;

import javax.swing.*;
import java.util.List;

/**
 * @author jenai beyene
 * Class to load different levels to the world
 */
public class LevelLoader extends World
{
    /**
     * The main character.
     */
    private TheWalker walker;
    /**
     * A platform object.
     */
    private NormalPlatform pl;
    /**
     * A platform object that is used to create a randomly generated level.
     */
    private MovingPlatforms movingPlat;
    /**
     * The portal object.
     */
    private Portal portal;
    private Background1 view;
    /**
     * The level number.
     */
    private int no = 1;
    private Minion minion;
    /**
     * The spinner object.
     */
    private Spinner spinner;
    private Main main;
    private int theHealth;

    /**
     * Constructor to build the world
     * @param framesPerSecond The FPS of the game.
     * @param main Stores the game.
     */
    public LevelLoader(int framesPerSecond, Main main)
    {
        super(framesPerSecond);
        this.main = main;
    }

    /**
     * Used to set the level number.
     * @param no The level number.
     */
    public void setNo(int no)
    {
        this.no = no;
    }
    public Tracker getTracker()
    {
        return main.getTracker();
    }

    /**
     * Used to return the main character.
     * @return Returns the main character.
     */
    public TheWalker getWalker()
    {
        return walker;
    }

    /**
     * Used to return the portal object.
     * @return Returns the portal object.
     */
    public Portal getPortal()
    {
        return portal;
    }

    /**
     * Used to populate the world with the main character.
     */
    public void populate()
    {
        walker = new TheWalker(this, 3);
        walker.setPosition(new Vec2(-30, -7.5f));
        /*Shape floor = new BoxShape(30.8f, 0.1f);
        Body theFloor = new StaticBody(this, floor);
        theFloor.setPosition(new Vec2(-5, -11.5f));*/

    }
    public int getTheHealth()
    {
        return theHealth;
    }
    public void setTheHealth(int health)
    {
        theHealth = health;
    }

    /**
     * Used to restart the game.
     */
    public void restartGame()
    {
        nukeLevel();
        populate();
        levelOne(this);
        main.resetCharMotion();
    }

    /**
     * Used to restart level 2.
     */
    public void restartLvl2()
    {
        nukeLevel();
        populate();
        levelTwo(this);
        main.resetCharMotion();
    }

    /**
     * Used to restart level 3.
     */
    public void restartLvl3()
    {
        nukeLevel();
        populate();
        levelThree(this);
        main.resetCharMotion();
    }

    /**
     * Used to return the key controls.
     * @return Returns the key controls.
     */
    public KeyHandler getKeyThing()
    {
        return main.getKeyThing();
    }

    /**
     * Used to destroy every static and dynamic body in the world including the main character.
     */
    public void nukeLevel()
    {
        List<DynamicBody> d = getDynamicBodies();
        List<StaticBody> s = getStaticBodies();
        for(int i = 0; i < d.size()-1; i++)
        {
            d.get(i).destroy();
        }
        for(int i = 0; i < s.size()-1; i++)
        {
            s.get(i).destroy();
        }
        walker.destroy();
        if(no == 2)
        {
            spinner.destroy();
        }

    }

    /**
     * Used to create level 1.
     * @param world Stores details of the world.
     */
    public void levelOne(World world)
    {
        no = 1;
        pl = new NormalPlatform(world);
        pl.drawPlat1(world);
        portal = new Portal(world);
        portal.setPosition(new Vec2(12, 42.15f));
        pl.straightPlat(world, -8.5f);


    }


    /**
     * Used to create level 2.
     * @param world Stores details of the world.
     */
    public void levelTwo(World world)
    {
        no = 2;
        pl.straightPlat(world, -8.5f);
        pl.draw1Plat(world, 15, -5.5f);
        pl.draw1Plat(world, 25, -0.5f);
        pl.draw1Plat(world, 15, 5.5f);
        pl.reversePlat(world);
        pl.draw1Plat(world, -37, 12);
        pl.draw1Plat(world, -45, 17);
        pl.draw1Plat(world,-37, 22);
        pl.straightPlat(world, 26);
        spinner = new Spinner(world, 3);
        this.addStepListener(new SpinnerMovement(world, spinner));
        spinner.addCollisionListener(new SpinnerCollisions(spinner));
        portal = new Portal(world);
        portal.setPosition(new Vec2(10, 30));
        //lvl2 in progress
    }

    /**
     * Used to return the level number.
     * @return Returns the level number.
     */
    public int returnNo()
    {
        return no;
    }

    /**
     * Used to create level 3.
     * @param world Stores details of the world.
     */
    public void levelThree(World world)
    {
        no = 3;
        pl.straightPlat(world, -8.5f);
        movingPlat = new MovingPlatforms(world);
        movingPlat.movingPlat(world);
        //yet to implement lvl 3
    }

    /**
     * Used to get the position of the highest platform that is randomly generated.
     * @return Returns the position of the highest platform that is randomly generated.
     */
    public Vec2 getLastPlatform()
    {
        return movingPlat.getLastPlatform();
    }


}

