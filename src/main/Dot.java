package main;

import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

public class Dot {

    public CircleShape graphicIndicator = new CircleShape(3);
    Vector2f position;
    Vector2f speed = new Vector2f(0,0);
    Vector2f acceleration = new Vector2f(0,0);
    int accIndex = 0;
    public boolean isDead = false;

    Brain genes;

    Dot(Vector2f init_pos, int directionCount)
    {
        position = init_pos;
        genes = new Brain(directionCount);
    }

    void Update(float elapsedTime)
    {
        if(!isDead)
        {

            position = Vector2f.add(position, Vector2f.mul(speed,elapsedTime));
            graphicIndicator.setPosition(position);

            speed = Vector2f.add(speed,  Vector2f.mul(acceleration,elapsedTime));
            acceleration = genes.directions[accIndex];
            accIndex ++;
            if(accIndex == genes.directions.length)
            {
                isDead = true;
            }
        }

    }

   public boolean OutOfSquareBounds(Vector2i p1, Vector2i p2)
    {
        if(position.x < p1.x || position.x >p2.x || position.y<p1.y || position.y>p2.y)
            return true;
        else
            return false;
    }

    public float Fitness(Vector2i goal)
    {
        float distanceToGoal =(float) Math.sqrt( Math.pow(goal.x-position.x,2)+Math.pow(goal.y-position.y,2));
        return 1/(distanceToGoal*distanceToGoal);
    }

}
