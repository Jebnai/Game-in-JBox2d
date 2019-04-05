package Collisions;
import city.cs.engine.*;
import main.Spinner;
import main.Projectile;
import main.TheWalker;
import org.jbox2d.common.Vec2;

/**
 * Class used to set a collision listener to the enemy spinner.
 */
public class SpinnerCollisions implements CollisionListener
{
    /**
     * The enemy spinner,
     */
    private Spinner spinner;

    /**
     * Constructor used to set a collision listener to the enemy spinner.
     * @param spinner
     */
    public SpinnerCollisions(Spinner spinner)
    {
        this.spinner = spinner;
    }

    /**
     * Used to do certain things to the spinner when it collides with certain objects like damage the main character and take damage when it collides with the fire ball.
     * @param e
     */
    @Override
    public void collide(CollisionEvent e)
    {
        if(e.getOtherBody() instanceof Projectile)
        {
            spinner.setHealth(spinner.getHealth()-1);
            e.getOtherBody().destroy();
            if(spinner.getHealth() <=0)
            {
                spinner.destroy();
            }
        }
        if(e.getOtherBody() instanceof TheWalker)
        {
            final float reverse = -spinner.getLinearVelocity().x;
            spinner.setLinearVelocity(new Vec2(reverse, 0));
            ((TheWalker) e.getOtherBody()).setHealth(((TheWalker) e.getOtherBody()).getHealth()-1);
            if(((TheWalker) e.getOtherBody()).getHealth() <= 0)
            {
                e.getOtherBody().destroy();
            }
        }
    }
}

