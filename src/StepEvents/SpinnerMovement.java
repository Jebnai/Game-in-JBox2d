package StepEvents;
import Levels.LevelLoader;
import city.cs.engine.*;
import main.Minion;
import main.Spinner;
import main.TheWalker;

/**
 * @author jenai beyene
 * Class to control the movement of the enemy spinner.
 */
public class SpinnerMovement implements StepListener
{
    /**
     * The enemy spinner.
     */
    private Spinner spinner;
    private World world;
    private int count = 0;

    /**
     * Constructor used to be able to create a movement control for the enemy spinner.
     * @param world
     * @param spinner
     */
    public SpinnerMovement(World world, Spinner spinner)
    {
        this.world = world;
        this.spinner = spinner;
    }

    @Override
    public void preStep(StepEvent e)
    {

    }

    /**
     * Used to control the movement of the enemy spinner. This method executes once every frame.
     * @param e The StepEvent.
     */
    @Override
    public void postStep(StepEvent e)
    {
        if(spinner.getPosition().x <= -30)
        {
            spinner.startWalking(35);
        }
        else if(spinner.getPosition().x >= 10)
        {
            spinner.startWalking(-25);
        }
        System.out.println(spinner.getPosition().x);
    }
}

