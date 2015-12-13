/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.panel;

import maze.model.Maze;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

/**
 * Information relative to the maze
 * 
 */
public class InfoPanel extends JPanel implements Observer {
    
    private final JLabel nWalls, nEmptySpaces;
    
    public InfoPanel(final GamePanel gamePanel){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Number of walls
        JPanel walls = new JPanel();
        walls.setLayout(new BoxLayout(walls, BoxLayout.Y_AXIS));        
        walls.add(new JLabel("Walls:"));
        
        nWalls = new JLabel(""+gamePanel.getMazeProperty().getMaze().getnWalls());
        walls.add(nWalls);
        
        // Number of empty spaces
        JPanel emptySpaces = new JPanel();
        emptySpaces.setLayout(new BoxLayout(emptySpaces, BoxLayout.Y_AXIS));
        emptySpaces.add(new JLabel("Empty spaces:"));
        
        nEmptySpaces = new JLabel(""+gamePanel.getMazeProperty().getMaze().getnEmptySpaces());
        emptySpaces.add(nEmptySpaces);
        
        // Show cell at given coordinates
        CoordinatesPanel coordinatesPanel = new CoordinatesPanel(gamePanel.getFieldPanel());
        
        add(walls);
        add(new JSeparator(JSeparator.HORIZONTAL));
        add(emptySpaces);
        add(new JSeparator(JSeparator.HORIZONTAL));
        add(coordinatesPanel);
        
        gamePanel.getMazeProperty().addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        Maze maze = (Maze) arg;
        nWalls.setText(""+maze.getnWalls());
        nEmptySpaces.setText(""+maze.getnEmptySpaces());
        
        revalidate();
    }
    
}
