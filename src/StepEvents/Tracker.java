package StepEvents;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.UserView;
import main.TheWalker;
import org.jbox2d.common.Vec2;

/**
 * @author jenai beyene
 * Class used to pan the view to where the main character is.
 */
public class Tracker implements StepListener
{
    /**
     * The view of the game.
     */
    private UserView view;
    /**
     * The main character.
     */
    private TheWalker walker;
    private int counter = 0;


    /**
     * Constructor used to be able to control where the view is at.
     * @param view The view of the game.
     * @param walker The main character.
     */
    public Tracker(UserView view, TheWalker walker)
    {
        this.view = view;
        this.walker = walker;
    }


    @Override
    public void preStep(StepEvent e)
    {

    }


    /**
     * Used to be able to set the view to wherever the main character is at. This method executes once every frame.
     * @param e The StepEvent.
     */
    @Override
    public void postStep(StepEvent e)
    {
        if(walker.getPosition().y > -7.5f)
        {
            view.setCentre(new Vec2(walker.getPosition().x, walker.getPosition().y + 4));
        }
        if(walker.getHealth() <=0)
        {
            walker.destroy();
        }

    }

    /**
     * Used to reset the tracker to the view and the main character when they are recreated.
     * @param view The view of the game/
     * @param walker The main character.
     */
    public void setTracker(UserView view, TheWalker walker)
    {
        this.view = view;
        this.walker = walker;
    }
}

