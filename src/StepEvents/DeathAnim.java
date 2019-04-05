package StepEvents;

import Collisions.MinionCollisions;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.UserView;
import main.Minion;
import main.TheWalker;
import org.jbox2d.common.Vec2;

/**
 * @author jenai beyene
 * Class to bind a death animation for the minion.
 */
public class DeathAnim implements StepListener
{
    private UserView view;
    /**
     * The minion.
     */
    private Minion minion;
    /**
     * A counter to time the death animation.
     */
    private int counter = 0;
    private int counter1 = 0;
    private TheWalker walker;


    /**
     * Constructor used to bind a death animation for the minion.
     * @param minion
     */
    public DeathAnim(Minion minion)
    {
        this.minion = minion;
    }


    @Override
    public void preStep(StepEvent e)
    {

    }


    /**
     * Used to display the death animation when the minions health falls to 0 or below. This method executes once every frame.
     * @param e The StepEvent.
     */
    @Override
    public void postStep(StepEvent e)
    {
        if(minion.getHealth()  <= 0)
        {
            counter++;
            minion.setLinearVelocity(new Vec2(0, minion.getLinearVelocity().y));
            if(counter == 60)
            {
                minion.destroy();
                counter = 0;
            }
        }

    }
}

