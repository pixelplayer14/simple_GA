package main;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

import java.util.Arrays;
import java.util.Random;


public class Population {

    Vector2i goal = new Vector2i(400,0);
    Vector2f spawn;
    Dot[] dots;
    int acceCount;

    Population(int populationSize, Vector2f spawnPos, int accCount)
    {
        acceCount = accCount;
        spawn = spawnPos;
        dots = new Dot[populationSize];
        StartRandomGeneration();
    }

    void StartRandomGeneration()
    {
        for(int i = 0; i<dots.length; i++)
        {
            dots[i] = new Dot(spawn,acceCount);
            dots[i].genes.RndPopulateDir();
        }
    }

    void Update(RenderWindow window, float deltaTime)
    {
        for(int i = 0; i<dots.length; i++)
        {
            dots[i].Update(deltaTime);
            dots[i].graphicIndicator.setFillColor(Color.BLUE);
            window.draw(dots[i].graphicIndicator);
            if(dots[i].OutOfSquareBounds(new Vector2i(0,0),window.getSize()))
                dots[i].isDead = true;
        }

        if(AllDotsDead())
            NewGeneration();
    }
    Boolean AllDotsDead()
    {
        for(int i = 0; i<dots.length; i++)
        {
            if(!dots[i].isDead)
                return false;
        }
        return  true;
    }

    void NewGeneration()
    {
        Dot[] newDots = new Dot[dots.length];
        float totalFitness = 0;

        for(int i = 0; i<dots.length; i++)
            totalFitness += dots[i].Fitness(goal);

        for(int i = 0; i<dots.length; i++)
        {
            newDots[i] = new Dot(spawn,acceCount);
            System.arraycopy(SelectParent(totalFitness).genes.directions,0,newDots[i].genes.directions,0,acceCount/2);
            System.arraycopy(SelectParent(totalFitness).genes.directions,acceCount/2,newDots[i].genes.directions,acceCount/2,acceCount/2);
            newDots[i].genes.Mutate(0.1f);
        }

        dots = newDots;
    }

    Dot SelectParent(float totalFitness)
    {
        double rn = Math.random()*totalFitness;
        float fitnessCounter = 0;


        for(int i = 0; i<dots.length; i++)
        {
            fitnessCounter += dots[i].Fitness(goal);
            if(fitnessCounter > rn)
                return dots[i];
        }

        System.out.println(fitnessCounter);
        System.out.println(rn);

        return  null;
    }
}
