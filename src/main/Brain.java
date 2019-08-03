package main;

import org.jsfml.system.Vector2f;
import java.util.Random;

public class Brain {
    public Vector2f[] directions;

    Brain(int directionCount)
    {
        directions = new Vector2f[directionCount];
    }

    void RndPopulateDir()
    {
        Random rng = new Random();
        for(int i = 0; i<directions.length; i++)
        {
            directions[i] = new Vector2f((float)Math.random()*2000-1000,(float)Math.random()*2000-1000);
        }
    }

    public void Mutate(float mutationRate)
    {
        for(int i = 0; i<directions.length; i++)
        {
            if(mutationRate>Math.random())
                directions[i] =  new Vector2f((float)Math.random()*2000-1000,(float)Math.random()*2000-1000);
        }

    }
}
