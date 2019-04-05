package StepEvents;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import main.Minion;
import main.TheWalker;


/**
 * @author jenai beyene
 * Class to render an attack animation for the minion.
 */
public class AttackAnim implements StepListener, CollisionListener
{
    /**
     * The main character.
     */
    private TheWalker walker;
    private Minion minion;
    /**
     * True if minion has collided with the main character.
     */
    private static boolean checkIfCollided;
    /**
     * A counter to time the animation.
     */
    private int count = 0;
    private int initialNo = 0;


    /**
     * Used to bind an attacking animation to the minion.
     * @param walker The main character.
     */
    public AttackAnim(TheWalker walker)
    {
        this.walker = walker;

    }


    @Override
    public void preStep(StepEvent e)
    {

    }

    /**
     * Used to display the attacking animation and decrease the main characters health at discrete intervals in time. This method executes once every frame.
     * @param e The StepEvent.
     */
    @Override
    public void postStep(StepEvent e)
    {

        if(checkIfCollided)
        {
            count++;

            if(count == 1 && initialNo == 0)
            {
                walker.setHealth(walker.getHealth()-1);
                count = 0;
                initialNo = 1;
            }
            else if(count == 100)
            {
                walker.setHealth(walker.getHealth() -1);
                System.out.println("Your health is: " + walker.getHealth());
                if(walker.getHealth() <= 0)
                {
                    walker.destroy();
                    checkIfCollided = false;
                }
                count = 0;
            }

        }
    }


    /**
     * Used to check if the minion has collided with the main character.
     * @param e The CollisionEvent.
     */
    public void collide(CollisionEvent e)
    {
        checkIfCollided = (e.getOtherBody() instanceof Minion);
    }
}

