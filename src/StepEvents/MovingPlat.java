package StepEvents;

import Platforms.MovingPlatforms;
import city.cs.engine.StepListener;
import city.cs.engine.*;
import main.Main;
import org.jbox2d.common.Vec2;

public class MovingPlat implements StepListener
{
    private Main main;
    private MovingPlatforms movingPlatforms;
    public MovingPlat(Main main, MovingPlatforms movingPlatforms)
    {
        this.main = main;
        this.movingPlatforms = movingPlatforms;
    }

    @Override
    public void preStep(StepEvent e)
    {

    }

    @Override
    public void postStep(StepEvent e)
    {
        if(movingPlatforms.getPosition().y % 10 == 0)
        {
            movement();
        }
        if(movingPlatforms.getPosition().y % 10 == 1)
        {
            movement();
        }


    }
    public void movement()
    {

        if(movingPlatforms.getPosition().x == 11)
        {
            movingPlatforms.setPosition(new Vec2(movingPlatforms.getPosition().x-0.1f, movingPlatforms.getPosition().y));
        }
        else if(movingPlatforms.getPosition().x == -33)
        {
            movingPlatforms.setPosition(new Vec2(movingPlatforms.getPosition().x+0.1f, movingPlatforms.getPosition().y));
        }
        else
        {
            movingPlatforms.setPosition(new Vec2(movingPlatforms.getPosition().x+0.1f, movingPlatforms.getPosition().y));
        }
    }
}

