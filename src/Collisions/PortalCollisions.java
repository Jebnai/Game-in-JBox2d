package Collisions;

import Levels.LevelLoader;
import city.cs.engine.*;
import main.Main;
import main.TheWalker;

/**
 * @author jenai beyene
 * Class used to set a collision listener to the portal.
 */
public class PortalCollisions implements CollisionListener
{
    private Main main;

    /**
     * Constructor used to set a collision listener to the portal.
     * @param main
     */
    public PortalCollisions(Main main)
    {
        this.main = main;
    }


    /**
     * Used to teleport the main character to the next level.
     * @param e The CollisionEvent.
     */
    @Override
    public void collide(CollisionEvent e)
    {
        if(e.getOtherBody() instanceof TheWalker)
        {
            if(main.returnNo() == 1)
            {
                main.nextLevel(main.getWorld());
            }
            else if(main.returnNo() == 2)
            {
                main.level3Load(main.getWorld());
            }
        }
    }
}

