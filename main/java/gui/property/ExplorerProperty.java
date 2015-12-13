/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.property;

import maze.model.Explorer;
import maze.model.Maze;
import java.util.Observable;
import java.util.Observer;

/**
 * Be notified when the maze changes + notify observers when the explorer changes
 */
public class ExplorerProperty extends Observable implements Observer {
    
    private Explorer explorer;
    
    public ExplorerProperty(MazeProperty mazeProperty){
        mazeProperty.addObserver(this);
        explorer = new Explorer(mazeProperty.getMaze());
    }


    public Explorer getExplorer() {
        return this.explorer;
    }

    @Override
    public void update(Observable o, Object arg) {
        explorer = new Explorer((Maze) arg);
        setChanged();
        notifyObservers(explorer);
    }
}
