package main;

import Backgrounds.Background1;
import Collisions.MinionCollisions;
import Collisions.MovingPlatCollisions;
import Collisions.PortalCollisions;
import Collisions.SpinnerCollisions;
import Levels.LevelLoader;
import Platforms.NormalPlatform;
import StepEvents.MovingPlat;
import StepEvents.Tracker;
import city.cs.engine.*;
import controlInputs.KeyHandler;
import org.jbox2d.common.Vec2;


import javax.swing.*;
import java.awt.*;

/**
 * @author jenai beyene
 * Class which starts up the game.
 */
public class Main
{
    //declare new world and view
    /**
     * Stores details about the world.
     */
    private LevelLoader world;
    /**
     * Stores details about the view of the world.
     */
    private Background1 view;
    private NormalPlatform pl;
    /**
     * Stores details about the frame of the game.
     */
    final JFrame window;
    /**
     * Stores details about key controls
     */
    private KeyHandler keyThing;
    /**
     * Stores details about how the camera pans around the world.
     */
    private Tracker tracker;
    private PortalCollisions portalCollide;
    private Main main;
    private ControlPanel button;

    /**
     * Used to return the tracker.
     * @return Returns the tracker.
     */
    public Tracker getTracker() {
        return tracker;
    }

    /**
     * Used to return the key controls.
     * @return Returns the key controls.
     */
    public KeyHandler getKeyThing() {
        return keyThing;
    }

    /**
     * Used to return whether or not the game is paused.
     * @return Returns true if game is paused.
     */
    public Boolean returnPause()
    {
        return button.getPause();
    }

    /**
     * Constructor to start up the game.
     */
    public Main()
    {
        //make a new world and view size

        world = new LevelLoader(60, this);
        world.populate();
        world.levelOne(world);












        //create window
        window = new JFrame("Game");
        view = new Background1(world, 1280, 720, window, this);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationByPlatform(true);
        window.add(view);
        window.setResizable(false);
        button = new ControlPanel(world);
        window.add(button.getPanel(), BorderLayout.NORTH);
        window.pack();
        window.setVisible(true);
        //window.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);


        //allow key inputs
        keyThing = new KeyHandler(world,view, world.getWalker());
        window.addKeyListener(keyThing);
        tracker = new Tracker(view, world.getWalker());
        world.addStepListener(tracker);
        portalCollide = new PortalCollisions(this);
        world.getPortal().addCollisionListener(portalCollide);















        //JFrame debugView = new DebugViewer(world, 500, 500);



        world.start();

    }


    /**
     * Used to return the world.
     * @return Returns the world.
     */
    public LevelLoader getWorld()
    {
        return world;
    }

    /**
     * Used to set the world to a particular world.
     * @param world Stores details about the world.
     */
    public void setWorld(LevelLoader world)
    {
        this.world = world;
    }

    /**
     * Used to get the main character.
     * @return Returns the main character.
     */
    public TheWalker getWalker()
    {
        return world.getWalker();
    }

    /**
     * Used to load level 2 of the game.
     * @param world Stores details of the world.
     */
    public void nextLevel(LevelLoader world)
    {
        world.setTheHealth(world.getWalker().getHealth());
        world.nukeLevel();
        world.levelTwo(world);
        loadLevel();
    }

    /**
     * Used to load level 3 of the game.
     * @param world Stores details of the world.
     */
    public void level3Load(LevelLoader world)
    {
        world.setTheHealth(world.getWalker().getHealth());
        world.nukeLevel();
        world.levelThree(world);
        loadLevel();
    }

    /**
     * Used to spawn the main character and rebind the portal collisions.
     */
    public void loadLevel()
    {
        world.populate();
        world.getWalker().setHealth(world.getTheHealth());
        keyThing.setBody(world.getWalker());
        tracker.setTracker(view, world.getWalker());
        world.getPortal().addCollisionListener(portalCollide);
    }



    public Main getMain() {
        return main;
    }

    /**
     * Used to rebind the main characters key controls and collisions between objects.
     */
    public void resetCharMotion()
    {
        keyThing.setBody(world.getWalker());
        tracker.setTracker(view, world.getWalker());
        world.getPortal().addCollisionListener(portalCollide);
        world.getWalker().returnMinion().addCollisionListener(new MinionCollisions(world.getWalker().returnMinion()));

    }

    /**
     * Used to return what level the player is currently playing.
     * @return Returns the level number.
     */
    public int returnNo()
    {
        return world.returnNo();
    }
    public LevelLoader returnWorld()
    {
        return world;
    }

    public static void main(String[] args)
    {
      new Main();
    }
}



