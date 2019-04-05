package Collisions;
import city.cs.engine.*;
import main.Minion;
import main.Projectile;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.util.zip.CheckedOutputStream;


/**
 * @author jenai beyene
 * Class used to set a collision listener to the fire ball.
 */
public class ProjectileCollisions implements CollisionListener {
    //declaring a projectile of type Projectile
    private Projectile projectile;


    /**
     * Constructor used to set a collision listener to the fire ball.
     * @param projectile
     */
    public ProjectileCollisions(Projectile projectile) {

        //initializing projectile
        this.projectile = projectile;


    }

    /**
     * Used to destroy the fire ball if it collides with anything.
     * @param e The CollisionEvent.
     */
    @Override
    public void collide(CollisionEvent e)
    {
        projectile.destroy();
        if(e.getOtherBody() instanceof Minion)
        {
            projectile.destroy();
            //e.getOtherBody().destroy();

        }
    }

}

