package main;

import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

public class Main {

    public static void main(String[] args)
    {

        RenderWindow window = new RenderWindow();
        window.create(new VideoMode(800,800),"hello humanity");

        Population population = new Population(50,new Vector2f(400,400),500);
        Clock deltaTime = new Clock();

        while(window.isOpen())
        {
            window.setFramerateLimit(60);
            window.clear(Color.WHITE);
            population.Update(window,deltaTime.restart().asSeconds());
            window.display();

            for(Event event : window.pollEvents()) {
                if(event.type == Event.Type.CLOSED) {
                    //The user pressed the close button
                    window.close();
                }
            }
        }
    }

}
