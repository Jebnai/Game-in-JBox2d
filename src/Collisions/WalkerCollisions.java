package Collisions;

import city.cs.engine.CollisionListener;
import city.cs.engine.*;
import main.Minion;
import main.Spinner;
import main.TheWalker;

/**
 * @author jenai beyene
 * Class used to set a collision listener to the main character.
 */
public class WalkerCollisions implements CollisionListener
{

    //declaring a walker of type TheWalker
    /**
     * The main character.
     */
    private TheWalker walker;

    /**
     * Constructor used to set a collision listener to the main character.
     * @param walker The main character.
     */
    public WalkerCollisions(TheWalker walker)
    {
        this.walker = walker;

    }


    /**
     * Used to do certain things to the main character when colliding to certain objects like enemies or the portal.
     * @param e
     */
    public void collide(CollisionEvent e)
    {
      if(e.getOtherBody() instanceof Spinner)
      {
          walker.setHealth(walker.getHealth()-1);
          if(walker.getHealth() <= 0)
          {
              walker.destroy();
          }
      }
    }
}

