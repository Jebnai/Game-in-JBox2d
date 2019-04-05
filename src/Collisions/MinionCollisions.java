package Collisions;
import city.cs.engine.*;
import main.Minion;
import main.Projectile;
import main.TheWalker;
import org.jbox2d.common.Vec2;

import java.util.Timer;


/**
 * @author jenai beyene
 * Class used to set a collision listener to the minion.
 */
public class MinionCollisions implements CollisionListener
{

    //declaring a minion of type Minion
    private Minion minion;
    private MinionCollisions walkerChecker;
    private boolean isCollided = false;


    /**
     * Constructor used to set a collision listener to the minion.
     * @param minion The minion.
     */
    public MinionCollisions(Minion minion)
    {
        //initializing minion
        this.minion = minion;

    }

    public boolean collisionStatus()
    {
        return isCollided;
    }


    /**
     * Used to do certain things to the minion when it hits particular objects like lower it's health or destroy it.
     * @param e The CollisionEvent.
     */
    public void collide(CollisionEvent e)
    {
        if(e.getOtherBody() instanceof Projectile)
        {
            minion.setHealth(minion.getHealth()-1);

            if(minion.getHealth() <= 0)
            {

                minion.deathImg();
                minion.setLinearVelocity(new Vec2(0, minion.getLinearVelocity().y));
                //minion.destroy();
                System.out.println("Minion health is: " + minion.getHealth());

                e.getOtherBody().destroy();

            }


        }
        if(e.getOtherBody() instanceof TheWalker)
        {
            isCollided = true;
            minion.stopWalking();
            minion.attackImg();
            System.out.println("MINION NOW IN ATTACK RANGE");
        }

    }
}

