/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.property;

import maze.model.Maze;
import java.util.Observable;

/**
 * Ensure that any object has the same reference to the maze at any moment
 * 
 */
public class MazeProperty extends Observable {
    
    private Maze maze;
    
    public MazeProperty(Maze maze){
        this.maze = maze;
    }

    public Maze getMaze() {
        return this.maze;
    }

    public void setMaze(Maze newMaze) {
        setChanged();
        this.maze = newMaze;
        notifyObservers(newMaze);
    }

}
